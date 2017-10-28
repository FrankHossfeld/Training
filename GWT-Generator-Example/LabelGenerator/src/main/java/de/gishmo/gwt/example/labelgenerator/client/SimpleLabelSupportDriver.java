package de.gishmo.gwt.example.labelgenerator.client;

public interface SimpleLabelSupportDriver<V extends LabelProvider> {

  void initialize(V labelProvider);

  void initializeAndLoad(V labelProvider);

}
