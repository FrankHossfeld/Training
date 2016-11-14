package de.gishmo.module0812.common.gwt.client.widget;


import com.google.gwt.user.client.ui.Composite;
import com.mvp4g.client.view.ReverseViewInterface;

/**
 * <p>ReverseComposite</p>
 * <p>Abstrakte Super-Klasse eines Views, der Composite erweitert. 
 * Unterstuetzt das Handling des View-Delegate-Pattern.</p>
 * <br><br>
 */
public abstract class ReverseComposite<P>
    extends Composite
    implements ReverseViewInterface<P> {

  private P presenter;

//------------------------------------------------------------------------------

  public ReverseComposite() {
    super();
  }

//------------------------------------------------------------------------------


  public P getPresenter() {
    return presenter;
  }

  public void setPresenter(P presenter) {
    this.presenter = presenter;
  }
}
