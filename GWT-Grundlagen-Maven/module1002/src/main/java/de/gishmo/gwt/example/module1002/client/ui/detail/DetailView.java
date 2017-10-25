package de.gishmo.gwt.example.module1002.client.ui.detail;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.editor.client.Editor;
import com.google.gwt.editor.client.SimpleBeanEditorDriver;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.uibinder.client.UiTemplate;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

import de.gishmo.gwt.example.module0503.shared.dto.Person;
import de.gishmo.gwt.example.module0708.client.widgets.ReverseComposite;
import de.gishmo.gwt.example.module0708.client.widgets.form.TextField;
import de.gishmo.gwt.example.module1002.client.resources.ApplicationConstants;
import de.gishmo.gwt.example.module1002.client.resources.ApplicationCss;

public class DetailView
  extends ReverseComposite<IDetailView.Presenter>
  implements IDetailView,
             Editor<Person> {
  
  @UiTemplate("DetailView.ui.xml")
  interface DetailViewUiBinder
    extends UiBinder<Widget, DetailView> {
  }

  interface Driver 
    extends SimpleBeanEditorDriver<Person, DetailView> {
  }
  
  
  @UiField
  FlowPanel detailPanel;
  @UiField
  FlowPanel buttonBar;
  @UiField
  @Ignore
  Label headline;

  @UiField
  @Path("firstName")
  TextField detailFirstName;
  @UiField
  @Path("name")
  TextField detailName;
  @UiField
  @Path("address.street")
  TextField detailStreet;
  @UiField
  @Path("address.zip")
  TextField detailZip;
  @UiField
  @Path("address.city")
  TextField detailCity;
  
  @UiField
  @Ignore
  Button saveButton;
  @UiField
  @Ignore
  Button revertButton; 
  
  private ApplicationCss style;
  
  private Person person;

  private Driver driver;
  
//------------------------------------------------------------------------------

  public DetailView(ApplicationCss style) {
    super();
    
    this.style = style;
    
    createView();
    bind();
  }

//------------------------------------------------------------------------------

  @Override
  public boolean isDirty() {
    return driver.isDirty();
  }

  @Override
  public void setUpData(Person person) {
    this.person = person;
    setDetailForm();
  }

//------------------------------------------------------------------------------

  @UiHandler("saveButton")
  void doSave(ClickEvent clickEvent) {
    updateDetailForm();
    getPresenter().doUpdate(person);    
  }

  @UiHandler("revertButton")
  void doRevert(ClickEvent clickEvent) {
    getPresenter().doRevert();
  }
  
//------------------------------------------------------------------------------

  private void bind() {
    driver = GWT.create(Driver.class);
    driver.initialize(this);
  }

  private void createView() {
    DetailViewUiBinder uiBinder = GWT.create(DetailViewUiBinder.class);
    
    initWidget(uiBinder.createAndBindUi(this));
  
    detailPanel = new FlowPanel();
    detailPanel.addStyleName(style.detailPanel());
    buttonBar.addStyleName(style.searchPanelButtonBar());

    headline.addStyleName(style.headline());
    headline.setText(ApplicationConstants.CONSTANTS.detailHeadline());

    detailFirstName.setLabel(ApplicationConstants.CONSTANTS.detailFirstName());
    detailName.setLabel(ApplicationConstants.CONSTANTS.detailName());
    detailStreet.setLabel(ApplicationConstants.CONSTANTS.detailStreet());
    detailZip.setLabel(ApplicationConstants.CONSTANTS.detailZip());
    detailCity.setLabel(ApplicationConstants.CONSTANTS.detailCity());

    saveButton.addStyleName(style.button());
    saveButton.setText(ApplicationConstants.CONSTANTS.saveButton());

    revertButton.addStyleName(style.button());
    revertButton.setText(ApplicationConstants.CONSTANTS.revertButton());
  }

  private void updateDetailForm() {
    person = driver.flush();
  }
  
  private void setDetailForm() {
    if (person != null) {
      driver.edit(person);
    }
  }
}
