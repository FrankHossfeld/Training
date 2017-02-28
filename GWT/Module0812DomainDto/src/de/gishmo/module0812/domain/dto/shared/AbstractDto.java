package de.gishmo.module0812.domain.dto.shared;

import com.fasterxml.jackson.annotation.JsonSubTypes;

import de.gishmo.module0812.domain.dto.shared.model.Address;
import de.gishmo.module0812.domain.dto.shared.model.Person;
import de.gishmo.module0812.domain.dto.shared.search.PersonSearch;
import de.gishmo.module0812.domain.dto.shared.transport.ExceptionInfo;
import de.gishmo.module0812.domain.dto.shared.transport.LoggingInfo;
import de.gishmo.module0812.domain.dto.shared.transport.Status;
import de.gishmo.module0812.domain.dto.shared.transport.response.PersonGetResponse;
import de.gishmo.module0812.domain.dto.shared.transport.response.PersonInsertResponse;
import de.gishmo.module0812.domain.dto.shared.transport.response.PersonSearchResponse;
import de.gishmo.module0812.domain.dto.shared.transport.response.PersonUpdateResponse;

@JsonSubTypes({ @JsonSubTypes.Type(value = Address.class,
			         			   name = "Address"),
                @JsonSubTypes.Type(value = ExceptionInfo.class,
                       name = "ExceptionInfo"),
                @JsonSubTypes.Type(value = LoggingInfo.class,
                       name = "LoggingInfo"),
                @JsonSubTypes.Type(value = PersonGetResponse.class,
                       name = "PersonGetResponse"),
        @JsonSubTypes.Type(value = PersonInsertResponse.class,
                           name = "PersonInsertResponse"),
        @JsonSubTypes.Type(value = PersonSearchResponse.class,
                           name = "PersonSearchResponse"),
        @JsonSubTypes.Type(value = PersonUpdateResponse.class,
                           name = "PersonUpdateResponse"),
				@JsonSubTypes.Type(value = Person.class,
								   name = "Person"),
				@JsonSubTypes.Type(value = PersonSearch.class,
								   name = "PersonSearch"),
				@JsonSubTypes.Type(value = Status.class,
				   				   name = "Status") })
public abstract class AbstractDto {
	
	private String uuid;
	
	public AbstractDto() {
		super();
		this.uuid = GUID.get();
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
}
