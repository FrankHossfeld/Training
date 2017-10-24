package de.gishmo.gwt.example.module0801.client.ui.search;

import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.Widget;
import de.gishmo.gwt.example.module0503.shared.dto.PersonSearch;
import de.gishmo.gwt.example.module0801.client.ClientContext;

public class SearchPresenter
  implements ISearchView.Presenter,
             IsWidget {

  private ClientContext clientContext;
  private ISearchView   view;

//------------------------------------------------------------------------------

  public SearchPresenter(ClientContext clientContext) {
    this.clientContext = clientContext;

    view = new SearchView(this.clientContext.getStyle());
    view.setPresenter(this);

  }

//------------------------------------------------------------------------------

  @Override
  public Widget asWidget() {
    return view.asWidget();
  }

  @Override
  public void doClickSearchButton(String searchName,
                                  String searchCity) {
    clientContext.setPersonSearch(new PersonSearch(searchName,
                                                   searchCity));

    clientContext.getShellPresenter()
                 .setCenter(ClientContext.RESULT_LIST);
  }
}
