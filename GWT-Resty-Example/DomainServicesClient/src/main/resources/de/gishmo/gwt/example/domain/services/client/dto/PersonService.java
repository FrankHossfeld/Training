package de.gishmo.module0812.domain.service.client.dto;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import org.fusesource.restygwt.client.MethodCallback;
import org.fusesource.restygwt.client.Options;
import org.fusesource.restygwt.client.RestService;

import de.gishmo.module0812.domain.dto.shared.model.Person;
import de.gishmo.module0812.domain.dto.shared.search.PersonSearch;
import de.gishmo.module0812.domain.dto.shared.transport.response.PersonGetResponse;
import de.gishmo.module0812.domain.dto.shared.transport.response.PersonInsertResponse;
import de.gishmo.module0812.domain.dto.shared.transport.response.PersonSearchResponse;
import de.gishmo.module0812.domain.dto.shared.transport.response.PersonUpdateResponse;

/**
 * der PersonService als RestyGWT-Service mit JAX-RS-Annotationen
 *
 */
@Path("/Person")
public interface PersonService extends RestService {

  @GET
  @Path("/get/{id}")
  void get(@PathParam("id") String id, MethodCallback<PersonGetResponse> callback);

  @POST
  @Path("/search")
  void search(PersonSearch search, MethodCallback<PersonSearchResponse> callback);

  @POST
  @Path("/insert")
  void insert(Person person, MethodCallback<PersonInsertResponse> callback);

  @POST
  @Options(timeout = 5000, expect = { 200, 201 })
  @Path("/update")
  void update(Person person, MethodCallback<PersonUpdateResponse> callback);

}
