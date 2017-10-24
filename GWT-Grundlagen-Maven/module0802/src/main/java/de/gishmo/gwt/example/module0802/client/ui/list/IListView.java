package de.gishmo.gwt.example.module0802.client.ui.list;

import com.google.gwt.user.client.ui.IsWidget;
import de.gishmo.gwt.example.module0503.shared.dto.Person;
import de.gishmo.gwt.example.module0802.client.widgets.ReverseView;

import java.util.List;

public interface IListView
  extends ReverseView<IListView.Presenter>,
          IsWidget {
  
  void resetTable();
  
  void setData(List<Person> result);

  public interface Presenter {

    void doUpdate(Person object);

  }
}
