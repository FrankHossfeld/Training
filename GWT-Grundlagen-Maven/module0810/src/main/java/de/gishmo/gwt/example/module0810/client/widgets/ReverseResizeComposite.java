package de.gishmo.gwt.example.module0810.client.widgets;


import com.google.gwt.user.client.ui.ResizeComposite;
import com.mvp4g.client.view.ReverseViewInterface;

/**
 * <p>ReverseComposite</p>
 * <p>Abstrakte Super-Klasse eines Views, der ResizeComposite erweitert. 
 * Unterstuetzt das Handling des View-Delegate-Pattern.</p>
 * <br><br>
 */
public abstract class ReverseResizeComposite<P>
  extends ResizeComposite
  implements ReverseViewInterface<P> {

  private P presenter;

//------------------------------------------------------------------------------

  public ReverseResizeComposite() {
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
