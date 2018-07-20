package com.carcosadreaming.rpg.common.data.model.jpa;

import com.carcosadreaming.rpg.common.data.model.Descriptor;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Data
@NoArgsConstructor
@EqualsAndHashCode( callSuper = true )
@ToString( callSuper = true )
@Entity
@Table(name="datum")
public class DescriptorJpaImpl extends AbstractMorpheme implements Descriptor
{

  @ManyToOne
  @JoinColumn(name = "classifierElementId", nullable = false)
  @OnDelete( action = OnDeleteAction.CASCADE )
  @JsonIgnore
  private ClassifierElementJpaImpl classifierElement;

  public DescriptorJpaImpl( String name, String shortName, String description, ClassifierElementJpaImpl classifierElement )
  {
    super(classifierElement.getSystem(), name, shortName, description);
    this.classifierElement = classifierElement;
  }
}
