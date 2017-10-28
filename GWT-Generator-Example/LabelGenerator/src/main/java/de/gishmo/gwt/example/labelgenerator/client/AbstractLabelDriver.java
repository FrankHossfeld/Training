package de.gishmo.gwt.example.labelgenerator.client;

import com.google.gwt.core.shared.GWT;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class AbstractLabelDriver<C extends LabelProvider>
  implements SimpleLabelSupportDriver<C> {

  protected C container;

  // 1. LabelId
  // 2. List der Widgets die dieses Label verwenden
  private Map<String, List<HasLabel>> labelWidgets;

  public AbstractLabelDriver() {
    this.labelWidgets = new HashMap<>();
  }

  public void add(String labelId,
                  HasLabel widget) {
    List<HasLabel> list = labelWidgets.get(labelId);
    if (list == null) {
      labelWidgets.put(labelId,
                       list = new ArrayList<>());
    }
    list.add(widget);
  }

  public void load() {
    GWT.debugger();
    for (String labelId : labelWidgets.keySet()) {
      for (HasLabel widget : labelWidgets.get(labelId)) {
        widget.setLabel(LabelFactory.get()
                                    .getLabel(labelId));
      }
    }
  }

  @Override
  public void initializeAndLoad(C contianer) {
    initialize(contianer);
    load();
  }

  public abstract void initialize(C contianer);

  // TODO cleqr Methode (Löschen der Labels im Container)
  // TODO remove immplementieren ...


}
