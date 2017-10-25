package de.gishmo.gwt.example.module0404.shared.actions;

import de.gishmo.gwt.example.module0404.shared.results.GetPersonResult;
import net.customware.gwt.dispatch.shared.Action;

public class GetPersonAction
  implements Action<GetPersonResult> {
  
  private long id;
  
  /* for serialization only */
  @SuppressWarnings("unused")
  private GetPersonAction() {
  }

  public GetPersonAction(long id) {
    this.id = id;
  }

  public long getId() {
    return id;
  }
}
