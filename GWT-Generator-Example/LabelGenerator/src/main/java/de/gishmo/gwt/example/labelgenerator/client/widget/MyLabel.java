package de.gishmo.gwt.example.labelgenerator.client.widget;

import com.google.gwt.user.client.ui.Label;
import de.gishmo.gwt.example.labelgenerator.client.HasLabel;

public class MyLabel
  extends Label
  implements HasLabel {

  @Override
  public void setLabel(String label) {
    super.setText(label);
  }

}
