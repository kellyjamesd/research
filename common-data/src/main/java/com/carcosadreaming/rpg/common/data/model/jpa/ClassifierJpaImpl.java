package com.carcosadreaming.rpg.common.data.model.jpa;

import com.carcosadreaming.rpg.common.data.model.Classifier;
import lombok.*;

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

  @Column(name="loadOrder")
  private Integer order;

  @Column(name = "loadAllElements", nullable = false)
  private boolean loadAllElements;

  public boolean loadAll()
  {
    return loadAllElements;
  }

  public ClassifierJpaImpl( String system, String name, String shortName, String description, String pluralName, Integer order, boolean loadAllElements )
  {
    super( system, name, shortName, description );
    this.pluralName = pluralName;
    this.order = order;
    this.loadAllElements = loadAllElements;
  }
}
