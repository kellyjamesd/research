package com.carcosadreaming.rpg.common.data.model.jpa;

import com.carcosadreaming.rpg.common.data.model.Datum;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.hateoas.Identifiable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.UUID;

/**
 * This is to hold specific entity instance data for descriptors (abilities, skills, feats, combat, etc.)
 *
 *
 */


@Data
@NoArgsConstructor
@EqualsAndHashCode( callSuper = true )
@ToString( callSuper = true )
@Entity
@Table(name="datum" )
public class DatumJpaImpl extends AbstractEntity implements Datum {

  @Column(name = "abstractEntityId")
  private UUID entityId;

  @Column(name = "factorId")
  private UUID factorId;

  @Column(name = "datumValue")
  private String datumValue;

  public DatumJpaImpl(Identifiable<UUID> entity, Identifiable<UUID> factor, String value)
  {
    super();
    this.entityId = entity.getId();
    this.factorId = factor.getId();
    this.datumValue = value;
  }
}
