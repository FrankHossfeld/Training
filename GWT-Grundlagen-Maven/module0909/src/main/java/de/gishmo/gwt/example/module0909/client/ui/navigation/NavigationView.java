package de.gishmo.gwt.example.module0909.client.ui.navigation;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlowPanel;

import de.gishmo.gwt.example.module0909.client.resources.ApplicationConstants;
import de.gishmo.gwt.example.module0909.client.resources.ApplicationCss;
import de.gishmo.gwt.example.module0909.client.resources.CssProvider;
import de.gishmo.gwt.example.module0909.client.widgets.ReverseComposite;

public class NavigationView
  extends ReverseComposite<INavigationView.Presenter>
  implements INavigationView {

  private ApplicationCss style;
  private FlowPanel      panel;
  private Button         searchButton;
  private Button         listButton;

  //------------------------------------------------------------------------------

  public NavigationView() {
    super();
    GWT.debugger();
    createView();
    bind();
  }

  //------------------------------------------------------------------------------

  private void bind() {
    searchButton.addClickHandler(new ClickHandler() {
      @Override
      public void onClick(ClickEvent event) {
        getPresenter().doShowSearch();
      }
    });

    listButton.addClickHandler(new ClickHandler() {
      @Override
      public void onClick(ClickEvent event) {
        getPresenter().doShowList();
      }
    });

  }

  public void createView() {
    GWT.debugger();
    this.style = CssProvider.get()
                            .getStyle();

    panel = new FlowPanel();
    panel.addStyleName(style.navigationPanel());

    searchButton = new Button(ApplicationConstants.CONSTANTS.searchFormButton());
    searchButton.addStyleName(style.navigationButton());
    panel.add(searchButton);

    listButton = new Button(ApplicationConstants.CONSTANTS.listFormButton());
    listButton.addStyleName(style.navigationButton());
    panel.add(listButton);

    initWidget(panel);
  }
}
