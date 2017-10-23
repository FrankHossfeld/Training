package de.gishmo.gwt.example.module0402.server;


import com.google.web.bindery.autobean.shared.AutoBean;
import com.google.web.bindery.autobean.shared.AutoBeanCodex;
import com.google.web.bindery.autobean.shared.AutoBeanFactory;
import com.google.web.bindery.autobean.shared.AutoBeanUtils;
import com.google.web.bindery.autobean.vm.AutoBeanFactorySource;
import de.gishmo.gwt.example.module0402.shared.dto.Address;
import de.gishmo.gwt.example.module0402.shared.dto.Person;
import de.gishmo.gwt.example.module0402.shared.dto.PersonList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

@SuppressWarnings("serial")
public class JsonPersonService
  extends HttpServlet {

  private static Map<Long, Person> persons;
  private MyBeanFactory myBeanFactory = AutoBeanFactorySource.create(MyBeanFactory.class);

  @Override
  protected void doGet(HttpServletRequest req,
                       HttpServletResponse resp)
    throws ServletException, IOException {

    String fc = req.getParameter("fc");
    if (fc != null) {
      if (persons == null) {
        persons = new HashMap<Long, Person>();
        initList();
      }
      if (fc.equals("01")) {
        handleFc01(req,
                   resp);
      } else if (fc.equals("02")) {
        handleFc02(resp);
      } else {
        resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
      }
    } else {
      resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
    }
  }

  private void initList() {
    Address address01 = this.createAddress();
    address01.setId(1);
    address01.setStreet("Evergreen Terrace");
    address01.setZip("7 42");
    address01.setCity("Springfield");

    Address address02 = this.createAddress();
    address01.setId(2);
    address01.setStreet("Blumenweg Nr. 13");
    address01.setZip("");
    address01.setCity("Entenhausen");

    Address address03 = this.createAddress();
    address01.setId(3);
    address01.setStreet("Am Goldberg Nr. 1");
    address01.setZip("");
    address01.setCity("Entenhausen");

    Person person01 = this.createPerson();
    person01.setId(1);
    person01.setFirstName("Homer");
    person01.setName("Simpson");
    person01.setAdress(address01);

    Person person02 = this.createPerson();
    person02.setId(2);
    person02.setFirstName("Marge");
    person02.setName("Simpson");
    person02.setAdress(address01);

    Person person03 = this.createPerson();
    person03.setId(3);
    person03.setFirstName("Bart");
    person03.setName("Simpson");
    person03.setAdress(address01);

    Person person04 = this.createPerson();
    person04.setId(4);
    person04.setFirstName("Lisa");
    person04.setName("Simpson");
    person04.setAdress(address01);

    Person person05 = this.createPerson();
    person05.setId(5);
    person05.setFirstName("Maggie");
    person05.setName("Simpson");
    person05.setAdress(address01);

    Person person06 = this.createPerson();
    person06.setId(6);
    person06.setFirstName("Donald");
    person06.setName("Duck");
    person06.setAdress(address02);

    Person person07 = this.createPerson();
    person07.setId(7);
    person07.setFirstName("Trick");
    person07.setName("Duck");
    person07.setAdress(address02);

    Person person08 = this.createPerson();
    person08.setId(8);
    person08.setFirstName("Tick");
    person08.setName("Duck");
    person08.setAdress(address02);

    Person person09 = this.createPerson();
    person09.setId(9);
    person09.setFirstName("Tack");
    person09.setName("Duck");
    person09.setAdress(address02);

    Person person10 = this.createPerson();
    person10.setId(10);
    person10.setFirstName("Dagobert");
    person10.setName("Duck");
    person10.setAdress(address03);

    persons.put(new Long(1),
                person01);
    persons.put(new Long(2),
                person02);
    persons.put(new Long(3),
                person03);
    persons.put(new Long(4),
                person04);
    persons.put(new Long(5),
                person05);
    persons.put(new Long(6),
                person06);
    persons.put(new Long(7),
                person07);
    persons.put(new Long(8),
                person08);
    persons.put(new Long(9),
                person09);
    persons.put(new Long(10),
                person10);
  }

  private void handleFc01(HttpServletRequest request,
                          HttpServletResponse response)
    throws IOException {
    String id = request.getParameter("id");
    if (id != null) {
      try {
        if (persons.containsKey(new Long(id))) {
          PrintWriter out = response.getWriter();
          out.print(this.serializePersonToJSON(persons.get(new Long(id))));
          out.flush();
        }
      } catch (NumberFormatException e) {
        response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
      }
    } else {
      response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
    }
  }

  private void handleFc02(HttpServletResponse response)
    throws IOException {
    PrintWriter out = response.getWriter();
    out.print(this.serializePersonListToJSON());
    out.flush();
  }

  private Address createAddress() {
    AutoBean<Address> person = myBeanFactory.address();
    return person.as();
  }

  private Person createPerson() {
    AutoBean<Person> person = myBeanFactory.person();
    return person.as();
  }

  private String serializePersonToJSON(Person person) {
    AutoBean<Person> bean = AutoBeanUtils.getAutoBean(person);
    return AutoBeanCodex.encode(bean)
                        .getPayload();
  }

  private String serializePersonListToJSON() {
    List<Person> list = new ArrayList<Person>();
    Iterator<Long> iterator = persons.keySet()
                                     .iterator();
    while (iterator.hasNext()) {
      list.add(persons.get(iterator.next()));
    }
    PersonList personList = createPersonList();
    personList.setPersons(list);
    return serializePersonListToJSON(personList);
  }

  private PersonList createPersonList() {
    AutoBean<PersonList> personList = myBeanFactory.personList();
    return personList.as();
  }

  private String serializePersonListToJSON(PersonList personList) {
    AutoBean<PersonList> bean = AutoBeanUtils.getAutoBean(personList);
    return AutoBeanCodex.encode(bean)
                        .getPayload();
  }

  interface MyBeanFactory
    extends AutoBeanFactory {
    AutoBean<Address> address();

    AutoBean<Person> person();

    AutoBean<PersonList> personList();
  }
}