package de.gishmo.gwt.example.module0806.client.widgets;

import com.google.gwt.user.client.ui.Composite;

public abstract class ReverseComposite<T>
  extends Composite {

  private T presenter;

  public ReverseComposite() {
    super();
  }

  public T getPresenter() {
    return presenter;
  }

  public void setPresenter(T presenter) {
    this.presenter = presenter;
  }
}
