package de.gishmo.gwt.example.module0902.client.ui.detail;

import com.google.gwt.user.client.ui.IsWidget;

import de.gishmo.gwt.example.module0503.shared.dto.Person;
import de.gishmo.gwt.example.module0708.client.widgets.ReverseView;

public interface IDetailView
  extends ReverseView<IDetailView.Presenter>,
          IsWidget {

  boolean isDirty();

  void setUpData(Person result);

  public interface Presenter {

    void doRevert();

    void doUpdate(Person person);

  }
}
