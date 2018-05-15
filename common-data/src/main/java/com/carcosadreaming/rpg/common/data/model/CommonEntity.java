package com.carcosadreaming.rpg.common.data.model;

import org.springframework.hateoas.Identifiable;

import java.util.UUID;

public interface CommonEntity extends Identifiable<UUID>
{
  boolean equals( Object o );

  int hashCode();

  String toString();

  UUID getId();

  java.time.ZonedDateTime getCreationDate();

  java.time.ZonedDateTime getModifiedDate();

  String getCreatedBy();

  String getModifiedBy();

  void setCreatedBy( String createdBy );

  void setModifiedBy( String modifiedBy );
}
