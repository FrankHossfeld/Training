package de.gishmo.gwt.example.module0809.client.ui.search;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ScrollPanel;
import de.gishmo.gwt.example.module0707.client.widgets.TextField;
import de.gishmo.gwt.example.module0809.client.resources.ApplicationConstants;
import de.gishmo.gwt.example.module0809.client.resources.ApplicationCss;
import de.gishmo.gwt.example.module0809.client.resources.ApplicationStyleFactory;
import de.gishmo.gwt.example.module0809.client.widgets.ReverseComposite;

public class SearchView
  extends ReverseComposite<ISearchView.Presenter>
  implements ISearchView {

  private ScrollPanel    panel;
  private ApplicationCss style;
  private TextField      searchName;
  private TextField      searchCity;
  private Button         searchButton;
  private Button         resetButton;

//------------------------------------------------------------------------------

  public SearchView() {
    super();
    
    this.style = ApplicationStyleFactory.get().getStyle();
    
    createView();
    bind();
  }

//------------------------------------------------------------------------------

  @Override
  public void setSearch(String searchName, String searchCity) {
    this.searchName.setText(searchName);
    this.searchCity.setText(searchCity);
  }

//------------------------------------------------------------------------------

  private void bind() {
    searchButton.addClickHandler(new ClickHandler() {
      @Override
      public void onClick(ClickEvent event) {
        getPresenter().doClickSearchButton(searchName.getText(), searchCity.getText());
      }
    });

    resetButton.addClickHandler(new ClickHandler() {
      @Override
      public void onClick(ClickEvent event) {
        searchName.setText("");
        searchCity.setText("");
      }
    });
  }
  
  private void createView() {
    panel = new ScrollPanel();
    
    FlowPanel searchPanel = new FlowPanel();
    searchPanel.addStyleName(style.searchPanel());
    panel.add(searchPanel);
    
    Label headline = new Label(ApplicationConstants.CONSTANTS.searchHeadline());
    headline.addStyleName(style.headline());
    searchPanel.add(headline);
    
    searchName = new TextField(ApplicationConstants.CONSTANTS.searchName());
    searchPanel.add(searchName);
    
    searchCity = new TextField(ApplicationConstants.CONSTANTS.searchCity());
    searchPanel.add(searchCity);
    
    FlowPanel buttonBar = new FlowPanel();
    buttonBar.addStyleName(style.searchPanelButtonBar());
    searchPanel.add(buttonBar);
    
    searchButton = new Button(ApplicationConstants.CONSTANTS.searchButton());
    searchButton.addStyleName(style.button());
    buttonBar.add(searchButton);
     
    resetButton = new Button(ApplicationConstants.CONSTANTS.resetButton());
    resetButton.addStyleName(style.button());
    buttonBar.add(resetButton);

    initWidget(panel);
  }
}

