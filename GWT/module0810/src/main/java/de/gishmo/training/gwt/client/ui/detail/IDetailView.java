package de.gishmo.training.gwt.client.ui.detail;

import com.google.gwt.user.client.ui.IsWidget;
import com.mvp4g.client.view.ReverseViewInterface;

import de.gishmo.module0503.shared.dto.Person;

public interface IDetailView
  extends ReverseViewInterface<IDetailView.Presenter>,
          IsWidget {

  boolean isDirty();

  void setUpData(Person result);

  public interface Presenter {

    void doRevert();

    void doUpdate(Person person);

  }
}
