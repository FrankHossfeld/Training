package de.gishmo.gwt.example.module0711.client;


import com.google.gwt.cell.client.Cell;
import com.google.gwt.cell.client.ClickableTextCell;
import com.google.gwt.cell.client.FieldUpdater;
import com.google.gwt.cell.client.TextCell;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.*;
import de.gishmo.gwt.example.module0503.client.PersonService;
import de.gishmo.gwt.example.module0503.client.PersonServiceAsync;
import de.gishmo.gwt.example.module0503.shared.dto.Person;
import de.gishmo.gwt.example.module0503.shared.dto.PersonSearch;
import de.gishmo.gwt.example.module0707.client.widgets.TextField;
import de.gishmo.gwt.example.module0711.client.resources.ApplicationConstants;
import de.gishmo.gwt.example.module0711.client.resources.ApplicationCss;
import de.gishmo.gwt.example.module0711.client.resources.ImageResources;

import java.util.ArrayList;
import java.util.List;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class Module0711
  implements EntryPoint {

  private final static String SEARCH_FORM = "searchForm";
  private final static String RESULT_LIST = "resultList";
  // TODO
//  private final static String DETAIL_FORM = "detailForm";
  private PersonServiceAsync service = GWT.create(PersonService.class);
  /* application */
  private ApplicationCss    style;
  private DockLayoutPanel   shell;
  /* search panel */
  private ScrollPanel       searchScrollPanel;
  private FlowPanel         searchPanel;
  private TextField         searchName;
  private TextField         searchCity;
  private Button            searchButton;
  private Button            resetButton;
  /* result list */
  private ScrollPanel       resultScrollPanel;
  private FlowPanel         resultPanel;
  private CellTable<Person> resultTable;

  /**
   * This is the entry point method.
   */
  public void onModuleLoad() {
    Resources resources = GWT.create(Resources.class);
    this.style = resources.style();
    this.style.ensureInjected();

    createShell();
    createSearchForm();
    createResultList();

    RootLayoutPanel.get()
                   .add(shell);

    setCenter(Module0711.SEARCH_FORM);
  }

  private void createShell() {
    shell = new DockLayoutPanel(Unit.PX);
    shell.setSize("100%",
                  "100%");

    Widget header = createNorth();
    shell.addNorth(header,
                   128);

    Widget footer = createSouth();
    shell.addSouth(footer,
                   42);

    Widget navigation = ceateNavigation();
    shell.addWest(navigation,
                  212);
  }

  private void createSearchForm() {
    searchScrollPanel = new ScrollPanel();

    searchPanel = new FlowPanel();
    searchPanel.addStyleName(style.searchPanel());
    searchScrollPanel.add(searchPanel);

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
    searchButton.addClickHandler(new ClickHandler() {
      @Override
      public void onClick(ClickEvent event) {
        resetTable();

        service.get(new PersonSearch(searchName.getText(),
                                     searchCity.getText()),
                    new AsyncCallback<List<Person>>() {
                      @Override
                      public void onFailure(Throwable caught) {
                        Window.alert("PANIC!!!!!");
                      }

                      @Override
                      public void onSuccess(List<Person> result) {
                        resultTable.setRowData(result);
                        setCenter(Module0711.RESULT_LIST);
                      }
                    });
      }
    });

    resetButton = new Button(ApplicationConstants.CONSTANTS.resetButton());
    resetButton.addStyleName(style.button());
    buttonBar.add(resetButton);
    resetButton.addClickHandler(new ClickHandler() {
      @Override
      public void onClick(ClickEvent event) {
        searchName.setText("");
        searchCity.setText("");
      }
    });
  }

  private void createResultList() {
    resultScrollPanel = new ScrollPanel();

    resultPanel = new FlowPanel();
    resultPanel.addStyleName(style.resultPanel());
    resultScrollPanel.add(resultPanel);

    Label headline = new Label(ApplicationConstants.CONSTANTS.resultHeadline());
    headline.addStyleName(style.headline());
    resultPanel.add(headline);

    resultTable = new CellTable<Person>();
    resultPanel.add(resultTable);
    resultTable.setEmptyTableWidget(new HTML(ApplicationConstants.CONSTANTS.resultText()));
    Column<Person, String> nameColumn = addColumn(new ClickableTextCell(),
                                                  ApplicationConstants.CONSTANTS.columnName(),
                                                  new GetValue<String>() {
                                                    @Override
                                                    public String getValue(Person person) {
                                                      return person.getName() + ", " + person.getFirstName();
                                                    }
                                                  },
                                                  new FieldUpdater<Person, String>() {
                                                    @Override
                                                    public void update(int index,
                                                                       Person object,
                                                                       String value) {
                                                      Window.alert("open detail form");
                                                    }
                                                  });

    Column<Person, String> streetColumn = addColumn(new TextCell(),
                                                    ApplicationConstants.CONSTANTS.columnStreet(),
                                                    new GetValue<String>() {
                                                      @Override
                                                      public String getValue(Person person) {
                                                        return person.getAddress()
                                                                     .getStreet();
                                                      }
                                                    },
                                                    null);

    Column<Person, String> plzColumn = addColumn(new TextCell(),
                                                 ApplicationConstants.CONSTANTS.columnPlz(),
                                                 new GetValue<String>() {
                                                   @Override
                                                   public String getValue(Person person) {
                                                     return person.getAddress()
                                                                  .getZip();
                                                   }
                                                 },
                                                 null);
    plzColumn.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_RIGHT);

    Column<Person, String> cityColumn = addColumn(new TextCell(),
                                                  ApplicationConstants.CONSTANTS.columnCity(),
                                                  new GetValue<String>() {
                                                    @Override
                                                    public String getValue(Person person) {
                                                      return person.getAddress()
                                                                   .getCity();
                                                    }
                                                  },
                                                  null);

    // Tabellen und Spalten-Breite setzen
    resultTable.setWidth("100%");
    resultTable.setColumnWidth(nameColumn,
                               "40%");
    resultTable.setColumnWidth(streetColumn,
                               "25%");
    resultTable.setColumnWidth(plzColumn,
                               "10%");
    resultTable.setColumnWidth(cityColumn,
                               "25%");

    resetTable();
  }

  private void setCenter(String requestedWidet) {
    if (searchScrollPanel.getParent() != null) {
      searchScrollPanel.removeFromParent();
    }
    if (resultScrollPanel.getParent() != null) {
      resultScrollPanel.removeFromParent();
    }

    if (requestedWidet.equals(Module0711.SEARCH_FORM)) {
      shell.add(searchScrollPanel);
    } else if (requestedWidet.equals(Module0711.RESULT_LIST)) {
      shell.add(resultScrollPanel);
    }
  }

  private Widget createNorth() {
    FlowPanel panel = new FlowPanel();
    panel.addStyleName(style.headerPanel());

    Image image = new Image(ImageResources.INSTANCE.gwtLogo());
    image.addStyleName(style.header());
    panel.add(image);

    return panel;
  }

  private Widget createSouth() {
    FlowPanel panel = new FlowPanel();
    panel.addStyleName(style.footerPanel());

    Label label = new Label(ApplicationConstants.CONSTANTS.footerText());
    label.addStyleName(style.footerLabel());
    panel.add(label);

    return panel;
  }

  private Widget ceateNavigation() {
    FlowPanel panel = new FlowPanel();
    panel.addStyleName(style.navigationPanel());

    Button searchButton = new Button(ApplicationConstants.CONSTANTS.searchFormButton());
    searchButton.addStyleName(style.navigationButton());
    searchButton.addClickHandler(new ClickHandler() {
      @Override
      public void onClick(ClickEvent event) {
        setCenter(Module0711.SEARCH_FORM);
      }
    });
    panel.add(searchButton);

    Button listButton = new Button(ApplicationConstants.CONSTANTS.listFormButton());
    listButton.addStyleName(style.navigationButton());
    listButton.addClickHandler(new ClickHandler() {
      @Override
      public void onClick(ClickEvent event) {
        setCenter(Module0711.RESULT_LIST);
      }
    });
    panel.add(listButton);

    return panel;
  }

  private void resetTable() {
    // Row-Count zurück setzen
    resultTable.setRowCount(0,
                            true);
    // Mit leerer Liste fuellen ... Brauch man das wirklich ... ?????
    resultTable.setRowData(0,
                           new ArrayList<Person>());
  }

  /**
   * Add a column with a header.
   *
   * @param <C>        the cell type
   * @param cell       the cell used to render the column
   * @param headerText the header string
   * @param getter     the value getter for the cell
   */
  private <C> Column<Person, C> addColumn(Cell<C> cell,
                                          String headerText,
                                          final GetValue<C> getter,
                                          FieldUpdater<Person, C> fieldUpdater) {
    Column<Person, C> column = new Column<Person, C>(cell) {
      @Override
      public C getValue(Person object) {
        return getter.getValue(object);
      }
    };
    column.setFieldUpdater(fieldUpdater);
    resultTable.addColumn(column,
                          headerText);
    return column;
  }

//------------------------------------------------------------------------------

  public interface Resources
    extends ClientBundle {

    @Source("resources/ApplicationCss.css")
    ApplicationCss style();

  }

  /**
   * Get a cell value from a record.
   *
   * @param <C> the cell type
   */
  private interface GetValue<C> {
    C getValue(Person person);
  }
}
