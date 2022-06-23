package de.gishmo.gwt.example.module0704.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.*;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class Module0704
  implements EntryPoint {

  private DialogBox dialogBox;

  private TextBox searchName;
  private TextBox searchCity;

  /**
   * This is the entry point method.
   */
  public void onModuleLoad() {
    createDialogBox();

    final Button showButton = new Button("show dialogbox");
    // We can add style names to widgets
    showButton.addStyleName("sendButton");

    showButton.addClickHandler(event -> dialogBox.center());

    // Use RootPanel.get() to get the entire body element
    RootPanel.get()
             .add(showButton);

  }

  private void createDialogBox() {
    dialogBox = new DialogBox();
    dialogBox.setText("Kundensuche");
    dialogBox.setAnimationEnabled(true);

    VerticalPanel dialogVPanel = new VerticalPanel();
    dialogVPanel.addStyleName("dialogVPanel");
    dialogBox.setWidget(dialogVPanel);

    HorizontalPanel hp01 = new HorizontalPanel();
    dialogVPanel.add(hp01);

    Label labelName = new Label("Name");
    labelName.getElement()
             .getStyle()
             .setMarginTop(10,
                           Unit.PX);
    labelName.setWidth("75px");
    hp01.add(labelName);
    searchName = new TextBox();
    searchName.setWidth("250px");
    searchName.getElement()
              .getStyle()
              .setMargin(5,
                         Unit.PX);
    hp01.add(searchName);

    HorizontalPanel hp02 = new HorizontalPanel();
    dialogVPanel.add(hp02);

    Label labelCity = new Label("City");
    labelCity.getElement()
             .getStyle()
             .setMarginTop(10,
                           Unit.PX);
    labelCity.setWidth("75px");
    hp02.add(labelCity);
    searchCity = new TextBox();
    searchCity.setWidth("250px");
    searchCity.getElement()
              .getStyle()
              .setMargin(5,
                         Unit.PX);
    hp02.add(searchCity);

    HorizontalPanel hp03 = new HorizontalPanel();
    dialogVPanel.add(hp03);
    dialogVPanel.setCellHorizontalAlignment(hp03,
                                            HasHorizontalAlignment.ALIGN_RIGHT);

    final Button searchButton = new Button("Suchen");
    searchButton.getElement()
                .getStyle()
                .setMargin(5,
                           Unit.PX);
    hp03.add(searchButton);

    final Button closeButton = new Button("Schliessen");
    closeButton.getElement()
               .getStyle()
               .setMargin(5,
                          Unit.PX);
    hp03.add(closeButton);
    closeButton.addClickHandler(new ClickHandler() {
      @Override
      public void onClick(ClickEvent event) {
        dialogBox.hide();
      }
    });
  }
}
