package de.gishmo.module0812.gwt.client.ui.list;

import java.util.List;

import com.google.gwt.user.client.ui.IsWidget;
import com.mvp4g.client.view.ReverseViewInterface;

import de.gishmo.module0812.domain.dto.shared.model.Person;

public interface IListView
  extends ReverseViewInterface<IListView.Presenter>,
          IsWidget {
  
  void resetTable();
  
  void setData(List<Person> result);

  public interface Presenter {

    void doUpdate(Person object);

  }
}
