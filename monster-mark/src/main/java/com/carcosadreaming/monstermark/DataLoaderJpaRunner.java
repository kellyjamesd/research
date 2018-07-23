package com.carcosadreaming.monstermark;

import com.carcosadreaming.rpg.common.data.model.jpa.ClassifierJpaImpl;
import com.carcosadreaming.rpg.common.data.repository.jpa.ClassifierRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired ))
@Slf4j
public class DataLoaderJpaRunner implements CommandLineRunner
{
  private static final String SYSTEM_DEFAULT_USERNAME = "SystemDesigner";
  private final ClassifierRepository classifierRepository;

  @Override public void run( String... args ) throws Exception
  {
    log.debug("DataLoaderRunner::run");
    log.debug("classifierRepository:  {}", classifierRepository);

    ClassifierJpaImpl
        classifier = classifierRepository.save( buildNewClassifier( "Ability", "Ability", "Abilities", "Each character has six ability scores that represent the character's most basic attributes.  They are the raw talent and prowess of the character." ) );
        //While a character rarely rolls a check using just an ability score, these scores, and the modifiers they create, affect nearly every aspect of a character's skills and abilities.  Each ability score generally ranges from 3 to 18, although racial bonuses and penalties can alter this; anverage ability score is 10."

    classifier = classifierRepository.save( buildNewClassifier( "Race", "Race", "Races", "Race provides both a starting point for character creation and sets the tone for a character as it progresses.  Races mixes biology and culture, then translates those concepts into racial traits." ) );
        //Yet since both biology and culture are mutable--especially when one considers the powerful forces of magic--racial traits can be so diverse that two elves can be extremely different while still manifesting aspects of their shared heritage and culture.  A race's traits, its history, its relations with other races, and the culture that all of these things imply--all of these frame your character."

    classifier = classifierRepository.save( buildNewClassifier( "Class", "Class", "Classes", "Every character chooses one or more classes that define the way they go about interacting with game world" ) );

    classifier = classifierRepository.save( buildNewClassifier( "Skill", "Skill", "Skills", "Skills represent some of the most basic and yet most fundamental abilities a character possess.  As the character advances in level, new skills can be gained and dramatic improvements can be made to existing skills." ) );

//    classifier = classifierRepository.save( buildNewClassifier( "", "", "", "" ) );


  }

  private ClassifierJpaImpl buildNewClassifier(String name, String shortName, String pluralName, String description)
  {
    ClassifierJpaImpl classifier = new ClassifierJpaImpl();
    classifier = new ClassifierJpaImpl();
    classifier.setName( name );
    classifier.setShortName( shortName );
    classifier.setPluralName( pluralName );
    classifier.setDescription( description );
    classifier.setCreatedBy( SYSTEM_DEFAULT_USERNAME );
    classifier.setModifiedBy( SYSTEM_DEFAULT_USERNAME );
    return classifierRepository.save( classifier );
  }
}
