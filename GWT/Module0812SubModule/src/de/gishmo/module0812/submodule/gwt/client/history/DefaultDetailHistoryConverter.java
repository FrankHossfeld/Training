package de.gishmo.module0812.submodule.gwt.client.history;

import javax.inject.Inject;

import com.mvp4g.client.annotation.History;
import com.mvp4g.client.annotation.History.HistoryConverterType;
import com.mvp4g.client.history.HistoryConverter;

import de.gishmo.module0812.common.gwt.client.model.ClientContext;
import de.gishmo.module0812.submodule.gwt.client.Module0812SubModuleEventBus;

@History(type = HistoryConverterType.SIMPLE)
public class DefaultDetailHistoryConverter
implements HistoryConverter<Module0812SubModuleEventBus> {

   @Inject
   private ClientContext clientContext;

   public DefaultDetailHistoryConverter() {}

   @Override
   public void convertFromToken(final String historyName,
                                final String param,
                                final Module0812SubModuleEventBus eventBus) {

      if ("gotoDetail".equals(historyName)) {
         try {
            final long id = Long.parseLong(param);
            eventBus.gotoDetail(id);
         }
         catch (final NumberFormatException e) {
            if (clientContext.getPersonSearch() != null) {
               eventBus.gotoSearch(clientContext.getPersonSearch().getName(),
                                   clientContext.getPersonSearch().getCity());
            }
            else {
               eventBus.gotoSearch("", "");
            }
         }
      }
      else {
         // Defaultwerte setzen ....
         eventBus.gotoSearch("", "");
      }
   }

   @Override
   public boolean isCrawlable() {
      // we don't want to be crawled
      return false;
   }

   public String convertToToken(final String historyName,
                                final long id) {
      return Long.toString(id);
   }
}
