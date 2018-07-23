package com.carcosadreaming.rpg.common.data;

import com.carcosadreaming.rpg.common.data.model.jpa.ClassifierJpaImpl;
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

import java.util.Arrays;
import java.util.List;

@Order( value = 10 )
@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Slf4j
public class RaceFF1DataLoader implements CommandLineRunner
{
    private final ClassifierRepository classifierRepo;
    private final ClassifierElementRepository classifierElementRepository;
    private final DescriptorRepository descriptorRepository;

    @Override
    public void run(String... args) throws Exception {

        // This may be handled by

//        Assert.notNull(classifierRepo, "Classifier repository not populated.");
//        Assert.notNull(classifierElementRepository, "classifier element repository not populated");
//        Assert.notNull(descriptorRepository, "descriptor repository not populated");
//
//        String ruleSystem = "PF1";
//
//        ClassifierJpaImpl raceClassifier = classifierRepo.findBySystemOrderByOrder(ruleSystem)
//                .stream()
//                .filter( c -> "Race".equalsIgnoreCase( c.getName() ) )
//                .findFirst()
//                .orElseThrow( () -> new Exception( "PF1 Race classifier not found." ) );
//
//        List<List<Object>> raceData = Arrays.asList(
//                // Racial ability score traits can be handled as a factor on ability score based on race
//                Arrays.asList("Size", "Size", "The overall size of the race", null),
//                Arrays.asList("Creature Type", "Type", "", Arrays.asList(
//                        Arrays.asList("Type", "Type", "The base Type of the race"),
//                        Arrays.asList("SubType", "SubType", "The "))),
//                Arrays.asList("", "", "", Arrays.asList("", "", "")),
//                Arrays.asList("", "", "", Arrays.asList("", "", "")),
//                Arrays.asList("", "", "", Arrays.asList("", "", "")),
//                Arrays.asList("", "", "", Arrays.asList("", "", "")),
//                Arrays.asList("", "", "", Arrays.asList("", "", "")),
//        );

    }
}
