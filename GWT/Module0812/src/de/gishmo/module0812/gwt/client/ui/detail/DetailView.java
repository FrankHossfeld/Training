package de.gishmo.module0812.gwt.client.ui.detail;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ScrollPanel;

import de.gishmo.module0812.domain.dto.shared.model.Person;
import de.gishmo.module0812.gwt.client.resource.ApplicationConstants;
import de.gishmo.module0812.gwt.client.resource.ApplicationCss;
import de.gishmo.module0812.gwt.client.resource.ApplicationStyleFactory;
import de.gishmo.module0812.gwt.client.widget.ReverseComposite;
import de.gishmo.module0812.gwt.client.widget.TextField;

public class DetailView
  extends ReverseComposite<IDetailView.Presenter>
  implements IDetailView {

  private ScrollPanel panel;

  private TextField detailFirstName;
  private TextField detailName;
  private TextField detailStreet;
  private TextField detailZip;
  private TextField detailCity;
  
  private Button saveButton;
  private Button revertButton; 
  
  private ApplicationCss style;
  
  private Person person;

//------------------------------------------------------------------------------

  public DetailView() {
    super();
    
    this.style = ApplicationStyleFactory.get().getStyle();
    
    createView();
    bind();
  }

//------------------------------------------------------------------------------

  @Override
  public boolean isDirty() {
    boolean notDirty = (person.getFirstName().equals(detailFirstName.getText())) &&
                       (person.getName().equals(detailName.getText())) &&
                       (person.getAddress().getStreet().equals(detailStreet.getText())) &&
                       (person.getAddress().getZip().equals(detailZip.getText())) &&
                       (person.getAddress().getCity().equals(detailCity.getText()));
    return !notDirty;
  }

  @Override
  public void setUpData(Person person) {
    this.person = person;
    setDetailForm();
  }

//------------------------------------------------------------------------------

  private void bind() {
    saveButton.addClickHandler(new ClickHandler() {
      @Override
      public void onClick(ClickEvent event) {
        updateDetailForm();
        getPresenter().doUpdate(person);
      }
    });
    
    revertButton.addClickHandler(new ClickHandler() {
      @Override
      public void onClick(ClickEvent event) {
        getPresenter().doRevert();
      }
    });
  }

  private void createView() {
    panel = new ScrollPanel();
  
    FlowPanel detailPanel = new FlowPanel();
    detailPanel.addStyleName(style.detailPanel());
    panel.add(detailPanel);
  
    Label headline = new Label(ApplicationConstants.CONSTANTS.detailHeadline());
    headline.addStyleName(style.headline());
    detailPanel.add(headline);
    
    detailFirstName = new TextField(ApplicationConstants.CONSTANTS.detailFirstName());
    detailPanel.add(detailFirstName);
    
    detailName = new TextField(ApplicationConstants.CONSTANTS.detailName());
    detailPanel.add(detailName);
    
    detailStreet = new TextField(ApplicationConstants.CONSTANTS.detailStreet());
    detailPanel.add(detailStreet);
    
    detailZip = new TextField(ApplicationConstants.CONSTANTS.detailZip());
    detailPanel.add(detailZip);
    
    detailCity = new TextField(ApplicationConstants.CONSTANTS.detailCity());
    detailPanel.add(detailCity);
  
    FlowPanel buttonBar = new FlowPanel();
    buttonBar.addStyleName(style.searchPanelButtonBar());
    detailPanel.add(buttonBar);
    
    saveButton = new Button(ApplicationConstants.CONSTANTS.saveButton());
    saveButton.addStyleName(style.button());
    buttonBar.add(saveButton);
    
    revertButton = new Button(ApplicationConstants.CONSTANTS.revertButton());
    revertButton.addStyleName(style.button());
    buttonBar.add(revertButton);
    
    initWidget(panel);
  }

  private void updateDetailForm() {
    person.setFirstName(detailFirstName.getText());
    person.setName(detailName.getText());
    person.getAddress().setStreet(detailStreet.getText());
    person.getAddress().setZip(detailZip.getText());
    person.getAddress().setCity(detailCity.getText());
  }
  
  private void setDetailForm() {
    if (person != null) {
      detailFirstName.setText(person.getFirstName());
      detailName.setText(person.getName());
      detailStreet.setText(person.getAddress().getStreet());
      detailZip.setText(person.getAddress().getZip());
      detailCity.setText(person.getAddress().getCity());
    }
  }
}
