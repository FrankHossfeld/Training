package de.gishmo.gwt.example.module0802.client;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.resources.client.ClientBundle;
import de.gishmo.gwt.example.module0503.client.PersonService;
import de.gishmo.gwt.example.module0503.client.PersonServiceAsync;
import de.gishmo.gwt.example.module0503.shared.dto.PersonSearch;
import de.gishmo.gwt.example.module0802.client.resources.ApplicationCss;
import de.gishmo.gwt.example.module0802.client.ui.detail.DetailPresenter;
import de.gishmo.gwt.example.module0802.client.ui.list.ListPresenter;
import de.gishmo.gwt.example.module0802.client.ui.navigation.NavigationPresenter;
import de.gishmo.gwt.example.module0802.client.ui.search.SearchPresenter;
import de.gishmo.gwt.example.module0802.client.ui.shell.ShellPresenter;

public class ClientContext {

  public final static String SEARCH_FORM = "searchForm";
  public final static String RESULT_LIST = "resultList";
  public final static String DETAIL_FORM = "detailForm";

  private PersonServiceAsync service = GWT.create(PersonService.class);
  
  public interface Resources
    extends ClientBundle {
    
    @Source("resources/ApplicationCss.css")
    ApplicationCss style();
    
  }
  
  /* application */
  private ApplicationCss style;

  /* views */
  private ShellPresenter      shellPresenter;
  private NavigationPresenter navigationPresenter;
  private SearchPresenter     searchPresenter;
  private ListPresenter       listPresenter;
  private DetailPresenter     detailPresenter;

  
  /* search object */
  private PersonSearch personSearch;
  
//------------------------------------------------------------------------------
  
  public ClientContext() {
    Resources resources = GWT.create(Resources.class);
    this.style = resources.style();
    this.style.ensureInjected();

    shellPresenter = new ShellPresenter(this);
    navigationPresenter = new NavigationPresenter(this);
    searchPresenter = new SearchPresenter(this);
    listPresenter = new ListPresenter(this);
    detailPresenter = new DetailPresenter(this);
  }

//------------------------------------------------------------------------------
  
  public ApplicationCss getStyle() {
    return style;
  }

  public ShellPresenter getShellPresenter() {
    return shellPresenter;
  }

  public NavigationPresenter getNavigationPresenter() {
    return navigationPresenter;
  }

  public SearchPresenter getSearchPresenter() {
    return searchPresenter;
  }

  public PersonServiceAsync getPersonService() {
    return service;
  }

  public PersonSearch getPersonSearch() {
    return personSearch;
  }

  public void setPersonSearch(PersonSearch personSearch) {
    this.personSearch = personSearch;
  }

  public ListPresenter getListPresenter() {
    return listPresenter;
  }

  public DetailPresenter getDetailPresenter() {
    return detailPresenter;
  }
}
