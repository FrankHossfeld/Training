package de.gishmo.gwt.example.domain.dto.shared.transport;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeName;
import de.gishmo.gwt.example.domain.dto.shared.AbstractDto;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>Status eines Server-Calls</p>
 * <p>
 * Der Status eines Servercalls beschreibt das Ergebnis des Server-Calls.
 * <ul>
 * <li>{@link ReturnCode}.OK beschreibt einen erfolgreichen Call des Servers</li>
 * <li>{@link ReturnCode}.FACHLICHE_MELDUNGEN beschreibt einen erfolgreichen Call bei dessen Verarbeitungen fachliche Meldungen ermittelt wurden.</li>
 * <li>{@link ReturnCode}.TECHNISCHER_FEHLER beschreibt einen nicht erfolgreichen Call des Servers</li>
 * </ul>
 * </p>
 * <p>
 * Bei einem {@link ReturnCode}.TECHNISCHER_FEHLER war die Verarbeitung nicht erfolgreich.
 * Im Normalfall wird die Verarbeitung im Client bei diesem Status beendet.
 * <br/><br/>
 * Bei einem {@link ReturnCode}.FACHLICHE_MELDUNGEN war die Verarbeitung erfolgreich, es sind allerdings Meldungen aufgetreten.
 * Im Normalfall wird die Verarbeitung im Client bei diesem Status zu einer Anzeige der Meldungen fuehren.
 * </p>
 */

@JsonTypeName("Status")
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.EXTERNAL_PROPERTY)
@XmlRootElement
public class Status
  extends AbstractDto {

  /* Returncode des Calls */
  private ReturnCode   returncode;
  /* Liste der fachlichen Meldungen */
  private List<String> meldungenFachlich;
  /* Technische Fehlermeildung */
  private String       meldungTechnisch;

  public Status() {
    super();
    this.returncode = ReturnCode.OK;
    this.meldungenFachlich = new ArrayList<>();
    this.meldungTechnisch = null;
  }

  public ReturnCode getReturncode() {
    return returncode;
  }

  public void setReturncode(ReturnCode returncode) {
    this.returncode = returncode;
  }

  public void add(String meldungFachlich) {
    this.meldungenFachlich.add(meldungFachlich);
  }

  public List<String> getMeldungenFachlich() {
    return meldungenFachlich;
  }

  public void setMeldungenFachlich(List<String> meldungenFachlich) {
    this.meldungenFachlich = meldungenFachlich;
  }

  public String getMeldungTechnisch() {
    return meldungTechnisch;
  }

  /**
   * <p>Setzen der technischen Fehlermeldung.</p>
   * <p>Durch das Setzen der Meldung wir der Status auf {@link ReturnCode}.TECHNISCHER_FEHLER gesetzt.</p>
   *
   * @param meldungTechnisch technische Fehlermeldung
   */
  public void setMeldungTechnisch(String meldungTechnisch) {
    if (meldungTechnisch != null && !meldungTechnisch.trim()
                                                     .isEmpty()) {
      this.returncode = ReturnCode.TECHNISCHER_FEHLER;
    }
    this.meldungTechnisch = meldungTechnisch;
  }
}
