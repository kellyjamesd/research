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
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

@Order( value = 1 )
@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired ))
@Slf4j
public class GeneralClassifierLoader implements CommandLineRunner
{
    private final ClassifierRepository classifierRepo;
    private final ClassifierElementRepository classifierElementRepository;
    private final DescriptorRepository descriptorRepository;

    private final ClassifierEngine classifierEngine;

    @Override
    public void run(String... args)
    {
        Assert.notNull(classifierRepo, "Classifier repository not populated.");
        Assert.notNull(classifierElementRepository, "classifier element repository not populated");
        Assert.notNull(descriptorRepository, "descriptor repository not populated");

        // PF1 Sample Data
        String ruleSystem = "PF1";
        List<List<Object>> abilityDescriptors = Arrays.asList(
                Arrays.asList("Permanent Ability Score", "Score", "The ability score with all permanent modifications"),
                Arrays.asList("Permanent Ability Modifier", "Modifier", "The modifier for the permanent ability score"),
                Arrays.asList("Temporary Adjustments", "Temp Adjustments", "All temporary adjustments to the permanent ability score"),
                Arrays.asList("Temporary Modifier", "Temp Modifier", "The modifier for the temporary ability score")
        );
        List<List<Object>> skillDescriptors = Arrays.asList(
                Arrays.asList("Key Ability", "Ability", "The ability associated with the skill."),
                Arrays.asList("Class Skill", "Class", "Is the skill associated with a class of the character"),
                Arrays.asList("Trained Use Only", "Trained", "The skill can only be used if trained (at least one rank)"),
                Arrays.asList("Armor Check Penalty", "Armor", "Does wearing armor impose a penalty for this skill use."),
                Arrays.asList("Total Bonus", "Bonus", "The total bonus of all modifiers"),
                Arrays.asList("Ability Modifier", "Mod.", "The ability modifier of the relevant ability."),
                Arrays.asList("Total Ranks", "Ranks", "The total number of ranks applied to the skill."),
                Arrays.asList("Misc. Modifiers", "Misc.", "Total of any additional static modifiers."),
                Arrays.asList("Conditional Modifiers", "Cond.", "Any additional modifiers that are situational based.")
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
                        // Race can be handled by qualifiers, factors, and datum.
//                        Arrays.asList(
//                                "Race", "Race", "Race mixes biology and culture then translates those concepts into racial traits.", "Races", false, null),
                        Arrays.asList(
                                "Skill", "Skill", "Skills represent some of the most basic and yet most fundamental abilities an entity possesses.", "Skills", true, Arrays.asList(
                                        Arrays.asList( "Acrobatics", "Acrobatics", "You can keep your balance while traversing narrow or treacherous surfaces. You can also dive, flip, jump, and roll, avoiding attacks and confusing your opponents.", skillDescriptors ),
                                        Arrays.asList( "Appraise", "Appraise", "You can evaluate the monetary value of an object.", skillDescriptors ),
                                        Arrays.asList( "Bluff", "Bluff", "You know how to tell a lie.", skillDescriptors ),
                                        Arrays.asList( "Climb", "Climb", "You are skilled at scaling vertical surfaces, from smooth city walls to rocky cliffs.", skillDescriptors ),
                                        Arrays.asList( "Craft", "Craft", "You are skilled in the creation of a specific group of items, such as armor or weapons. ", skillDescriptors ),
                                        Arrays.asList( "Diplomacy", "Diplomacy", "You can use this skill to persuade others, to resolve differences, to gather valuable information, and to negotiate conflicts by using the proper etiquette and manners.", skillDescriptors ),
                                        Arrays.asList( "Disable Device", "Disable", "You are skilled at disarming traps and opening locks. In addition, this skill lets you sabotage simple mechanical devices, such as catapults, wagon wheels, and doors.", skillDescriptors ),
                                        Arrays.asList( "Disguise", "Disguise", "You are skilled at changing your appearance.", skillDescriptors ),
                                        Arrays.asList( "Escape Artist", "Escape", "Your training allows you to slip bonds and escape from grapples.", skillDescriptors ),
                                        Arrays.asList( "Fly", "Fly", "You are skilled at flying, either through the use of wings or magic, and you can perform daring or complex maneuvers while airborne.", skillDescriptors ),
                                        Arrays.asList( "Handle Animal", "Handle", "You are trained at working with animals, and can teach them tricks, get them to follow your simple commands, or even domesticate them.", skillDescriptors ),
                                        Arrays.asList( "Heal", "Heal", "You are skilled at tending to the ailments of others.", skillDescriptors ),
                                        Arrays.asList( "Intimidate", "Intimidate", "You can use this skill to frighten your opponents or to get them to act in a way that benefits you. This skill includes verbal threats and displays of prowess.", skillDescriptors ),
                                        Arrays.asList( "Knowledge Arcana", "Arcana", "Ancient mysteries, magic traditions, arcane symbols, constructs, dragons, magical beasts", skillDescriptors ),
                                        Arrays.asList( "Knowledge Dungeoneering", "Dungeon", "Aberrations, caverns, oozes, spelunking", skillDescriptors ),
                                        Arrays.asList( "Knowledge Eldritch Arcana", "Eldritch", "Covers arcane knowledge from the ancient periods, outer planes/gods, and foreign lands", skillDescriptors ),
                                        Arrays.asList( "Knowledge Engineering", "Engineer", "Buildings, aqueducts, bridges, fortifications, technology", skillDescriptors ),
                                        Arrays.asList( "Knowledge Geography", "Geography", "Lands, terrain, climate, people, astronomy", skillDescriptors ),
                                        Arrays.asList( "Knowledge History", "History", "Wars, colonies, migrations, founding of cities", skillDescriptors ),
                                        Arrays.asList( "Knowledge Local", "Local", "Legends, personalities, inhabitants, laws, customs, traditions, humanoids", skillDescriptors ),
                                        Arrays.asList( "Knowledge Nature", "Nature", "Animals, fey, monstrous humanoids, plants, seasons and cycles, weather, vermin", skillDescriptors ),
                                        Arrays.asList( "Knowledge Nobility", "Nobility", "lineages, heraldry, personalities, royalty", skillDescriptors ),
                                        Arrays.asList( "Knowledge Planes", "Planes", "The Inner Planes, the Outer Planes, the Astral Plane, the Ethereal Plane, outsiders, planar magic", skillDescriptors ),
                                        Arrays.asList( "Knowledge Religion", "Religion", "Gods and goddesses, mythic history, ecclesiastic tradition, holy symbols, undead", skillDescriptors ),
                                        Arrays.asList( "Linguistics", "Linguistics", "You are skilled at working with language.", skillDescriptors ),
                                        Arrays.asList( "Perception", "Perception", "Your senses allow you to notice fine details and alert you to danger. ", skillDescriptors ),
                                        Arrays.asList( "Perform", "Perform", "You are skilled at one form of entertainment, from singing to acting to playing an instrument.", skillDescriptors ),
                                        Arrays.asList( "Profession", "Profession", "You are skilled at a specific job.", skillDescriptors ),
                                        Arrays.asList( "Ride", "Ride", "You are skilled at riding mounts, usually a horse, but possibly something more exotic, like a griffon or pegasus.", skillDescriptors ),
                                        Arrays.asList( "Sense Motive", "Sense", "You are skilled at detecting falsehoods and true intentions.", skillDescriptors ),
                                        Arrays.asList( "Sleight of Hand", "Sleight", "Your training allows you to pick pockets, draw hidden weapons, and take a variety of actions without being noticed.", skillDescriptors ),
                                        Arrays.asList( "Spellcraft", "Spellcraft", "You are skilled at the art of casting spells, identifying magic items, crafting magic items, and identifying spells as they are being cast.", skillDescriptors ),
                                        Arrays.asList( "Stealth", "Stealth", "You are skilled at avoiding detection, allowing you to slip past foes or strike from an unseen position.", skillDescriptors ),
                                        Arrays.asList( "Survival", "Survival", "You are skilled at surviving in the wild and at navigating in the wilderness. You also excel at following trails and tracks left by others.", skillDescriptors ),
                                        Arrays.asList( "Swim", "Swim", "You know how to swim and can do so even in stormy water.", skillDescriptors ),
                                        Arrays.asList( "Use Magical Device", "UMD", "You are skilled at activating magic items, even if you are not otherwise trained in their use.", skillDescriptors )
                                )
                        ),
                        Arrays.asList(
                                // Look at handling these as qualifiers, factors, and datum.
                                "Trait", "Trait", "Character traits are abilities not tied to your race or class allowing for additional customization.", "Traits", false, Arrays.asList(
                                        // TODO Include source as a descriptor.
                                        Arrays.asList("Campaign Traits", "Campaign", "Campaign traits are specifically designed to tie your character into a storyline", null),
                                        Arrays.asList("Combat Traits", "Combat", "Combat traits are associated with combat, battle, and physical prowess.", null),
                                        Arrays.asList("Equipment Traits", "Equipment", "Equipment traits help characters make the most of their equipment or use their equipment to make the most of their skills", null),
                                        Arrays.asList("Faith Traits", "Faith", "Faith traits rely upon conviction of spirit, perception, and religion, but are not directly tied to the worship of a specific deity.", null),
                                        Arrays.asList("Family Traits", "Family", "Family traits allow you to inject a mechanical edge into the dynamics of your family", null),
                                        Arrays.asList("Magic Traits", "Magic", "Magic traits are associated with magic, and focus on spellcasting and manipulating magic.", null),
                                        Arrays.asList("Race Traits", "Race", "Race traits are keyed to specific races or ethnicities.", null),
                                        Arrays.asList("Regional Traits", "Regional", "Regional traits are keyed to specific regions, be they large (such as a nation or geographic region) or small (such as a city or a specific mountain).", null),
                                        Arrays.asList("Religion Traits", "Religion", "Religion traits indicate that your character has an established faith in a specific deity.", null),
                                        Arrays.asList("Social Traits", "Social", "Social Traits are a sort of catch-all category—these traits reflect the social upbringing of your character.", null)
                                )
                        ),
                        Arrays.asList(
                                // Look at handling these as qualifiers, factors, and datum.
                                // TODO Include source as a descriptor.
                                "Feat", "Feat", "Feats represent abilities outside the scope of an entity's race or class", "Feats", true, null),
                        Arrays.asList(
                                "Equipment", "Equipment", "Things owned/carried.", "Equipment", false, null),
                        Arrays.asList(
                                "Entity", "Entity", "Information about a specific entity", "Entity Data", true, Arrays.asList(
                                        Arrays.asList(
                                                "Identification", "ID", "How is the entity known.", Arrays.asList(
                                                        Arrays.asList("Name", "Name", "The true name of the entity"),
                                                        Arrays.asList("Aliases", "Aliases", "Any aliases the entity is known by"),
                                                        Arrays.asList("Current Alias", "Alias", "The alias if any the entity is currently going by"),
                                                        Arrays.asList("Current Description", "Description", "The outward description of the character (.e.g. what its wearing"),
                                                        Arrays.asList("Noticeable Marks", "Marks", "Scars, tattoos, etc. that are clearly visible"),
                                                        Arrays.asList("Hidden/Covered Marks", "Hidden Marks", "Scars, tattoos, etc. that are hidden by magic, clothes, etc."),
                                                        Arrays.asList("Owner", "Owner", "Who is running this entity"),
                                                        Arrays.asList("Campaign", "Campaign", "What campaign is this entity in.")
                                                )
                                        ),
                                        Arrays.asList(
                                                "Physical Appearance", "Appearance", "Physical appearance.", Arrays.asList(
                                                        Arrays.asList("Race", "Race", "What is the race of the entity."),
                                                        Arrays.asList("Size", "Size", "Overall how big the entity is."),
                                                        Arrays.asList("Speed", "Speed", "How fast does it move."),
                                                        Arrays.asList("Gender", "Gender", "The gender of the entity"),
                                                        Arrays.asList("Height", "Ht.", "How tall is it"),
                                                        Arrays.asList("Weight", "Wt.", "How much does it weigh"),
                                                        Arrays.asList("Build", "Build", "More detail within a specific size"),
                                                        Arrays.asList("Age", "Age", "How old"),
                                                        Arrays.asList("Hair Color", "Hair", "The color of hair if any"),
                                                        Arrays.asList("Eye Color", "Eyes", "The color of the eyes if any"),
                                                        Arrays.asList("Vision", "Vision", "How does the entity see.")
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
                                                        Arrays.asList("Homeland (city, village, forest, hex, etc.)", "Homeland", "Specific locational detail. Hex or name."),
                                                        Arrays.asList("Languages", "Language", "How does the entity communicate.")
                                                )
                                        ),
                                        Arrays.asList(
                                                "Character Generation", "Generation", "Aspects of how the entity was generated.", Arrays.asList(
                                                        Arrays.asList( "Abilities", "Abilities", "How are the ability scores generated" )
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

    private void persistDescriptors(List<List<String>> descriptors, ClassifierElementJpaImpl classifierElement)
    {
        Collection<DescriptorJpaImpl> descriptorJpaCollection = new ArrayList<>();
        if (null != descriptors) {
            descriptors.forEach(
                    row -> descriptorJpaCollection.add(
                            descriptorRepository.save(
                                    new DescriptorJpaImpl(row.get(0), row.get(1), row.get(2), classifierElement)
                            )
                    )
            );
            descriptorJpaCollection.forEach(d -> log.debug("Classifier Element Descriptors:  {}, {}, {}. {}", classifierElement.getName(), d.getName(), d.getShortName(), d.getDescription()));
        }
    }

    @SuppressWarnings("unchecked")
    private void persistClassifierElements(List<List<Object>> elements, ClassifierJpaImpl classifier)
    {
        Collection<ClassifierElementJpaImpl> elementCollection = new ArrayList<>();
        if (null != elements)
        {
            log.debug("elements:  {}, {}", classifier.getName(), elements);
            elements.forEach(
                    row -> {
                        AtomicInteger elementOrder = new AtomicInteger(0);
                        ClassifierElementJpaImpl element = classifierElementRepository.save(
                                new ClassifierElementJpaImpl((String) row.get(0), (String) row.get(1), (String) row.get(2), elementOrder.addAndGet(1), classifier)
                        );
                        elementCollection.add( element );
                        log.debug("row.get(3):  {}, {}", row.get(3), row);
                        persistDescriptors((List<List<String>>)row.get(3), element );
                    }
            );
            elementCollection.forEach( d -> log.debug("Classifier Elements:  {}, {}, {}. {}", classifier.getName(), d.getName(), d.getShortName(), d.getDescription() ) );
        }
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
                        Collections.singletonList(row).forEach(item -> log.debug("classifier row item:  {}", item ) );
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
