package com.carcosadreaming.rpg.common.data.model.jpa;

import com.carcosadreaming.rpg.common.data.model.CommonEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.hateoas.Identifiable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.util.UUID;

@Data
@NoArgsConstructor
@EqualsAndHashCode( callSuper = true )
@ToString( callSuper = true )
@Entity
@Table(name="datum")
public class DatumImpl extends AbstractEntity implements CommonEntity
{
  @Column(name = "abstractEntityId")
  private UUID entityId;

  @ManyToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "descriptorId")
  private ClassifierElementJpaImpl descriptor;

  @Column(name = "datumValue")
  private String datumValue;

  public DatumImpl( Identifiable<UUID> entity, ClassifierElementJpaImpl descriptor, String datumValue )
  {
    this.entityId = entity.getId();
    this.descriptor = descriptor;
    this.datumValue = datumValue;
  }
}
