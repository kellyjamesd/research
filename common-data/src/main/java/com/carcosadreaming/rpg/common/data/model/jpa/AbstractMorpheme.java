package com.carcosadreaming.rpg.common.data.model.jpa;

import com.carcosadreaming.rpg.common.data.model.Morpheme;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

@Data
@MappedSuperclass
@NoArgsConstructor
@EqualsAndHashCode( callSuper = true )
@ToString(callSuper = true)
public class AbstractMorpheme extends AbstractEntity implements Morpheme
{
  @Column(name="name", nullable =  false)
  private String name;

  @Column(name = "shortName")
  private String shortName;

  @Column(name = "description")
  private String description;

  protected AbstractMorpheme(String system, String name, String shortName, String description)
  {
    super(system);
    this.name = name;
    this.shortName = shortName;
    this.description = description;
  }

}
