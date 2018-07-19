package com.carcosadreaming.rpg.common.data;

import com.carcosadreaming.rpg.common.data.model.jpa.ClassifierJpaImpl;
import com.carcosadreaming.rpg.common.data.model.jpa.DescriptorJpaImpl;
import com.carcosadreaming.rpg.common.data.repository.jpa.ClassifierRepository;
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
  DescriptorRepository descriptorRepo;

  public static void main( String[] args )
  {
    SpringApplication.run( CommonDataApplication.class, args );
  }

  @Override
  public void run ( String... args) throws Exception {
    Assert.notNull(classifierRepo, "Classifier repository not populated.");

    Collection<ClassifierJpaImpl> newClassifiers = Arrays.asList(
        new ClassifierJpaImpl( "PF1", "Ability", "Ability", "Ability scores for an entity", "Abilities" ),
        new ClassifierJpaImpl( "PF1", "Skill", "Skill", "Skills for an entity", "Skills" ),
        new ClassifierJpaImpl( "PF1", "Feat", "Feat", "Feats for an entity", "Feats" ),

        new ClassifierJpaImpl( "PF2", "Ability", "Ability", "Ability scores for an entity", "Abilities" ),
        new ClassifierJpaImpl( "PF2", "Skill", "Skill", "Skills for an entity", "Skills" ),
        new ClassifierJpaImpl( "PF2", "Feat", "Feat", "Feats for an entity", "Feats" ),

        new ClassifierJpaImpl( "MA", "Ability", "Ability", "Ability scores for an entity", "Abilities" )
    );
    Collection<ClassifierJpaImpl> savedClassifiers = classifierRepo.saveAll( newClassifiers );
    savedClassifiers.stream().forEach( c -> log.debug( "Classifier:  {}", c ) );

    ClassifierJpaImpl classifier = savedClassifiers.stream()
        .filter( c -> "PF1".equalsIgnoreCase( c.getSystem() ) && "Ability".equalsIgnoreCase( c.getName() ) )
        .findFirst().orElseThrow( () -> new Exception( "Unable to find specific classifier" ) );
    Collection<DescriptorJpaImpl> newDescriptors = Arrays.asList(
        new DescriptorJpaImpl( "Strength", "STR", "How strong is an entity", classifier ),
        new DescriptorJpaImpl( "Dexterity", "DEX", "How dexterous is an entity", classifier ),
        new DescriptorJpaImpl( "Constitution", "CON", "The overall health of an entity", classifier ),
        new DescriptorJpaImpl( "Intelligence", "INT", "The intelligence of an entity", classifier ),
        new DescriptorJpaImpl( "Wisdom", "WIS", "The wisdom level of an entity", classifier ),
        new DescriptorJpaImpl( "Charisma", "CHA", "How the entity relates to the world", classifier )
    );

    classifier = savedClassifiers.stream()
        .filter( c -> "PF2".equalsIgnoreCase( c.getSystem() ) && "Ability".equalsIgnoreCase( c.getName() ) )
        .findFirst().orElseThrow( () -> new Exception( "Unable to find specific classifier" ) );
    newDescriptors = Arrays.asList(
        new DescriptorJpaImpl( "Strength", "STR", "How strong is an entity", classifier ),
        new DescriptorJpaImpl( "Dexterity", "DEX", "How dexterous is an entity", classifier ),
        new DescriptorJpaImpl( "Constitution", "CON", "The overall health of an entity", classifier ),
        new DescriptorJpaImpl( "Intelligence", "INT", "The intelligence of an entity", classifier ),
        new DescriptorJpaImpl( "Wisdom", "WIS", "The wisdom level of an entity", classifier ),
        new DescriptorJpaImpl( "Charisma", "CHA", "How the entity relates to the world", classifier )
    );

  }
}
