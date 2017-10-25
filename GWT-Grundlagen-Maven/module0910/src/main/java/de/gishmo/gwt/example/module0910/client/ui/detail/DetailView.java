package de.gishmo.gwt.example.module0910.client.ui.detail;

import com.google.gwt.core.client.GWT;
import com.google.gwt.editor.client.Editor;
import com.google.gwt.editor.client.SimpleBeanEditorDriver;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ScrollPanel;
import de.gishmo.gwt.example.module0503.shared.dto.Person;
import de.gishmo.gwt.example.module0707.client.widgets.TextField;
import de.gishmo.gwt.example.module0910.client.resources.ApplicationConstants;
import de.gishmo.gwt.example.module0910.client.resources.ApplicationCss;
import de.gishmo.gwt.example.module0910.client.resources.ApplicationStyleFactory;
import de.gishmo.gwt.example.module0910.client.widgets.ReverseComposite;

public class DetailView
  extends ReverseComposite<IDetailView.Presenter>
  implements IDetailView,
             Editor<Person> {

  @Path("firstName")
  TextField detailFirstName;
  @Path("name")
  TextField detailName;
  @Path("address.street")
  TextField detailStreet;
  @Path("address.zip")
  TextField detailZip;
  @Path("address.city")
  TextField detailCity;
  private ScrollPanel panel;
  private Button saveButton;
  private Button revertButton;

  private ApplicationCss style;

  private Driver driver;

  public DetailView() {
    super();

    this.style = ApplicationStyleFactory.get()
                                        .getStyle();

    createView();
    bind();
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

  private void bind() {
    saveButton.addClickHandler(new ClickHandler() {
      @Override
      public void onClick(ClickEvent event) {
        getPresenter().doUpdate(driver.flush());
      }
    });

    revertButton.addClickHandler(new ClickHandler() {
      @Override
      public void onClick(ClickEvent event) {
        getPresenter().doRevert();
      }
    });

    driver = GWT.create(Driver.class);
    driver.initialize(this);
  }

  @Override
  public boolean isDirty() {
    return driver.isDirty();
  }

  @Override
  public void setUpData(Person person) {
    driver.edit(person);
  }

  interface Driver
    extends SimpleBeanEditorDriver<Person, DetailView> {
  }
}
