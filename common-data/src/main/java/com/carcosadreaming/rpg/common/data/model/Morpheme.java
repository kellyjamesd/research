package com.carcosadreaming.rpg.common.data.model;

public interface Morpheme extends CommonEntity
{
  String getName();

  String getShortName();

  String getDescription();

  void setName( String name );

  void setShortName( String shortName );

  void setDescription( String description );
}
