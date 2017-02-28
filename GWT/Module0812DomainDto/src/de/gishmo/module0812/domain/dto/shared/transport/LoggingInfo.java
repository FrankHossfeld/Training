package de.gishmo.module0812.domain.dto.shared.transport;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeName;

import de.gishmo.module0812.domain.dto.shared.AbstractDto;

@JsonTypeName("LoggingInfo")
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "@class")
public class LoggingInfo extends AbstractDto {

	public enum Loglevel {
		DEBUG, INFO, WARNING, ERROR, CRITICAL;
	}

	protected Loglevel loglevel;
	protected String message;
	protected String parameter;
	protected String methode;

	public LoggingInfo() {
	}

	public LoggingInfo(Loglevel pLoglevel, String pMethode, String pMessage, String pParameter) {
		methode = pMethode;
		message = pMessage;
		loglevel = pLoglevel;
		parameter = pParameter;
	}

	public String getMessage() {
		return message;
	}

	@Override
	public String toString() {
		StringBuffer tBuffer = new StringBuffer();

		tBuffer.append("loglevel=");
		tBuffer.append(loglevel);
		tBuffer.append(";");

		tBuffer.append("methode=");
		tBuffer.append(methode != null ? methode : "");
		tBuffer.append(";");

		tBuffer.append("message=");
		tBuffer.append(message != null ? message : "");
		tBuffer.append(";");

		tBuffer.append("parameter=");
		tBuffer.append(parameter != null ? parameter : "");
		tBuffer.append(";");

		return tBuffer.toString();
	}

	public String getParameter() {
		return parameter;
	}

	public String getMethode() {
		return methode;
	}

	public Loglevel getLoglevel() {
		return loglevel;
	}

	public void setLoglevel(Loglevel loglevel) {
		this.loglevel = loglevel;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public void setParameter(String parameter) {
		this.parameter = parameter;
	}

	public void setMethode(String methode) {
		this.methode = methode;
	}

}
