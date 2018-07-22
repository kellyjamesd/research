package com.carcosadreaming.rpg.common.data.model.jpa;

import com.carcosadreaming.rpg.common.data.model.CommonEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.hateoas.Identifiable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.Table;
import java.util.UUID;

@Data
@NoArgsConstructor
@EqualsAndHashCode( callSuper = true )
@ToString( callSuper = true )
@Entity
@Table(name="datum", indexes = { @Index( columnList = "classifier, element, descriptor" ) } )
public class DatumJpaImpl extends AbstractEntity implements CommonEntity
{
  @Column(name = "abstractEntityId")
  private UUID entityId;

  @Column(name = "classifier")
  private String classifier;

  @Column(name = "element")
  private String element;

  @Column(name = "descriptor")
  private String descriptor;

  @Column(name = "datumValue")
  private String datumValue;

  public DatumJpaImpl(Identifiable<UUID> entity, String classifer, String element, String descriptor, String datumValue )
  {
    this.entityId = entity.getId();
    this.classifier = classifer;
    this.element = element;
    this.descriptor = descriptor;
    this.datumValue = datumValue;
  }
}
