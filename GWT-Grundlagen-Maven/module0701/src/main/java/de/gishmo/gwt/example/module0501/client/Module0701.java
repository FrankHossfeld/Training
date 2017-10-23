package de.gishmo.gwt.example.module0501.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.*;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class Module0701
  implements EntryPoint {

  /**
   * This is the entry point method.
   */
  public void onModuleLoad() {
    // einfaches Label
    Label label = new Label("Ich bin ein Label!");
    RootPanel.get()
             .add(label);

    // Label mit zugeordneter CSS-Style
    Label labelHeadline = new Label("Ich bin ein Label mit " +
                                    "dem Style Headline!");
    labelHeadline.addStyleName("headline");
    RootPanel.get()
             .add(labelHeadline);


    // einfaches HTML
    HTML html = new HTML("<i>Kursiver Text</i>");
    RootPanel.get()
             .add(html);

    // HTML mit setzen des Style-Attributes im DOM
    HTML htmlKurUndBo = new HTML("Ich bin ein fettes, kurives HTML!");
    htmlKurUndBo.getElement()
                .setAttribute("style",
                              "font-weight: bold;");
    RootPanel.get()
             .add(htmlKurUndBo);


    Button button = new Button("Drück mich!");
    button.setSize("200px",
                   "30px");
    button.addClickHandler(new ClickHandler() {
      @Override
      public void onClick(ClickEvent event) {
        Window.alert("Ich wurde gedrückt!");
      }
    });
    RootPanel.get()
             .add(button);


    Anchor anchor = new Anchor("Panik");
    anchor.addClickHandler(new ClickHandler() {
      @Override
      public void onClick(ClickEvent event) {
        Window.alert("Don't worry, be happy");
      }
    });
    RootPanel.get()
             .add(anchor);
  }
}
