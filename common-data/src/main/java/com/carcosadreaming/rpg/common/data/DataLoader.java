package com.carcosadreaming.rpg.common.data;

import com.carcosadreaming.rpg.common.data.model.jpa.ClassifierElementJpaImpl;
import com.carcosadreaming.rpg.common.data.model.jpa.ClassifierJpaImpl;
import com.carcosadreaming.rpg.common.data.model.jpa.DescriptorJpaImpl;
import com.carcosadreaming.rpg.common.data.repository.jpa.ClassifierElementRepository;
import com.carcosadreaming.rpg.common.data.repository.jpa.ClassifierRepository;
import com.carcosadreaming.rpg.common.data.repository.jpa.DescriptorRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired ))
@Slf4j
public class DataLoader implements CommandLineRunner
{
    private final ClassifierRepository classifierRepo;
    private final ClassifierElementRepository classifierElementRepository;
    private final DescriptorRepository descriptorRepository;

    private final ClassifierEngine classifierEngine;

    @Override
    public void run(String... args) throws Exception {
        Assert.notNull(classifierRepo, "Classifier repository not populated.");
        Assert.notNull(classifierElementRepository, "classifier element repository not populated");
        Assert.notNull(descriptorRepository, "descriptor repository not populated");

        Integer classifierOrder = 0;

        // PF1 Sample Data
        String ruleSystem = "PF1";
        List<List<Object>> abilityDescriptors = Arrays.asList(
                Arrays.asList("Permanent Ability Score", "Score", "The ability score with all permanent modifications"),
                Arrays.asList("Permanent Ability Modifier", "Modifier", "The modifier for the permanent ability score"),
                Arrays.asList("Temporary Adjustments", "Temp Adjustments", "All temporary adjustments to the permanent ability score"),
                Arrays.asList("Temporary Modifier", "Temp Modifier", "The modifier for the temporary ability score")
        );
        persistClassifiers(
                ruleSystem,
                Arrays.asList(
                        Arrays.asList(
                                "Ability", "Ability", "Each entity has a number of scores that represent his entity's most basic attributes.  They represent raw talent and prowess", "Abilities", true, Arrays.asList(
                                        Arrays.asList("Strength", "STR", "Strength measures muscle and physical power.", abilityDescriptors),
                                        Arrays.asList("Dexterity", "DEX", "Dexterity measures agility, reflexes, and balance.", abilityDescriptors),
                                        Arrays.asList("Constitution", "CON", "Constitution represents a character's health and stamina.", abilityDescriptors),
                                        Arrays.asList("Intelligence", "INT", "Intelligence determines a character's ability to learn and reason.", abilityDescriptors),
                                        Arrays.asList("Wisdom", "WIS", "Wisdom describes a character's willpower, common sense, awareness, and intuition.", abilityDescriptors),
                                        Arrays.asList("Charisma", "CHA", "Charisma measures a character's personality, personal magnetism, ability to lead, and appearance.", abilityDescriptors)
                                )
                        ),
                        Arrays.asList("Race", "Race", "Race mixes biology and culture then translates those concepts into racial traits.", "Races", false, null),
                        Arrays.asList("Skill", "Skill", "Skills represent some of the most basic and yet most fundamental abilities an entity possesses.", "Skills", true, null),
                        Arrays.asList("Feat", "Feat", "Feats represent abilities outside the scope of an entity's race or class", "Feats", true, null),
                        Arrays.asList("Equipment", "Equipment", "Things owned/carried.", "Equipment", false, null),
                        Arrays.asList(
                                "Entity", "Entity", "Information about a specific entity", "Entity Data", true, Arrays.asList(
                                        Arrays.asList(
                                                "Identification", "ID", "How is the entity known.", Arrays.asList(
                                                        Arrays.asList("Name", "Name", "The true name of the entity"),
                                                        Arrays.asList("Aliases", "Aliases", "Any aliases the entity is known by"),
                                                        Arrays.asList("Current Alias", "Alias", "The alias if any the entity is currently going by"),
                                                        Arrays.asList("Current Description", "Description", "The outward description of the character (.e.g. what its wearing"),
                                                        Arrays.asList("Noticeable Marks", "Marks", "Scars, tattoos, etc. that are clearly visible"),
                                                        Arrays.asList("Hidden/Covered Marks", "Hidden Marks", "Scars, tattoos, etc. that are hidden by magic, clothes, etc.")
                                                )
                                        ),
                                        Arrays.asList(
                                                "Physical Appearance", "Appearance", "Physical appearance.", Arrays.asList(
                                                        Arrays.asList("Size", "Size", "Overall how big the entity is."),
                                                        Arrays.asList("Gender", "Gender", "The gender of the entity"),
                                                        Arrays.asList("Height", "Ht.", "How tall is it"),
                                                        Arrays.asList("Weight", "Wt.", "How much does it weigh"),
                                                        Arrays.asList("Build", "Build", "More detail with a specific size"),
                                                        Arrays.asList("Age", "Age", "How old"),
                                                        Arrays.asList("Hair Color", "Hair", "The color of hair if any"),
                                                        Arrays.asList("Eye Color", "Eyes", "The color of the eyes if any")
                                                )
                                        ),
                                        Arrays.asList(
                                                "Social Aspects", "Social", "How does the entity generally view the world.", Arrays.asList(
                                                        Arrays.asList("Order (Law/Chaos)", "Order", "The respect for truth, honor, authority, etc. Law, Neutral, Chaos"),
                                                        Arrays.asList("Order Level", "Order Level", "A scale of how important the choice of order is."),
                                                        Arrays.asList("Morality (Good/Evil)", "Morality", "Where on the moral compass.  Good, Neutral, Evil"),
                                                        Arrays.asList("Morality Level", "Morality Level", "A scale of how import the choice in morality is.")
                                                )
                                        ),
                                        Arrays.asList(
                                                "Cultural Background", "Heritage", "The entity's location and cultural background.", Arrays.asList(
                                                        Arrays.asList("Home Region (map)", "Region", "Where born/raised/created.  What map"),
                                                        Arrays.asList("Homeland (city, village, forest, hex, etc.)", "Homeland", "Specific locational detail. Hex or name.")
                                                )
                                        )
                                )
                        )
                )
        )
        .forEach(classifier -> log.debug("Classifier:  {}", classifier ) )
        ;





        // PF2 Sample Data
        ruleSystem = "PF2";
        persistClassifiers(
                ruleSystem,
                Arrays.asList(
                        Arrays.asList(
                                "Ability", "Ability", "Each entity has a number of scores that represent his entity's most basic attributes.  They represent raw talent and prowess", "Abilities", true, Arrays.asList(
                                        Arrays.asList("Strength", "STR", "Strength measures muscle and physical power.", abilityDescriptors),
                                        Arrays.asList("Dexterity", "DEX", "Dexterity measures agility, reflexes, and balance.", abilityDescriptors),
                                        Arrays.asList("Constitution", "CON", "Constitution represents a character's health and stamina.", abilityDescriptors),
                                        Arrays.asList("Intelligence", "INT", "Intelligence determines a character's ability to learn and reason.", abilityDescriptors),
                                        Arrays.asList("Wisdom", "WIS", "Wisdom describes a character's willpower, common sense, awareness, and intuition.", abilityDescriptors),
                                        Arrays.asList("Charisma", "CHA", "Charisma measures a character's personality, personal magnetism, ability to lead, and appearance.", abilityDescriptors)
                                )
                        ),
                        Arrays.asList("Race", "Race", "Race mixes biology and culture then translates those concepts into racial traits.", "Races", false, null),
                        Arrays.asList("Skill", "Skill", "Skills represent some of the most basic and yet most fundamental abilities an entity possesses.", "Skills", true, null),
                        Arrays.asList("Feat", "Feat", "Feats represent abilities outside the scope of an entity's race or class", "Feats", true, null),
                        Arrays.asList("Equipment", "Equipment", "Things owned/carried.", "Equipment", false, null),
                        Arrays.asList(
                                "Entity", "Entity", "Information about a specific entity", "Entity Data", true, Arrays.asList(
                                        Arrays.asList(
                                                "Identification", "ID", "How is the entity known.", Arrays.asList(
                                                        Arrays.asList("Name", "Name", "The true name of the entity"),
                                                        Arrays.asList("Aliases", "Aliases", "Any aliases the entity is known by"),
                                                        Arrays.asList("Current Alias", "Alias", "The alias if any the entity is currently going by"),
                                                        Arrays.asList("Current Description", "Description", "The outward description of the character (.e.g. what its wearing"),
                                                        Arrays.asList("Noticeable Marks", "Marks", "Scars, tattoos, etc. that are clearly visible"),
                                                        Arrays.asList("Hidden/Covered Marks", "Hidden Marks", "Scars, tattoos, etc. that are hidden by magic, clothes, etc.")
                                                )
                                        ),
                                        Arrays.asList(
                                                "Physical Appearance", "Appearance", "Physical appearance.", Arrays.asList(
                                                        Arrays.asList("Size", "Size", "Overall how big the entity is."),
                                                        Arrays.asList("Gender", "Gender", "The gender of the entity"),
                                                        Arrays.asList("Height", "Ht.", "How tall is it"),
                                                        Arrays.asList("Weight", "Wt.", "How much does it weigh"),
                                                        Arrays.asList("Build", "Build", "More detail with a specific size"),
                                                        Arrays.asList("Age", "Age", "How old"),
                                                        Arrays.asList("Hair Color", "Hair", "The color of hair if any"),
                                                        Arrays.asList("Eye Color", "Eyes", "The color of the eyes if any")
                                                )
                                        ),
                                        Arrays.asList(
                                                "Social Aspects", "Social", "How does the entity generally view the world.", Arrays.asList(
                                                        Arrays.asList("Order (Law/Chaos)", "Order", "The respect for truth, honor, authority, etc. Law, Neutral, Chaos"),
                                                        Arrays.asList("Order Level", "Order Level", "A scale of how important the choice of order is."),
                                                        Arrays.asList("Morality (Good/Evil)", "Morality", "Where on the moral compass.  Good, Neutral, Evil"),
                                                        Arrays.asList("Morality Level", "Morality Level", "A scale of how import the choice in morality is.")
                                                )
                                        ),
                                        Arrays.asList(
                                                "Cultural Background", "Heritage", "The entity's location and cultural background.", Arrays.asList(
                                                        Arrays.asList("Home Region (map)", "Region", "Where born/raised/created.  What map"),
                                                        Arrays.asList("Homeland (city, village, forest, hex, etc.)", "Homeland", "Specific locational detail. Hex or name.")
                                                )
                                        )
                                )
                        )
                )
        )
                .forEach(classifier -> log.debug("Classifier:  {}", classifier ) )
        ;

        // OSRIC Sample Data
        ruleSystem = "OSRIC";
        persistClassifiers(
                ruleSystem,
                Arrays.asList(
                        Arrays.asList(
                                "Ability", "Ability", "Each entity has a number of scores that represent his entity's most basic attributes.  They represent raw talent and prowess", "Abilities", true, Arrays.asList(
                                        Arrays.asList("Strength", "STR", "Strength measures physical power.", null),
                                        Arrays.asList("Dexterity", "DEX", "Dexterity measures speed, hand-eye coordination, and nimbleness of foot.", null),
                                        Arrays.asList("Constitution", "CON", "Constitution represents overall health and vitality.", null),
                                        Arrays.asList("Intelligence", "INT", "Intelligence measures raw mental power; the ability to calculate, recall facts, and solve abstract problems.", null),
                                        Arrays.asList("Wisdom", "WIS", "Wisdom indicates how in tune the character is with the surroundings; it also indicates mystical attunement.", null),
                                        Arrays.asList("Charisma", "CHA", "Charisma determines the maximum number and loyalty of henchmen.", null)
                                )
                        ),
                        Arrays.asList("Equipment", "Equipment", "Things owned/carried.", "Equipment", false, null),
                        Arrays.asList(
                                "Entity", "Entity", "Information about a specific entity", "Entity Data", true, Arrays.asList(
                                        Arrays.asList(
                                                "Identification", "ID", "How is the entity known.", Arrays.asList(
                                                        Arrays.asList("Name", "Name", "The true name of the entity"),
                                                        Arrays.asList("Aliases", "Aliases", "Any aliases the entity is known by"),
                                                        Arrays.asList("Current Alias", "Alias", "The alias if any the entity is currently going by"),
                                                        Arrays.asList("Current Description", "Description", "The outward description of the character (.e.g. what its wearing"),
                                                        Arrays.asList("Noticeable Marks", "Marks", "Scars, tattoos, etc. that are clearly visible"),
                                                        Arrays.asList("Hidden/Covered Marks", "Hidden Marks", "Scars, tattoos, etc. that are hidden by magic, clothes, etc.")
                                                )
                                        ),
                                        Arrays.asList(
                                                "Physical Appearance", "Appearance", "Physical appearance.", Arrays.asList(
                                                        Arrays.asList("Size", "Size", "Overall how big the entity is."),
                                                        Arrays.asList("Gender", "Gender", "The gender of the entity"),
                                                        Arrays.asList("Height", "Ht.", "How tall is it"),
                                                        Arrays.asList("Weight", "Wt.", "How much does it weigh"),
                                                        Arrays.asList("Build", "Build", "More detail with a specific size"),
                                                        Arrays.asList("Age", "Age", "How old"),
                                                        Arrays.asList("Hair Color", "Hair", "The color of hair if any"),
                                                        Arrays.asList("Eye Color", "Eyes", "The color of the eyes if any")
                                                )
                                        ),
                                        Arrays.asList(
                                                "Social Aspects", "Social", "How does the entity generally view the world.", Arrays.asList(
                                                        Arrays.asList("Order (Law/Chaos)", "Order", "The respect for truth, honor, authority, etc. Law, Neutral, Chaos"),
                                                        Arrays.asList("Order Level", "Order Level", "A scale of how important the choice of order is."),
                                                        Arrays.asList("Morality (Good/Evil)", "Morality", "Where on the moral compass.  Good, Neutral, Evil"),
                                                        Arrays.asList("Morality Level", "Morality Level", "A scale of how import the choice in morality is.")
                                                )
                                        ),
                                        Arrays.asList(
                                                "Cultural Background", "Heritage", "The entity's location and cultural background.", Arrays.asList(
                                                        Arrays.asList("Home Region (map)", "Region", "Where born/raised/created.  What map"),
                                                        Arrays.asList("Homeland (city, village, forest, hex, etc.)", "Homeland", "Specific locational detail. Hex or name.")
                                                )
                                        )
                                )
                        )
                )
        );
//        String osricRuleSystem = "OSRIC";
//        classifierOrder = 0;
//        savedClassifiers = classifierRepo.saveAll( Arrays.asList(
//                new ClassifierJpaImpl( osricRuleSystem, "Ability", "Ability", "Ability scores for an entity", "Abilities", ++classifierOrder, true ),
//                new ClassifierJpaImpl( osricRuleSystem, "Race", "Race", "Race mixes biology and culture then translates those concepts into racial traits.", "Races", ++classifierOrder, false)
//        ) );
//        savedClassifiers.forEach( c -> log.debug( "Classifier:  {}", c ) );
//        savedClassifiers.stream()
//                .filter( c -> osricRuleSystem.equalsIgnoreCase( c.getSystem() ) && "Ability".equalsIgnoreCase( c.getName() ) )
//                .findFirst()
//                .ifPresent( classifier -> {
//                    Integer elementOrder = 0;
//                    classifierElementRepository.saveAll(Arrays.asList(
//                            new ClassifierElementJpaImpl("Strength", "STR", "How strong is an entity", ++elementOrder, classifier),
//                            new ClassifierElementJpaImpl("Dexterity", "DEX", "How dexterous is an entity", ++elementOrder, classifier),
//                            new ClassifierElementJpaImpl("Constitution", "CON", "The overall health of an entity", ++elementOrder, classifier),
//                            new ClassifierElementJpaImpl("Intelligence", "INT", "The intelligence of an entity", ++elementOrder, classifier),
//                            new ClassifierElementJpaImpl("Wisdom", "WIS", "The wisdom level of an entity", ++elementOrder, classifier),
//                            new ClassifierElementJpaImpl("Charisma", "CHA", "How the entity relates to the world", ++elementOrder, classifier)
//                    ))
//                            .forEach(d -> log.debug("ClassifierElement:  {}", d));
//                } );
//
        // MA Sample Data
        ruleSystem = "MA";
        persistClassifiers(
                ruleSystem,
                Arrays.asList(
                        Arrays.asList(
                                "Ability", "Ability", "Each entity has a number of scores that represent his entity's most basic attributes.  They represent raw talent and prowess", "Abilities", true, Arrays.asList(
                                        Arrays.asList("Radiation Resistance", "RR", "Radiation Resistance indicates the ability to resist the damaging effects of radiation.", null),
                                        Arrays.asList("Constitution", "CON", "Constitution indicates how many hit dice and the ability to deal with poisons.", null),
                                        Arrays.asList("Dexterity", "DEX", "Dexterity indicates speed and reaction time", null),
                                        Arrays.asList("Strength", "STR", "Strength indicates the amount of damaging power in any given battle situation.", null),
                                        Arrays.asList("Mental Resistance", "MR", "Mental Resistance indicates the ability to withstand an attack on the mind by mental energy.", null),
                                        Arrays.asList("Leadership Potential", "LP", "Leadership Potential is used to see if and how many other creatures with become followers", null)
                                )
                        ),
                        Arrays.asList("Equipment", "Equipment", "Things owned/carried.", "Equipment", false, null),
                        Arrays.asList(
                                "Entity", "Entity", "Information about a specific entity", "Entity Data", true, Arrays.asList(
                                        Arrays.asList(
                                                "Identification", "ID", "How is the entity known.", Arrays.asList(
                                                        Arrays.asList("Name", "Name", "The true name of the entity"),
                                                        Arrays.asList("Aliases", "Aliases", "Any aliases the entity is known by"),
                                                        Arrays.asList("Current Alias", "Alias", "The alias if any the entity is currently going by"),
                                                        Arrays.asList("Current Description", "Description", "The outward description of the character (.e.g. what its wearing"),
                                                        Arrays.asList("Noticeable Marks", "Marks", "Scars, tattoos, etc. that are clearly visible"),
                                                        Arrays.asList("Hidden/Covered Marks", "Hidden Marks", "Scars, tattoos, etc. that are hidden by magic, clothes, etc.")
                                                )
                                        ),
                                        Arrays.asList(
                                                "Physical Appearance", "Appearance", "Physical appearance.", Arrays.asList(
                                                        Arrays.asList("Size", "Size", "Overall how big the entity is."),
                                                        Arrays.asList("Gender", "Gender", "The gender of the entity"),
                                                        Arrays.asList("Height", "Ht.", "How tall is it"),
                                                        Arrays.asList("Weight", "Wt.", "How much does it weigh"),
                                                        Arrays.asList("Build", "Build", "More detail with a specific size"),
                                                        Arrays.asList("Age", "Age", "How old"),
                                                        Arrays.asList("Hair Color", "Hair", "The color of hair if any"),
                                                        Arrays.asList("Eye Color", "Eyes", "The color of the eyes if any")
                                                )
                                        ),
                                        Arrays.asList(
                                                "Social Aspects", "Social", "How does the entity generally view the world.", Arrays.asList(
                                                        Arrays.asList("Order (Law/Chaos)", "Order", "The respect for truth, honor, authority, etc. Law, Neutral, Chaos"),
                                                        Arrays.asList("Order Level", "Order Level", "A scale of how important the choice of order is."),
                                                        Arrays.asList("Morality (Good/Evil)", "Morality", "Where on the moral compass.  Good, Neutral, Evil"),
                                                        Arrays.asList("Morality Level", "Morality Level", "A scale of how import the choice in morality is.")
                                                )
                                        ),
                                        Arrays.asList(
                                                "Cultural Background", "Heritage", "The entity's location and cultural background.", Arrays.asList(
                                                        Arrays.asList("Home Region (level)", "Region", "Where born/raised/created.  What map"),
                                                        Arrays.asList("Homeland (city, village, forest, hex, etc.)", "Homeland", "Specific locational detail. Hex or name.")
                                                )
                                        )
                                )
                        )
                )
        );

        // COC Sample Data

        // AtD Sample Data


        classifierEngine.loadClassifiers();

    }

    private Collection<DescriptorJpaImpl> persistDescriptors(List<List<String>> descriptors, ClassifierElementJpaImpl classifierElement)
    {
        Collection<DescriptorJpaImpl> descriptorJpaCollection = new ArrayList<>();
        if (null != descriptors)
        {
            descriptors.forEach(
                    row ->  descriptorJpaCollection.add(
                            descriptorRepository.save(
                                    new DescriptorJpaImpl(row.get(0), row.get(1), row.get(2), classifierElement)
                            )
                    )
            );
            descriptorJpaCollection.forEach( d -> log.debug("Classifier Element Descriptors:  {}, {}, {}. {}", classifierElement.getName(), d.getName(), d.getShortName(), d.getDescription() ) );
        }
        return descriptorJpaCollection;
    }

    @SuppressWarnings("unchecked")
    private Collection<ClassifierElementJpaImpl> persistClassifierElements(List<List<Object>> elements, ClassifierJpaImpl classifier)
    {
        Collection<ClassifierElementJpaImpl> elementCollection = new ArrayList<>();
        if (null != elements)
        {
            elements.forEach(
                    row -> {
                        AtomicInteger elementOrder = new AtomicInteger(0);
                        ClassifierElementJpaImpl element = classifierElementRepository.save(
                                new ClassifierElementJpaImpl((String) row.get(0), (String) row.get(1), (String) row.get(2), elementOrder.addAndGet(1), classifier)
                        );
                        elementCollection.add( element );
                        persistDescriptors((List<List<String>>)row.get(3), element );
                    }
            );
            elementCollection.forEach( d -> log.debug("Classifier Elements:  {}, {}, {}. {}", classifier.getName(), d.getName(), d.getShortName(), d.getDescription() ) );
        }
        return elementCollection;
    }

    @SuppressWarnings("unchecked")
    private Collection<ClassifierJpaImpl> persistClassifiers(String rulesSystem, List<List<Object>> classifiers)
    {
        Collection<ClassifierJpaImpl> classifierCollection = new ArrayList<>();
        if (null != classifiers)
        {
            AtomicInteger classifierOrder = new AtomicInteger(0);
            classifiers.forEach(
                    row -> {
                        Arrays.asList(row).forEach(item -> log.debug("classifier row item:  {}", item ) );
                        log.debug( "classifier row:  {}", row );
                        ClassifierJpaImpl classifier = classifierRepo.save(
                                new ClassifierJpaImpl(rulesSystem, (String) row.get(0), (String) row.get(1), (String) row.get(2), (String) row.get(3), classifierOrder.addAndGet(1), (Boolean) row.get(4))
                        );
                        log.debug( "saved classifier:  {}, {}", classifier.getName(), classifier.getOrder() );
                        classifierCollection.add(classifier);
                        persistClassifierElements((List<List<Object>>)row.get(5), classifier);
                    }
            );
        }
        return classifierCollection;
    }
}
