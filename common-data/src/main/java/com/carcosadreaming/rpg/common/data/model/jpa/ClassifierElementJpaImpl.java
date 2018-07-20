package com.carcosadreaming.rpg.common.data.model.jpa;

import com.carcosadreaming.rpg.common.data.model.ClassifierElement;
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
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)//, exclude = {"classifier"})
@Entity
@Table(name = "descriptor")
public class ClassifierElementJpaImpl extends AbstractMorpheme implements ClassifierElement
{

  @ManyToOne//(fetch = FetchType.LAZY)
  @JoinColumn(name = "classifierId", nullable = false)
  @OnDelete( action = OnDeleteAction.CASCADE )
  @JsonIgnore
  private ClassifierJpaImpl classifier;

  public ClassifierElementJpaImpl( String name, String shortName, String description, ClassifierJpaImpl classifier )
  {
    super( classifier.getSystem(), name, shortName, description );
    this.classifier = classifier;
    setClassifier( classifier );
  }

}
