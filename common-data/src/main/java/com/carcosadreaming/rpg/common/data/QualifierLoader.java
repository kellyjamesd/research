package com.carcosadreaming.rpg.common.data;

import com.carcosadreaming.rpg.common.data.model.Classifier;
import com.carcosadreaming.rpg.common.data.model.ClassifierIdentifier;
import com.carcosadreaming.rpg.common.data.model.Descriptor;
import com.carcosadreaming.rpg.common.data.model.Qualifier;
import com.carcosadreaming.rpg.common.data.model.jpa.FactorJpaImpl;
import com.carcosadreaming.rpg.common.data.model.jpa.QualifierJpaImpl;
import com.carcosadreaming.rpg.common.data.repository.jpa.ClassifierElementRepository;
import com.carcosadreaming.rpg.common.data.repository.jpa.ClassifierRepository;
import com.carcosadreaming.rpg.common.data.repository.jpa.DescriptorRepository;
import com.carcosadreaming.rpg.common.data.repository.jpa.FactorRepository;
import com.carcosadreaming.rpg.common.data.repository.jpa.QualifierRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

/**
 * Qualifier Data Load
 * Example:
 *      Classifier:     Ability
 *          Classifer Elements:  Strength, Dexterity, Constitution, Intelligence, Wisdom, Charisma
 *              Descriptor:  Permanent Ability Score
 *      Scenario - Character generation with point buy:
 *          Primary Descriptor:  Entity:Character Generation:Abilities
 *          Qualifier:      Point Buy Abilities
 *              Factors:
 *                  Strength Score
 *                  Dexterity Score
 *                  Constitution Score
 *                  Intelligence Score
 *                  Wisdom Score
 *                  Charisma Score
 *          Qualifier:      Rolled Abilities
 *              Factors:
 *                  Point Buy Total
 *                  Strength Score
 *                  Dexterity Score
 *                  Constitution Score
 *                  Intelligence Score
 *                  Wisdom Score
 *                  Charisma Score
 *          Qualifier:      Elven Race
 *              Factors:
 *                  Dexterity Score
 *                  Intelligence Score
 *                  Constitution Score
 */

@Order( value = 10 )
@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Slf4j
public class QualifierLoader implements CommandLineRunner
{
    private final ClassifierEngine classifierEngine;

    private final QualifierRepository qualifierRepo;
    private final FactorRepository factorRepo;

    private final ClassifierRepository classifierRepo;
    private final ClassifierElementRepository classifierElementRepository;
    private final DescriptorRepository descriptorRepository;

    @Override
    public void run(String... args) throws Exception {
        Assert.notNull(classifierEngine, "Classifier Engine not populated.");
//        Assert.notNull(classifierRepo, "Classifier repository not populated.");
//        Assert.notNull(classifierElementRepository, "classifier element repository not populated");
//        Assert.notNull(descriptorRepository, "descriptor repository not populated");

        // PF1 Sample Data
        String ruleSystem = "PF1";

        ClassifierIdentifier identifier;
        //identifier = new ClassifierIdentifier( ruleSystem,"Ability", "Strength", "Permanent Ability Score");
        identifier = new ClassifierIdentifier( ruleSystem,"Entity", "Character Generation", "Abilities");

        Descriptor qualifierDescriptor = classifierEngine.getDescriptor(identifier);
        log.debug("Qualifier Descriptor:  {}:{}:{}", classifierEngine.getClassifier(identifier).getName(), classifierEngine.getClassifierElement(identifier).getName(), qualifierDescriptor.getName());

        QualifierJpaImpl qualifier = new QualifierJpaImpl( "Point Buy Abilities", "Point Buy", "Abilities are selected via the point buy system.", qualifierDescriptor );
        qualifier = qualifierRepo.save( qualifier );

        identifier =  new ClassifierIdentifier( ruleSystem, "Ability", "Strength", "Permanent Ability Score" );
        Descriptor factorDescriptor = classifierEngine.getDescriptor( identifier );
        FactorJpaImpl factor = new FactorJpaImpl( "Strength Score", "STR", "Point Buy for Strength.", qualifier, factorDescriptor );
        factor = factorRepo.save( factor );
        log.debug("Factor Descriptor:  {}:{}:{}", classifierEngine.getClassifier(identifier).getName(), classifierEngine.getClassifierElement(identifier).getName(), factorDescriptor.getName());
        log.debug("Identifier:  {}, {}", identifier, factor);

        identifier.setClassifierElementName( "Dexterity" );
        factorDescriptor = classifierEngine.getDescriptor( identifier );
        factor = new FactorJpaImpl( "Dexterity Score", "DEX", "Point Buy for Dexterity.", qualifier, factorDescriptor );
        factor = factorRepo.save( factor );
        log.debug("Factor Descriptor:  {}:{}:{}", classifierEngine.getClassifier(identifier).getName(), classifierEngine.getClassifierElement(identifier).getName(), factorDescriptor.getName());
        log.debug("Identifier:  {}, {}", identifier, factor);

        builder(ruleSystem,"Entity", "Character Generation", "Abilities")
            .and()
        ;

    }

    private QualifierBuilder builder()
    {
        QualifierBuilder builder = new QualifierBuilder();
        builder.setClassifierEngine( this.classifierEngine );
        builder.setQualifierRepo( this.qualifierRepo );
        builder.setFactorRepo( this.factorRepo );
        return builder;
    }

    private QualifierBuilder builder(ClassifierIdentifier identifier)
    {
        QualifierBuilder builder = builder();
        builder.setIdentifier( identifier );
        return builder;
    }

    private QualifierBuilder builder(String ruleSet, String classifierName, String elementName, String descriptorName)
    {
        return builder( new ClassifierIdentifier( ruleSet, classifierName, elementName, descriptorName ) );
    }


}
