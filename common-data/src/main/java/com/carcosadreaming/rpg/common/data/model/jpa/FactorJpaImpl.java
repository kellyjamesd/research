package com.carcosadreaming.rpg.common.data.model.jpa;

import com.carcosadreaming.rpg.common.data.model.CommonEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Data
@EqualsAndHashCode( callSuper = true )
@ToString( callSuper = true )
@Entity
@Table(name="datum")
public class FactorJpaImpl extends AbstractEntity implements CommonEntity
{

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "descriptorId", nullable = false)
  @OnDelete( action = OnDeleteAction.CASCADE )
  @JsonIgnore
  private DescriptorJpaImpl descriptor;

}
