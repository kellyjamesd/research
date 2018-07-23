package com.carcosadreaming.rpg.common.data;

import com.carcosadreaming.rpg.common.data.model.Classifier;
import com.carcosadreaming.rpg.common.data.model.ClassifierIdentifier;
import com.carcosadreaming.rpg.common.data.model.Descriptor;
import com.carcosadreaming.rpg.common.data.repository.jpa.ClassifierElementRepository;
import com.carcosadreaming.rpg.common.data.repository.jpa.ClassifierRepository;
import com.carcosadreaming.rpg.common.data.repository.jpa.DescriptorRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

@Order( value = 100 )
@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Slf4j
public class EntityLoader implements CommandLineRunner
{
    private final ClassifierRepository classifierRepo;
    private final ClassifierElementRepository classifierElementRepository;
    private final DescriptorRepository descriptorRepository;

    private final ClassifierEngine classifierEngine;

    @Override
    public void run(String... args) throws Exception {
        Assert.notNull(classifierEngine, "Classifier Engine not populated.");
//        Assert.notNull(classifierRepo, "Classifier repository not populated.");
//        Assert.notNull(classifierElementRepository, "classifier element repository not populated");
//        Assert.notNull(descriptorRepository, "descriptor repository not populated");

        // PF1 Sample Data
        String ruleSystem = "PF1";

        ClassifierIdentifier identifier = new ClassifierIdentifier( ruleSystem,"Ability", "Strength", "Permanent Ability Score");

        Descriptor descriptor = classifierEngine.getDescriptor(identifier);

        log.debug("Identifier:  {}", identifier);
        log.debug("Ability:  {}", classifierEngine.getClassifier(identifier).getName());
        log.debug("Strength:  {}", classifierEngine.getClassifierElement(identifier).getName());
        log.debug("Score:  {}", descriptor.getName());

    }
}
