package com.carcosadreaming.rpg.common.data.model.jpa;

import com.carcosadreaming.rpg.common.data.model.CommonEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.Map;

@Data
@NoArgsConstructor
@EqualsAndHashCode( callSuper = true )
@ToString( callSuper = true )
@Entity
@Table(name="entity")
public class EntityJpaImpl extends AbstractEntity implements CommonEntity
{
}
