package com.carcosadreaming.rpg.common.data;

import com.carcosadreaming.rpg.common.data.model.jpa.ClassifierJpaImpl;
import com.carcosadreaming.rpg.common.data.model.jpa.ClassifierElementJpaImpl;
import com.carcosadreaming.rpg.common.data.model.jpa.DescriptorJpaImpl;
import com.carcosadreaming.rpg.common.data.repository.jpa.ClassifierRepository;
import com.carcosadreaming.rpg.common.data.repository.jpa.ClassifierElementRepository;
import com.carcosadreaming.rpg.common.data.repository.jpa.DescriptorRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.util.Assert;

import java.util.Arrays;
import java.util.Collection;

@SpringBootApplication
@EnableJpaAuditing
@Slf4j
public class CommonDataApplication implements CommandLineRunner
{

  @Autowired
  ClassifierRepository classifierRepo;

  @Autowired
  ClassifierElementRepository descriptorRepo;

  @Autowired
  DescriptorRepository factorRepo;

  public static void main( String[] args )
  {
    SpringApplication.run( CommonDataApplication.class, args );
  }

  @Override
  public void run ( String... args) throws Exception {
    Assert.notNull(classifierRepo, "Classifier repository not populated.");
    Assert.notNull(descriptorRepo, "descriptor repository not populated");
    Assert.notNull(factorRepo, "factor repository not populated");


    // PF1 Sample Data
    String pf1RuleSystem = "PF1";
    Collection<ClassifierJpaImpl> savedClassifiers = classifierRepo.saveAll( Arrays.asList(
        new ClassifierJpaImpl( pf1RuleSystem, "Ability", "Ability", "Each character has six ability scores that represent his character's most basic attributes.  They represent raw talent and prowess", "Abilities" ),
        new ClassifierJpaImpl( pf1RuleSystem, "Skill", "Skill", "Skills for an entity", "Skills" ),
        new ClassifierJpaImpl( pf1RuleSystem, "Feat", "Feat", "Feats for an entity", "Feats" )
    ) );
    savedClassifiers.stream().forEach( c -> log.debug( "Classifier:  {}", c ) );

    savedClassifiers.stream()
        .filter( c -> "Ability".equalsIgnoreCase( c.getName() ) )
        .findFirst()
        .ifPresent( classifier ->
              descriptorRepo.saveAll( Arrays.asList(
                  new ClassifierElementJpaImpl( "Strength", "STR", "Strength measures muscle and physical power.", classifier ),
                  new ClassifierElementJpaImpl( "Dexterity", "DEX", "Dexterity measures agaility, reflexes, and balance.", classifier ),
                  new ClassifierElementJpaImpl( "Constitution", "CON", "Constitution represents a character's health and stamina.", classifier ),
                  new ClassifierElementJpaImpl( "Intelligence", "INT", "Intelligence determines a character's ability to learn and reason.", classifier ),
                  new ClassifierElementJpaImpl( "Wisdom", "WIS", "Wisdom describes a character's willpower, common sense, awareness, and intuition.", classifier ),
                  new ClassifierElementJpaImpl( "Charisma", "CHA", "Charisma measures a character's personality, personal magnetism, ability to lead, and appearance.", classifier )
              ) )
                  .stream().forEach( d -> {
                    log.debug( "ClassifierElement:  {}", d );
                    factorRepo.saveAll( Arrays.asList(
                        new DescriptorJpaImpl( "Permanent Ability Score", "Score", "The ability score with all permanent modifications", d ),
                        new DescriptorJpaImpl( "Permanent Ability Modifier", "Modifier", "The modifier for the permanent ability score", d ),
                        new DescriptorJpaImpl( "Temporary Adjustments", "Temp Adjustments", "All temporary adjustments to the permanent ability score", d ),
                        new DescriptorJpaImpl( "Temporary Modifier", "Temp Modifier", "The modifier for the temporary ability score", d )
                    ) )
                        .stream().forEach( f -> log.debug( "Descriptor:  {}", f ) );
              } )
            );



    // PF2 Sample Data
    String pf2RuleSystem = "PF2";
    savedClassifiers = classifierRepo.saveAll( Arrays.asList(
        new ClassifierJpaImpl( pf2RuleSystem, "Ability", "Ability", "Ability scores for an entity", "Abilities" ),
        new ClassifierJpaImpl( pf2RuleSystem, "Skill", "Skill", "Skills for an entity", "Skills" ),
        new ClassifierJpaImpl( pf2RuleSystem, "Feat", "Feat", "Feats for an entity", "Feats" )
    ) );
    savedClassifiers.stream().forEach( c -> log.debug( "Classifier:  {}", c ) );
    savedClassifiers.stream()
        .filter( c -> pf2RuleSystem.equalsIgnoreCase( c.getSystem() ) && "Ability".equalsIgnoreCase( c.getName() ) )
        .findFirst()
        .ifPresent( classifier ->
          descriptorRepo.saveAll( Arrays.asList(
              new ClassifierElementJpaImpl( "Strength", "STR", "How strong is an entity", classifier ),
              new ClassifierElementJpaImpl( "Dexterity", "DEX", "How dexterous is an entity", classifier ),
              new ClassifierElementJpaImpl( "Constitution", "CON", "The overall health of an entity", classifier ),
              new ClassifierElementJpaImpl( "Intelligence", "INT", "The intelligence of an entity", classifier ),
              new ClassifierElementJpaImpl( "Wisdom", "WIS", "The wisdom level of an entity", classifier ),
              new ClassifierElementJpaImpl( "Charisma", "CHA", "How the entity relates to the world", classifier )
          ) )
              .stream().forEach( d -> log.debug("ClassifierElement:  {}", d) )
        );

    // OSRIC Sample Data
    String osricRuleSystem = "OSRIC";
    savedClassifiers = classifierRepo.saveAll( Arrays.asList(
        new ClassifierJpaImpl( osricRuleSystem, "Ability", "Ability", "Ability scores for an entity", "Abilities" )
    ) );
    savedClassifiers.stream().forEach( c -> log.debug( "Classifier:  {}", c ) );
    savedClassifiers.stream()
        .filter( c -> osricRuleSystem.equalsIgnoreCase( c.getSystem() ) && "Ability".equalsIgnoreCase( c.getName() ) )
        .findFirst()
        .ifPresent( classifier ->
            descriptorRepo.saveAll( Arrays.asList(
              new ClassifierElementJpaImpl( "Strength", "STR", "How strong is an entity", classifier ),
              new ClassifierElementJpaImpl( "Dexterity", "DEX", "How dexterous is an entity", classifier ),
              new ClassifierElementJpaImpl( "Constitution", "CON", "The overall health of an entity", classifier ),
              new ClassifierElementJpaImpl( "Intelligence", "INT", "The intelligence of an entity", classifier ),
              new ClassifierElementJpaImpl( "Wisdom", "WIS", "The wisdom level of an entity", classifier ),
              new ClassifierElementJpaImpl( "Charisma", "CHA", "How the entity relates to the world", classifier )
            ) )
                .stream().forEach( d -> log.debug("ClassifierElement:  {}", d) )
        );

    // MA Sample Data
    String maRuleSystem = "MA";
    savedClassifiers = classifierRepo.saveAll( Arrays.asList(
        new ClassifierJpaImpl( maRuleSystem, "Ability", "Ability", "Ability scores for an entity", "Abilities" )
    ) );
    savedClassifiers.stream().forEach( c -> log.debug( "Classifier:  {}", c ) );
    savedClassifiers.stream()
        .filter( c -> maRuleSystem.equalsIgnoreCase( c.getSystem() ) && "Ability".equalsIgnoreCase( c.getName() ) )
        .findFirst()
        .ifPresent( classifier ->
            descriptorRepo.saveAll( Arrays.asList(
              new ClassifierElementJpaImpl( "Strength", "STR", "How strong is an entity", classifier ),
              new ClassifierElementJpaImpl( "Dexterity", "DEX", "How dexterous is an entity", classifier ),
              new ClassifierElementJpaImpl( "Constitution", "CON", "The overall health of an entity", classifier ),
              new ClassifierElementJpaImpl( "Intelligence", "INT", "The intelligence of an entity", classifier ),
              new ClassifierElementJpaImpl( "Wisdom", "WIS", "The wisdom level of an entity", classifier ),
              new ClassifierElementJpaImpl( "Charisma", "CHA", "How the entity relates to the world", classifier )
            ) )
                .stream().forEach( d -> log.debug("ClassifierElement:  {}", d) )
        );

    // COC Sample Data

    // AtD Sample Data

  }
}
