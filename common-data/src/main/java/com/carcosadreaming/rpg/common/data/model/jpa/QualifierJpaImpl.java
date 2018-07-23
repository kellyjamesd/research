package com.carcosadreaming.rpg.common.data.model.jpa;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * This holds the Source and Effect for Class abilities, Racial Traits, Feats, etc.
 *
 */

@Data
@EqualsAndHashCode( callSuper = true )
@ToString( callSuper = true )
@Entity
@Table(name="qualifier" )
public class QualifierJpaImpl extends AbstractMorpheme {
}
