package de.gishmo.gwt.example.labelgenerator.rebind;

import com.google.gwt.core.ext.*;
import com.google.gwt.core.ext.typeinfo.JClassType;
import com.google.gwt.core.ext.typeinfo.JField;
import com.google.gwt.core.ext.typeinfo.JParameterizedType;
import com.google.gwt.core.ext.typeinfo.TypeOracle;
import com.google.gwt.user.client.ui.IsWidget;
import com.squareup.javapoet.*;
import de.gishmo.gwt.example.labelgenerator.client.AbstractLabelDriver;
import de.gishmo.gwt.example.labelgenerator.client.HasLabel;
import de.gishmo.gwt.example.labelgenerator.client.LabelProvider;
import de.gishmo.gwt.example.labelgenerator.client.SimpleLabelSupportDriver;
import de.gishmo.gwt.example.labelgenerator.client.annotations.LabelSupport;

import javax.lang.model.element.Modifier;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

public class SimpleLabelGenerator
  extends IncrementalGenerator {

  private final static String IMPL_NAME = "LabelDriverImpl";

  private static final long GENERATOR_VERSION_ID = 1L;

  @Override
  public RebindResult generateIncrementally(TreeLogger logger,
                                            GeneratorContext context,
                                            String typeName)
    throws UnableToCompleteException {

    // get the typeOracle
    TypeOracle typeOracle = context.getTypeOracle();
    assert typeOracle != null;

    // get the JClassType of the label accessor
    JClassType labelDriver = typeOracle.findType(typeName);
    if (labelDriver == null) {
      logger.log(TreeLogger.ERROR,
                 "LabelGenerator: Unable to find metadata for LabelGenerator class '" + typeName + "'");
      throw new UnableToCompleteException();
    }

    // branch logger
    TreeLogger labelLogger = logger.branch(TreeLogger.TRACE,
                                           "LabelGenerator: Generating driver for LabelGenerator class '" + labelDriver
                                                                                                              .getQualifiedSourceName() + "'",
                                           null);

    // some validations
    // must be done before creating the Printwriter, because we need one
    // parameter for creating the name
    // check, that the tool tip driver is an interface
    if (labelDriver.isInterface() == null) {
      logger.log(TreeLogger.ERROR,
                 "LabelGenerator: '" + typeName + "' is not a interface");
      throw new UnableToCompleteException();
    }
    // The paramized type checks ...
    JParameterizedType parameterizedType = getGeneric(context,
                                                      labelDriver,
                                                      SimpleLabelSupportDriver.class.getCanonicalName());
    if (parameterizedType == null || parameterizedType.getTypeArgs() == null || parameterizedType
                                                                                  .getTypeArgs().length == 0) {
      labelLogger.log(TreeLogger.ERROR,
                      "LabelGenerator: does not have a type parameter");
      throw new UnableToCompleteException();
    }
    JClassType labelContainer = parameterizedType.getTypeArgs()[0];
    if (labelContainer == null) {
      labelLogger.log(TreeLogger.ERROR,
                      "LabelGenerator: does not have a type parameter or an ampty type parameter");
      throw new UnableToCompleteException();
    }
    if (!checkRequiredInterface(labelContainer,
                                LabelProvider.class.getCanonicalName())) {
      labelLogger.log(TreeLogger.ERROR,
                      "LabelGenerator: does not implement: " + LabelProvider.class.getCanonicalName());
      throw new UnableToCompleteException();
    }

    // PrintWriter erzeugen
    PrintWriter labelDriverPrintWriter = context.tryCreate(labelLogger,
                                                           labelContainer.getPackage()
                                                                         .getName(),
                                                           labelContainer.getName() + SimpleLabelGenerator.IMPL_NAME);
    if (labelDriverPrintWriter == null) {
      // generation already done, use already generated classes
      labelLogger.log(TreeLogger.INFO,
                      "LabelGenerator:  reuse already generated classes",
                      null);
      // stop generating
      return new RebindResult(RebindMode.USE_EXISTING,
                              getImplName(labelContainer.getPackage()
                                                        .getName(),
                                          labelContainer.getName()));
    }

    // check weather there is a usual version or not.
    if (checkAlreadyGenerated(typeOracle,
                              getImplName(labelContainer.getPackage()
                                                        .getName(),
                                          labelContainer.getName()))) {
      // Log
      logger.log(TreeLogger.INFO,
                 "LabelGenerator: reuse already generated files",
                 null);
      // stop generating
      return new RebindResult(RebindMode.USE_EXISTING,
                              getImplName(labelContainer.getPackage()
                                                        .getName(),
                                          labelContainer.getName()));
    }

    create(labelLogger,
           context,
           labelDriverPrintWriter,
           labelDriver,
           labelContainer);

    return new RebindResult(RebindMode.USE_ALL_NEW_WITH_NO_CACHING,
                            getImplName(labelContainer.getPackage()
                                                      .getName(),
                                        labelContainer.getName()));
  }

  @Override
  public long getVersionId() {
    return SimpleLabelGenerator.GENERATOR_VERSION_ID;
  }

  private JParameterizedType getGeneric(GeneratorContext context,
                                        JClassType type,
                                        String requestedType)
    throws UnableToCompleteException {
    Set<? extends JClassType> set = type.getFlattenedSupertypeHierarchy();
    for (JClassType jClassType : set) {
      if (jClassType.getQualifiedSourceName()
                    .equals(requestedType)) {
        return jClassType.asParameterizationOf(context.getTypeOracle()
                                                      .findType(requestedType)
                                                      .isGenericType());
      }
    }
    return null;
  }

  private boolean checkRequiredInterface(JClassType type,
                                         String requiredInterface) {
    Set<? extends JClassType> set = type.getFlattenedSupertypeHierarchy();
    for (JClassType jClassType : set) {
      if (jClassType.getQualifiedSourceName()
                    .equals(requiredInterface)) {
        return true;
      }
    }
    return false;
  }

  private String getImplName(String packageName,
                             String simpleSourceName) {
    return packageName + "." + simpleSourceName + SimpleLabelGenerator.IMPL_NAME;
  }

  private boolean checkAlreadyGenerated(TypeOracle typeOracle,
                                        String implClassName) {
    return typeOracle.findType(implClassName) != null;
  }

  private void create(TreeLogger labelLogger,
                      GeneratorContext context,
                      PrintWriter labelDriverPrintWriter,
                      JClassType labelDriver,
                      JClassType labelContainer)
    throws UnableToCompleteException {

    Date start = new Date();

    labelLogger.log(TreeLogger.INFO,
                    "LabelGenerator: generate Configurator classes");

    // ermitteln der Felder, die verarbeitet werden muessen ...
    List<LabelAnnotatedField> labelAnnotatedFields = new ArrayList<>();
    for (JField field : labelContainer.getFields()) {
      LabelSupport labelWidget = field.getAnnotation(LabelSupport.class);
      if (labelWidget != null) {
        JClassType fieldType = field.getType()
                                    .isClassOrInterface();
        if (!checkRequiredInterface(fieldType,
                                    HasLabel.class.getCanonicalName())) {
          labelLogger.log(TreeLogger.ERROR,
                          "LabelGenerator: annotated field " + field
                                                                 .getName() + " does not implement: " + HasLabel.class.getCanonicalName());
          throw new UnableToCompleteException();
        }
        if (!checkRequiredInterface(fieldType,
                                    IsWidget.class.getCanonicalName())) {
          labelLogger.log(TreeLogger.ERROR,
                          "LabelGenerator: annotated field " + field
                                                                 .getName() + " does not implement: " + IsWidget.class.getCanonicalName());
          throw new UnableToCompleteException();
        }
        labelAnnotatedFields.add(new LabelAnnotatedField(labelWidget.value(),
                                                         field));
      }
    }
    generate(labelDriverPrintWriter,
             labelLogger,
             context,
             labelDriver,
             labelContainer,
             labelAnnotatedFields,
             start);
  }

  private void generate(PrintWriter labelDriverPrintWriter,
                        TreeLogger labelLogger,
                        GeneratorContext context,
                        JClassType labelDriver,
                        JClassType labelContainer,
                        List<LabelAnnotatedField> labelAnnotatedFields,
                        Date start)
    throws UnableToCompleteException {

    TypeSpec.Builder typeSpec = TypeSpec.classBuilder(labelContainer.getName() + SimpleLabelGenerator.IMPL_NAME)
                                        .superclass(ParameterizedTypeName.get(ClassName.get(AbstractLabelDriver.class),
                                                                              ClassName.get(labelContainer.getPackage()
                                                                                                          .getName(),
                                                                                            labelContainer.getName())))
                                        .addModifiers(Modifier.PUBLIC,
                                                      Modifier.FINAL)
                                        .addSuperinterface(ClassName.get(labelDriver.getPackage()
                                                                                    .getName(),
                                                                         labelDriver.getName()));

    typeSpec.addMethod(MethodSpec.constructorBuilder()
                                 .addModifiers(Modifier.PUBLIC)
                                 .addStatement("super()")
                                 .build());

    ParameterSpec containerParameter =
      ParameterSpec
        .builder(ClassName.get(labelContainer.getPackage()
                                             .getName(),
                               labelContainer.getName()),
                 "container",
                 Modifier.FINAL)
        .build();
    MethodSpec.Builder initializeMethod = MethodSpec.methodBuilder("initialize")
                                                    .addAnnotation(Override.class)
                                                    .addModifiers(Modifier.PUBLIC)
                                                    .returns(void.class)
                                                    .addParameter(containerParameter);
    initializeMethod.addStatement("this.container = $N",
                                  containerParameter);
    for (LabelAnnotatedField labelAnnotatedField : labelAnnotatedFields) {
      //    initializeMethod.addStatement("$T.alert(\"Here I am\")", Window.class);
      initializeMethod.addStatement("super.add($S, (($T) this.$N.$N))",
                                    labelAnnotatedField.getlabelId(),
                                    HasLabel.class,
                                    "container",
                                    labelAnnotatedField.getField()
                                                       .getName());
    }
    typeSpec.addMethod(initializeMethod.build());

    JavaFile javaFile = JavaFile.builder(labelContainer.getPackage()
                                                       .getName(),
                                         typeSpec.build())
                                .build();

    System.out.println(javaFile.toString());

    labelDriverPrintWriter.print(javaFile.toString());
    labelDriverPrintWriter.flush();
    labelDriverPrintWriter.close();

    context.commit(labelLogger,
                   labelDriverPrintWriter);

    Date end = new Date();

    labelLogger.log(TreeLogger.INFO,
                    "LabelGenerator: classes generated in " + Long.toString(end.getTime() - start.getTime()) + " ms.");
  }

  private class LabelAnnotatedField {

    private String labelId;

    private JField field;

    LabelAnnotatedField(String id,
                        JField field) {
      this.labelId = id;
      this.field = field;
    }

    String getlabelId() {
      return labelId;
    }

    JField getField() {
      return field;
    }
  }
}
