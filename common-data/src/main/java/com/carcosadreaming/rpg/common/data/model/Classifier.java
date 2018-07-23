package com.carcosadreaming.rpg.common.data.model;

public interface Classifier extends Morpheme
{
  boolean loadAll();

  void setLoadAllElements(boolean loadAllElements);

  String getPluralName();

  void setPluralName(String pluralName);
}
