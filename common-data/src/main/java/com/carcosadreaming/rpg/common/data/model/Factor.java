package com.carcosadreaming.rpg.common.data.model;

public interface Factor extends Morpheme
{
  java.util.UUID getQualifierId();

  java.util.UUID getDescriptorId();

  void setQualifierId( java.util.UUID qualifierId );

  void setDescriptorId( java.util.UUID descriptorId );
}
