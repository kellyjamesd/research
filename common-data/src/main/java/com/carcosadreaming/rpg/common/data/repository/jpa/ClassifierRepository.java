package com.carcosadreaming.rpg.common.data.repository.jpa;

import com.carcosadreaming.rpg.common.data.model.jpa.ClassifierJpaImpl;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.UUID;

@RepositoryRestResource(collectionResourceRel = "classifiers", path = "classifiers")
public interface ClassifierRepository extends JpaRepository<ClassifierJpaImpl, UUID>
{
}
