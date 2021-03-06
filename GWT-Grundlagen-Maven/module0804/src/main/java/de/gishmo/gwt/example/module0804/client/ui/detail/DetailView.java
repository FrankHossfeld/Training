package de.gishmo.gwt.example.module0804.client.ui.detail;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ScrollPanel;
import de.gishmo.gwt.example.module0503.shared.dto.Person;
import de.gishmo.gwt.example.module0707.client.widgets.TextField;
import de.gishmo.gwt.example.module0804.client.resources.ApplicationConstants;
import de.gishmo.gwt.example.module0804.client.resources.ApplicationCss;
import de.gishmo.gwt.example.module0804.client.widgets.ReverseComposite;

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

  public DetailView(ApplicationCss style) {
    super();

    this.style = style;

    createView();
    bind();
  }

//------------------------------------------------------------------------------

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

  private void updateDetailForm() {
    person.setFirstName(detailFirstName.getText());
    person.setName(detailName.getText());
    person.getAddress()
          .setStreet(detailStreet.getText());
    person.getAddress()
          .setZip(detailZip.getText());
    person.getAddress()
          .setCity(detailCity.getText());
  }

  @Override
  public void setUpData(Person person) {
    this.person = person;
    setDetailForm();
  }

  private void setDetailForm() {
    if (person != null) {
      detailFirstName.setText(person.getFirstName());
      detailName.setText(person.getName());
      detailStreet.setText(person.getAddress()
                                 .getStreet());
      detailZip.setText(person.getAddress()
                              .getZip());
      detailCity.setText(person.getAddress()
                               .getCity());
    }
  }
}
