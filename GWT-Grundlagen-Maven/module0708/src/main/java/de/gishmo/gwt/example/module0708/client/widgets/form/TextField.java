package de.gishmo.gwt.example.module0708.client.widgets.form;

import com.google.gwt.core.client.Scheduler;
import com.google.gwt.core.client.Scheduler.ScheduledCommand;
import com.google.gwt.core.shared.GWT;
import com.google.gwt.editor.client.IsEditor;
import com.google.gwt.editor.ui.client.adapters.ValueBoxEditor;
import com.google.gwt.event.dom.client.HasKeyDownHandlers;
import com.google.gwt.event.dom.client.KeyDownHandler;
import com.google.gwt.event.logical.shared.ResizeEvent;
import com.google.gwt.event.logical.shared.ResizeHandler;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.user.client.ui.*;

public class TextField
  extends Composite
  implements HasText,
             HasValue<String>,
             Focusable,
             IsEditor<ValueBoxEditor<String>>,
             HasKeyDownHandlers {

  
  private TextFieldStyle    style;
  private Label             label;
  private TextBox           textBox;
  private FlowPanel         panel;
  private ResizeLayoutPanel widgetPanel;


  public interface Resources
    extends ClientBundle {
    
    @Source("TextFieldStyle.css")
    TextFieldStyle style();
    
  }


  public TextField() {
    this(null);
  }

  public TextField(String label) {
    Resources resources = GWT.create(Resources.class);
    this.style = resources.style();
    this.style.ensureInjected();
    
    createWidget();
    
    setLabel(label);
  }
  
  
  
  public String getLabel() {
    return label.getText();
  }

  @Override
  public int getTabIndex() {
    return textBox.getTabIndex();
  }

  @Override
  public String getText() {
    return textBox.getText();
  }

  @Override
  public void setAccessKey(char key) {
    textBox.setAccessKey(key);
  }

  @Override
  public void setFocus(boolean focused) {
    textBox.setFocus(focused);
  }
  
  public void setLabel(String label) {
    if (label != null && label.length() > 0 ) {
      this.label.setText(label);
      this.label.setVisible(true);
      super.setHeight("68px");
    } else {
      this.label.setText("");
      this.label.setVisible(false);
      super.setHeight("42px");
    }
  }

  @Override
  public void setTabIndex(int index) {
    textBox.setTabIndex(index);
  }
  
  @Override
  public void setText(String text) {
    this.textBox.setText(text);
  }
  
  public void setWidth(String width) {
    assert false : "setting width not allowed";
  }

  
  private void createWidget() {
    widgetPanel = new ResizeLayoutPanel();
    widgetPanel.addStyleName(style.widgetPanel());
    widgetPanel.addResizeHandler(new ResizeHandler() {
      @Override
      public void onResize(ResizeEvent event) {
        forceLayout();
      }
    });
    
    panel = new FlowPanel();
    panel.addStyleName(style.panel());
    widgetPanel.add(panel);

    label = new Label();
    label.addStyleName(style.label());
    panel.add(label);
    
    textBox = new TextBox();
    textBox.addStyleName(style.textBox());
    panel.add(textBox);
    
    initWidget(widgetPanel);
  }
  
  
  public void onLoad() {
    super.onLoad();
    Scheduler.get().scheduleDeferred(new ScheduledCommand() {
      @Override
      public void execute() {
        forceLayout(); 
      }
    });    
  }
  
  private void forceLayout() {
    if (this.isAttached()) {
      Widget parent = getParent();
      if (parent != null) {
        int parentWidth = parent.getOffsetWidth();
        
        label.setWidth(Integer.toString(parentWidth - 48) + "px");
        textBox.setWidth(Integer.toString(parentWidth - 48) + "px");
      }
    }
  }

//------------------------------------------------------------------------------  
 
  @Override
  public ValueBoxEditor<String> asEditor() {
    return textBox.asEditor();
  }

  @Override
  public HandlerRegistration addValueChangeHandler(ValueChangeHandler<String> handler) {
    return textBox.addValueChangeHandler(handler);
  }

  @Override
  public String getValue() {
    return textBox.getValue();
  }

  @Override
  public void setValue(String value) {
    textBox.setValue(value);
  }

  @Override
  public void setValue(String value, boolean fireEvents) {
    textBox.setValue(value, fireEvents);
  }

  @Override
  public HandlerRegistration addKeyDownHandler(KeyDownHandler handler) {
    return textBox.addKeyDownHandler(handler);
  }
}
