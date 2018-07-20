package com.carcosadreaming.rpg.common.data.repository.jpa;

import com.carcosadreaming.rpg.common.data.model.jpa.ClassifierElementJpaImpl;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;
import java.util.UUID;

@RepositoryRestResource(collectionResourceRel = "classifierElements", path = "classifierElements")
public interface ClassifierElementRepository extends JpaRepository<ClassifierElementJpaImpl, UUID>
{
  List<ClassifierElementJpaImpl> findByClassifierId(UUID classifierId);
}
