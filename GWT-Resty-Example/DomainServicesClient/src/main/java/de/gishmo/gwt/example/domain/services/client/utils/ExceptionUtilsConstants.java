package de.gishmo.gwt.example.domain.services.client.utils;

import com.google.gwt.core.client.GWT;
import com.google.gwt.i18n.client.Constants;

public interface ExceptionUtilsConstants
  extends Constants {

  ExceptionUtilsConstants INSTANCE = GWT.create(ExceptionUtilsConstants.class);

  String applicationErrorText();

  String applicationErrorMessage();

  String methodName();

  String serverErrorData();

  String serverErrorMessage();

  String serverErrorPayload();

  String serverErrorText();

  String serverMessageStatus();

  String serverMessageResponseHeader();

  String serverNoResponseDataExist();

  String serverNoRespneseHeaderInforamationExist();

  String serverTechnischerFehler();

  String notYetImplemetat();

  String applicationErrorStackTrace();

}
