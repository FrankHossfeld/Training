package de.gishmo.gwt.example.module0802.client.ui.navigation;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlowPanel;
import de.gishmo.gwt.example.module0802.client.ClientContext;
import de.gishmo.gwt.example.module0802.client.resources.ApplicationConstants;
import de.gishmo.gwt.example.module0802.client.resources.ApplicationCss;
import de.gishmo.gwt.example.module0802.client.widgets.ReverseComposite;

public class NavigationView
  extends ReverseComposite<INavigationView.Presenter>
  implements INavigationView {

  private ApplicationCss style;
  private FlowPanel      panel;
  private Button         searchButton;
  private Button         listButton;
  
//------------------------------------------------------------------------------

  public NavigationView(ApplicationCss style) {
    super();
    
    this.style = style;
    
    createView();
    bind();
  }

//------------------------------------------------------------------------------

  private void bind() {
    searchButton.addClickHandler(event -> getPresenter().doSetCenter(ClientContext.SEARCH_FORM));
    
    listButton.addClickHandler(event -> getPresenter().doSetCenter(ClientContext.RESULT_LIST));

  }

  private void createView() {
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
