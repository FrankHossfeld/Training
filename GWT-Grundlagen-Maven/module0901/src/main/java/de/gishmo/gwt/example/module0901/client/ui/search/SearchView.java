package de.gishmo.gwt.example.module0901.client.ui.search;

import com.google.gwt.core.client.GWT;
import com.google.gwt.editor.client.Editor;
import com.google.gwt.editor.client.SimpleBeanEditorDriver;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ScrollPanel;
import de.gishmo.gwt.example.module0503.shared.dto.PersonSearch;
import de.gishmo.gwt.example.module0708.client.widgets.ReverseComposite;
import de.gishmo.gwt.example.module0708.client.widgets.form.TextField;
import de.gishmo.gwt.example.module0901.client.resources.ApplicationConstants;
import de.gishmo.gwt.example.module0901.client.resources.ApplicationCss;


public class SearchView
  extends ReverseComposite<ISearchView.Presenter>
  implements ISearchView,
             Editor<PersonSearch> {
  
  interface Driver
    extends SimpleBeanEditorDriver<PersonSearch, SearchView> {
  }

  private Driver driver;
  
  private ScrollPanel    panel;
  private ApplicationCss style;
  
  @Path("name")
  TextField searchName;
  @Path("city")
  TextField searchCity;
  
  private Button searchButton;
  private Button resetButton; 
  
  private PersonSearch search;

//------------------------------------------------------------------------------

  public SearchView(ApplicationCss style) {
    super();
    
    this.style = style;
    
    createView();
    bind();
  }

//------------------------------------------------------------------------------

  @Override
  public void setSearch(PersonSearch search) {
    this.search = search;
    /* transfer data to widgets */
    driver.edit(search);
  }

//------------------------------------------------------------------------------

  private void bind() {
    searchButton.addClickHandler(new ClickHandler() {
      @Override
      public void onClick(ClickEvent event) {
        /* transfer data from widgets to model */
        search = driver.flush();
        getPresenter().doClickSearchButton(search);
      }
    });

    resetButton.addClickHandler(new ClickHandler() {
      @Override
      public void onClick(ClickEvent event) {
        searchName.setText("");
        searchCity.setText("");
      }
    });

    /* Editor */
    driver = GWT.create(Driver.class);
    driver.initialize(this);
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

