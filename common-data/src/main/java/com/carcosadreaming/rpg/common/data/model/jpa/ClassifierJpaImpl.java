package com.carcosadreaming.rpg.common.data.model.jpa;

import com.carcosadreaming.rpg.common.data.model.Classifier;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Data
@NoArgsConstructor
@EqualsAndHashCode( callSuper = true )
@ToString( callSuper = true )
@Entity
@Table(name="classifier", uniqueConstraints = { @UniqueConstraint ( columnNames = {"system", "name"})} )
public class ClassifierJpaImpl extends AbstractMorpheme implements Classifier
{
  @Column(name="pluralName")
  private String pluralName;

  public ClassifierJpaImpl( String system, String name, String shortName, String description, String pluralName )
  {
    super( system, name, shortName, description );
    this.pluralName = pluralName;
  }
}
