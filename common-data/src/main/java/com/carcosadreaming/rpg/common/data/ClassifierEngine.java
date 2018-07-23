package com.carcosadreaming.rpg.common.data;

import com.carcosadreaming.rpg.common.data.model.Classifier;
import com.carcosadreaming.rpg.common.data.model.ClassifierElement;
import com.carcosadreaming.rpg.common.data.model.ClassifierIdentifier;
import com.carcosadreaming.rpg.common.data.model.Descriptor;
import com.carcosadreaming.rpg.common.data.repository.jpa.service.ClassifierServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.*;

@Service
@RequiredArgsConstructor(onConstructor=@__({@Autowired}))
@Slf4j
public class ClassifierEngine
{

    private static final List<String> RULE_SYSTEMS = new ArrayList<String>( Arrays.asList( "PF1", "PF2", "OSRIC", "MA" ) );
    private static final String CLASSIFIER_KEY = "classifier";
    private static final String CLASSIFIER_ELEMENT_MAP_KEY = "classifier elements";
    private static final String CLASSIFIER_ELEMENT_KEY = "classifier element";
    private static final String DESCRIPTOR_MAP_KEY = "descriptors";

    private final ClassifierServiceImpl classifierService;

    private Map<String, Map<String, Object>> rulesClassifierMap = new LinkedHashMap<>();

    @SuppressWarnings("unchecked")
    private Map<String, Object> getDescriptorMap(Map<String, Object> classifierElementMap, ClassifierIdentifier identifier)
    {
        return (Map<String, Object>)classifierElementMap.get(DESCRIPTOR_MAP_KEY);
    }

    @SuppressWarnings("unchecked")
    public Descriptor getDescriptor(ClassifierIdentifier identifier)
    {
        if (StringUtils.isEmpty(identifier.getRuleSet()) ||
                StringUtils.isEmpty(identifier.getClassifierName()) ||
                StringUtils.isEmpty(identifier.getClassifierElementName()) ||
                StringUtils.isEmpty(identifier.getDescriptorName())
                )
        {
            log.debug("Param check failed:  {}", identifier);
            return null;
        }
        Map<String, Object> classifierMap = getClassifierMap( identifier );
        Map<String, Object> classifierElementMap = getClassifierElementMap(classifierMap, identifier);
        Map<String, Object> descriptorMap = getDescriptorMap(classifierElementMap, identifier);
        log.debug("descriptorMap:  {}", descriptorMap);
        return (Descriptor) descriptorMap.get( identifier.getDescriptorName() );
    }

    @SuppressWarnings("unchecked")
    private Map<String, Object> getClassifierElementMap(Map<String, Object> classifierMap, ClassifierIdentifier identifier)
    {
        Map<String, Object> classifierElementsMap = (Map<String, Object>)classifierMap.get( CLASSIFIER_ELEMENT_MAP_KEY );
        log.debug("classifierElementsMap:  {}", classifierElementsMap);
        return (Map<String, Object>)classifierElementsMap.get( identifier.getClassifierElementName() );
    }

    @SuppressWarnings("unchecked")
    public ClassifierElement getClassifierElement(ClassifierIdentifier identifier)
    {
        if (StringUtils.isEmpty(identifier.getRuleSet()) ||
                StringUtils.isEmpty(identifier.getClassifierName()) ||
                StringUtils.isEmpty(identifier.getClassifierElementName())
                )
        {
            log.debug("Param check failed:  {}", identifier);
            return null;
        }
        Map<String, Object> classifierMap = getClassifierMap( identifier );
        Map<String, Object> classifierElementMap = getClassifierElementMap(classifierMap, identifier);
        log.debug("classifierElementMap:  {}", classifierElementMap);
        return (ClassifierElement) classifierElementMap.get( CLASSIFIER_ELEMENT_KEY );
    }

    @SuppressWarnings("unchecked")
    private Map<String, Object> getClassifierMap(ClassifierIdentifier identifier)
    {
        return (Map<String, Object>)rulesClassifierMap.get(identifier.getRuleSet()).get(identifier.getClassifierName());
    }

    @SuppressWarnings("unchecked")
    public Classifier getClassifier(ClassifierIdentifier identifier)
    {
        if (StringUtils.isEmpty( identifier.getRuleSet() ) || StringUtils.isEmpty( identifier.getClassifierName() ) )
        {
            log.debug("Param check failed:  {}", identifier);
            return null;
        }
        Map<String, Object> classifierMap = getClassifierMap(identifier);
        return (Classifier) classifierMap.get(CLASSIFIER_KEY);
    }

//classifiers:
//    Ability, Ability, Abilities, Each entity has a number of scores that represent his entity's most basic attributes.  They represent raw talent and prowess
//        Strength, STR, Strength measures muscle and physical power.
//            Permanent Ability Score, Score, The ability score with all permanent modifications

    @SuppressWarnings("unchecked")
    public void loadClassifiers()
    {
        RULE_SYSTEMS.forEach( ruleSystem -> {
//            log.debug( "loadClassifiers:  {}", ruleSystem );
            Map<String, Object> classifierGraph = classifierService.retrieveClassifierGraph(ruleSystem);
//            log.debug( "classifierGraph retrieved:  {}", classifierGraph );
            if (null != classifierGraph)
            {
                rulesClassifierMap.put(ruleSystem, classifierGraph );
            }
        } );

//        if (log.isDebugEnabled()) {
//            log.debug("rule:");
//            rulesClassifierMap.forEach((rules, classifiersMap) -> {
//                log.debug("{}", rules);
//                log.debug("\tclassifiers:");
//                classifiersMap.forEach((classifiersMapKey, classifiersMapObject) -> {
//                    Map<String, Object> classifierMap = (Map<String, Object>) classifiersMapObject;
//                    Classifier classifier = (Classifier) classifierMap.get(CLASSIFIER_KEY);
//                    log.debug("\t\t{}, {}, {}, {}", classifiersMapKey, classifier.getShortName(), classifier.getPluralName(), classifier.getDescription());
//                    ((Map<String, Object>) classifierMap.get(CLASSIFIER_ELEMENT_MAP_KEY)).forEach((classifierElementsMapKey, classifierElementsMapObject) -> {
//                        Map<String, Object> classifiersElementMap = (Map<String, Object>) classifierElementsMapObject;
//                        ClassifierElement classifierElement = (ClassifierElement) classifiersElementMap.get(CLASSIFIER_ELEMENT_KEY);
//                        log.debug("\t\t\t{}, {}, {}", classifierElementsMapKey, classifierElement.getShortName(), classifierElement.getDescription());
//                        ((Map<String, Object>) classifiersElementMap.get(DESCRIPTOR_MAP_KEY)).forEach((descriptorKey, descriptorObj) -> {
//                            Descriptor descriptor = (Descriptor) descriptorObj;
//                            log.debug("\t\t\t\t{}, {}, {}", descriptorKey, descriptor.getShortName(), descriptor.getDescription());
//                        });
//                    });
//                });
//            });
//        }
    }



}
//MC7SK38VCB
