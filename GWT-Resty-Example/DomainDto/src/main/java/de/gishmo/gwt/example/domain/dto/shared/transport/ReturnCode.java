package de.gishmo.gwt.example.domain.dto.shared.transport;

import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum ReturnCode {

  OK("200", "OK"),
  FACHLICHE_MELDUNGEN("300", "FACHLICHE_MELDUNGEN"),
  TECHNISCHER_FEHLER("500", "TECHNISCHER_FEHLER");

  private String statusCode;
  private String status;

  ReturnCode(final String statusCode, final String status) {
    this.statusCode = statusCode;
    this.status = status;
  }

  public String getStatusCode() {
    return statusCode;
  }

  public void setStatusCode(String statusCode) {
    this.statusCode = statusCode;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }
}
