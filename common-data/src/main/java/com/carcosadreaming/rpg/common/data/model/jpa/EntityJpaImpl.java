package com.carcosadreaming.rpg.common.data.model.jpa;

import com.carcosadreaming.rpg.common.data.model.CommonEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Data
@NoArgsConstructor
@EqualsAndHashCode( callSuper = true )
@ToString( callSuper = true )
@Entity
@Table(name="entity")
public class EntityJpaImpl extends AbstractEntity implements CommonEntity
{

}
