package com.carcosadreaming.rpg.common.data.model.jpa;

import com.carcosadreaming.rpg.common.data.model.ClassifierElement;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)//, exclude = {"classifier"})
@Entity
@Table(name = "descriptor")
public class ClassifierElementJpaImpl extends AbstractMorpheme implements ClassifierElement
{

  @Column(name = "loadOrder")
  private Integer order;

  @ManyToOne//(fetch = FetchType.LAZY)
  @JoinColumn(name = "classifierId", nullable = false)
  @OnDelete( action = OnDeleteAction.CASCADE )
  @JsonIgnore
  private ClassifierJpaImpl classifier;

  public ClassifierElementJpaImpl( String name, String shortName, String description, Integer order, ClassifierJpaImpl classifier )
  {
    super( classifier.getSystem(), name, shortName, description );
    this.order = order;
    this.classifier = classifier;
    setClassifier( classifier );
  }

}
