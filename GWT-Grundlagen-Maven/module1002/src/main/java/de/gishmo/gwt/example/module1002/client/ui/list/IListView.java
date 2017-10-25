package de.gishmo.gwt.example.module1002.client.ui.list;

import java.util.List;

import com.google.gwt.user.client.ui.IsWidget;

import de.gishmo.gwt.example.module0503.shared.dto.Person;
import de.gishmo.gwt.example.module0708.client.widgets.ReverseView;

public interface IListView
  extends ReverseView<IListView.Presenter>,
          IsWidget {
  
  void resetTable();
  
  void setData(List<Person> result);

  public interface Presenter {

    void doUpdate(Person object);

  }
}
