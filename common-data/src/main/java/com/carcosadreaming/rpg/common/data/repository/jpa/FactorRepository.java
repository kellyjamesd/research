package com.carcosadreaming.rpg.common.data.repository.jpa;

import com.carcosadreaming.rpg.common.data.model.jpa.FactorJpaImpl;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;
import java.util.UUID;

@RepositoryRestResource(collectionResourceRel = "qualifiers", path = "qualifiers")
public interface FactorRepository extends JpaRepository<FactorJpaImpl, UUID>
{
  List<FactorJpaImpl> findByQualifierId( UUID qualifierId );
}
