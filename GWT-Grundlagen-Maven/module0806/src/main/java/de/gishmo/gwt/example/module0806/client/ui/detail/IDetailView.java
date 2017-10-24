package de.gishmo.gwt.example.module0809.client.ui.detail;

import com.google.gwt.user.client.ui.IsWidget;
import de.gishmo.gwt.example.module0503.shared.dto.Person;
import de.gishmo.gwt.example.module0809.client.widgets.ReverseView;

public interface IDetailView
  extends ReverseView<IDetailView.Presenter>,
          IsWidget {
  
  void edit(Person person);
  
  Person flush(Person person);
  
  boolean isDirty(Person person);
  
  public interface Presenter {

    void doRevert();

    void doUpdate();

  }
}
