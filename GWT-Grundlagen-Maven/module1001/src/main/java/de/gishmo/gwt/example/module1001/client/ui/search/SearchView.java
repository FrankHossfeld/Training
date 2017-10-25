package de.gishmo.gwt.example.module1001.client.ui.search;

import com.google.gwt.core.client.GWT;
import com.google.gwt.editor.client.Editor;
import com.google.gwt.editor.client.SimpleBeanEditorDriver;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.uibinder.client.UiTemplate;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

import de.gishmo.gwt.example.module0503.shared.dto.PersonSearch;
import de.gishmo.gwt.example.module0708.client.widgets.ReverseComposite;
import de.gishmo.gwt.example.module0708.client.widgets.form.TextField;
import de.gishmo.gwt.example.module1001.client.resources.ApplicationConstants;
import de.gishmo.gwt.example.module1001.client.resources.ApplicationCss;

public class SearchView
  extends ReverseComposite<ISearchView.Presenter>
  implements ISearchView,
             Editor<PersonSearch> {
  
  @UiTemplate("SearchView.ui.xml")
  interface SearchViewUiBinder
    extends UiBinder<Widget, SearchView> {
  }

  interface Driver
    extends SimpleBeanEditorDriver<PersonSearch, SearchView> {
  }

  private Driver driver;
  
//  private ScrollPanel panel;
  private ApplicationCss style;
  
  @UiField
  FlowPanel searchPanel;
  @UiField
  FlowPanel buttonBar;
  @UiField
  @Ignore
  Label headline;
  
  @UiField
  @Path("name")
  TextField searchName;
  @UiField
  @Path("city")
  TextField searchCity;
  @UiField
  @Ignore
  Button searchButton;
  @UiField
  @Ignore
  Button resetButton; 
  
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

  @UiHandler("searchButton")
  void doSearch(ClickEvent clickEvent) {
    /* transfer data from widgets to model */
    search = driver.flush();
    getPresenter().doClickSearchButton(search);
  }

  @UiHandler("resetButton")
  void doRevert(ClickEvent clickEvent) {
    searchName.setText("");
    searchCity.setText("");
  }

//------------------------------------------------------------------------------

  private void bind() {
    /* Editor */
    driver = GWT.create(Driver.class);
    driver.initialize(this);
  }
  
  private void createView() {
    SearchViewUiBinder uiBinder = GWT.create(SearchViewUiBinder.class);
    
    initWidget(uiBinder.createAndBindUi(this));

    searchPanel.addStyleName(style.searchPanel());
    buttonBar.addStyleName(style.searchPanelButtonBar());
    
    headline.addStyleName(style.headline());
    headline.setText(ApplicationConstants.CONSTANTS.searchHeadline());
    
    searchName.setLabel(ApplicationConstants.CONSTANTS.searchName());
    searchCity.setLabel(ApplicationConstants.CONSTANTS.searchCity());
    
    searchButton.addStyleName(style.button());
    searchButton.setText(ApplicationConstants.CONSTANTS.searchButton());
    resetButton.addStyleName(style.button());
    resetButton.setText(ApplicationConstants.CONSTANTS.resetButton());
  }
}

