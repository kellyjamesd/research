package com.carcosadreaming.rpg.common.data.repository.jpa;

import com.carcosadreaming.rpg.common.data.model.jpa.ClassifierJpaImpl;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ClassifierRepository extends JpaRepository<ClassifierJpaImpl, UUID>
{
}
