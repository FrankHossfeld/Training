package de.gishmo.gwt.example.domain.dto.shared.transport.request;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import de.gishmo.gwt.example.domain.dto.shared.AbstractDto;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY)
public abstract class AbstractRequest
  extends AbstractDto {

  public AbstractRequest() {
    super();
  }

}
