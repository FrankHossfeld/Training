package de.gishmo.gwt.example.module0910.client.history;

import com.mvp4g.client.annotation.History;
import com.mvp4g.client.annotation.History.HistoryConverterType;
import com.mvp4g.client.history.HistoryConverter;
import de.gishmo.gwt.example.module0910.client.model.ClientContext;
import de.gishmo.gwt.example.module0910.client.module0910EventBus;

import javax.inject.Inject;

@History(type = HistoryConverterType.SIMPLE)
public class DefaultHistoryConverter
  implements HistoryConverter<module0910EventBus> {

  private final static String DELIMITER = "+!!+";

  @Inject
  private ClientContext clientContext;

  public DefaultHistoryConverter() {
  }

  @Override
  public void convertFromToken(String historyName,
                               String param,
                               module0910EventBus eventBus) {

    if ("gotoSearch".equals(historyName)) {
      String searchName = "";
      String searchCity = "";
      if (param.length() > 0) {
        searchName = param.substring(0,
                                     param.indexOf(DELIMITER));
        if (param.length() > param.indexOf(DELIMITER) + DELIMITER.length()) {
          searchCity = param.substring(param.indexOf(DELIMITER) + DELIMITER.length());
        }
      }
      eventBus.gotoSearch(searchName,
                          searchCity);
    } else if ("gotoList".equals(historyName)) {
      String searchName = "";
      String searchCity = "";
      if (param.length() > 0) {
        searchName = param.substring(0,
                                     param.indexOf(DELIMITER));
        if (param.length() > param.indexOf(DELIMITER) + DELIMITER.length()) {
          searchCity = param.substring(param.indexOf(DELIMITER) + DELIMITER.length());
        }
      }
      if (searchName.length() > 0 || searchCity.length() > 0) {
        eventBus.gotoList(searchName,
                          searchCity);
      } else {
        eventBus.gotoSearch("",
                            "");
      }
    } else if ("gotoDetail".equals(historyName)) {
      try {
        long id = Long.parseLong(param);
        eventBus.gotoDetail(id);
      } catch (NumberFormatException e) {
        if (clientContext.getPersonSearch() != null) {
          eventBus.gotoSearch(clientContext.getPersonSearch()
                                           .getName(),
                              clientContext.getPersonSearch()
                                           .getCity());
        } else {
          eventBus.gotoSearch("",
                              "");
        }
      }
    } else {
      // Defaultwerte setzen ....
      eventBus.gotoSearch("",
                          "");
    }
  }

  @Override
  public boolean isCrawlable() {
    // we don't want to be crawled
    return false;
  }

  public String convertToToken(String historyName,
                               long id) {
    return Long.toString(id);
  }

  public String convertToToken(String historyName) {
    return "";
  }

  public String convertToToken(String historyName,
                               String param1,
                               String param2) {
    return param1 + DELIMITER + param2;
  }
}
