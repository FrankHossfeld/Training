package de.gishmo.gwt.example.module0910.client.ui.list;

import com.google.gwt.user.client.ui.IsWidget;
import com.mvp4g.client.view.ReverseViewInterface;
import de.gishmo.gwt.example.module0503.shared.dto.Person;

import java.util.List;

public interface IListView
  extends ReverseViewInterface<IListView.Presenter>,
          IsWidget {

  void resetTable();

  void setData(List<Person> result);

  public interface Presenter {

    void doUpdate(Person object);

  }
}
