package com.carcosadreaming.rpg.common.data.model.jpa;

import com.carcosadreaming.rpg.common.data.model.Descriptor;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "descriptor")
public class DescriptorJpaImpl extends AbstractMorpheme implements Descriptor
{

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "classifierId", nullable = false)
  @OnDelete( action = OnDeleteAction.CASCADE )
  @JsonIgnore
  private ClassifierJpaImpl classifier;

}
