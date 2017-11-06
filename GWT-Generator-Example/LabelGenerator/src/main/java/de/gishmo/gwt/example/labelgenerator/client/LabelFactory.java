package de.gishmo.gwt.example.labelgenerator.client;

import java.util.HashMap;
import java.util.Map;

public class LabelFactory {

  private static LabelFactory instance;

  private Map<String, String> labelMap;

  private LabelFactory() {
    labelMap = new HashMap<>();

    initFactory();
  }

  private void initFactory() {
    labelMap.put("searchViewName",
                 "zu suchender Name 123");
    labelMap.put("searchViewCity",
                 "zu suchender Ort 123");
    labelMap.put("labelKey03",
                 "GWT-Schulung Extended");
  }

  public static LabelFactory get() {
    if (instance == null) {
      instance = new LabelFactory();
    }
    return instance;
  }

  public String getLabel(String id) {
    return labelMap.get(id);
  }
}
