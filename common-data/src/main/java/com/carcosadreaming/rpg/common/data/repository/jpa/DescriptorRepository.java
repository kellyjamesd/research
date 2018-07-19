package com.carcosadreaming.rpg.common.data.repository.jpa;

import com.carcosadreaming.rpg.common.data.model.jpa.DescriptorJpaImpl;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;
import java.util.UUID;

@RepositoryRestResource(collectionResourceRel = "descriptors", path = "descriptors")
public interface DescriptorRepository extends JpaRepository<DescriptorJpaImpl, UUID>
{
  List<DescriptorJpaImpl> findByClassifierId(UUID classifierId);
}
