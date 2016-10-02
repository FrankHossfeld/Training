package de.gishmo.module0812.gwt.client.ui.detail;

import com.google.gwt.user.client.ui.IsWidget;
import com.mvp4g.client.view.ReverseViewInterface;

import de.gishmo.module0812.domain.dto.shared.model.Person;

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
