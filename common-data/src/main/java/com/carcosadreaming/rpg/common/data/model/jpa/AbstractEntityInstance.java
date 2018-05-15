package com.carcosadreaming.rpg.common.data.model.jpa;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

@Data
@EqualsAndHashCode( callSuper = true )
@ToString( callSuper = true )
@MappedSuperclass
public class AbstractEntityInstance extends AbstractEntity
{
}
