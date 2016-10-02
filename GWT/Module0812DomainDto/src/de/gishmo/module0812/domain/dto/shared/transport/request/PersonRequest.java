package de.gishmo.module0812.domain.dto.shared.transport.request;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeName;

@SuppressWarnings("serial")
@JsonTypeName("PersonRequest")
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.EXTERNAL_PROPERTY)
public class PersonRequest extends AbstractRequest implements Serializable {

	private long id;

	public PersonRequest() {
		super();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

}
