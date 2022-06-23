package de.gishmo.gwt.example.module0702.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.*;
import elemental2.dom.DomGlobal;
import jsinterop.base.Js;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class Module0702
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
    button.addClickHandler(event -> Window.alert("Ich wurde gedrückt!"));
    SimplePanel sp = new SimplePanel();
    sp.add(button);
    RootPanel.get()
             .add(sp);

    Js.debugger();
    Window.alert("Test 01");
    DomGlobal.window.alert("Test02");

    Anchor anchor = new Anchor("Panik");
    anchor.addClickHandler(event -> Window.alert("Don't worry, be happy"));
    RootPanel.get()
             .add(anchor);


  }
}
