package com.carcosadreaming.rpg.common.data.model.jpa;

import com.carcosadreaming.rpg.common.data.model.Descriptor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.UUID;

/**
 * This holds the modifications a qualifier receives
 */

@Data
@EqualsAndHashCode( callSuper = true )
@ToString( callSuper = true )
@Entity
@Table(name="factor" )
public class FactorJpaImpl  extends AbstractMorpheme {

    @Column(name = "descriptorId")
    private UUID descriptorId;

    public FactorJpaImpl(String name, String shortName, String description, Descriptor descriptor)

    {
        super(descriptor.getClassifierElement().getSystem(), name, shortName, description);
        this.descriptorId = descriptor.getId();
    }

}
