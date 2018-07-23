package com.carcosadreaming.rpg.common.data.model.jpa;

import com.carcosadreaming.rpg.common.data.model.Descriptor;
import com.carcosadreaming.rpg.common.data.model.Qualifier;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.UUID;

/**
 * This holds the Source and Effect for Class abilities, Racial Traits, Feats, etc.
 *
 */

@Data
@NoArgsConstructor
@EqualsAndHashCode( callSuper = true )
@ToString( callSuper = true )
@Entity
@Table(name="qualifier" )
public class QualifierJpaImpl extends AbstractMorpheme implements Qualifier
{

  @Column(name = "descriptorId")
  private UUID descriptorId;

  public QualifierJpaImpl(String name, String shortName, String description, Descriptor descriptor)

  {
    super(descriptor.getClassifierElement().getSystem(), name, shortName, description);
    this.descriptorId = descriptor.getId();
  }

}
