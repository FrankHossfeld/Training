package de.gishmo.gwt.example.module0806.client.ui.detail;

import com.google.gwt.user.client.ui.IsWidget;
import de.gishmo.gwt.example.module0503.shared.dto.Person;
import de.gishmo.gwt.example.module0806.client.widgets.ReverseView;

public interface IDetailView
  extends ReverseView<IDetailView.Presenter>,
          IsWidget {
  
  void edit(Person person);
  
  Person flush(Person person);
  
  boolean isDirty(Person person);
  
  interface Presenter {

    void doRevert();

    void doUpdate();

  }
}
