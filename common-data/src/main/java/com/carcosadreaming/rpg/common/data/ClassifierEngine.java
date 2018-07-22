package com.carcosadreaming.rpg.common.data;

import com.carcosadreaming.rpg.common.data.model.Classifier;
import com.carcosadreaming.rpg.common.data.model.ClassifierElement;
import com.carcosadreaming.rpg.common.data.model.Descriptor;
import com.carcosadreaming.rpg.common.data.repository.jpa.service.ClassifierServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor(onConstructor=@__({@Autowired}))
@Slf4j
public class ClassifierEngine
{

    private final ClassifierServiceImpl classifierService;

    private static final List<String> RULE_SYSTEMS = new ArrayList<String>( Arrays.asList( "PF1", "PF2", "OSRIC", "MA" ) );

    private Map<String, Map<String, Object>> rulesClassifierMap = new LinkedHashMap<>();

    @SuppressWarnings("unchecked")
    public void loadClassifiers()
    {
        RULE_SYSTEMS.forEach( ruleSystem -> {
            log.debug( "loadClassifiers:  {}", ruleSystem );
            Map<String, Object> classifierGraph = classifierService.retrieveClassifierGraph(ruleSystem);
            log.debug( "classifierGraph retrieved:  {}", classifierGraph );
            if (null != classifierGraph)
            {
                rulesClassifierMap.put(ruleSystem, classifierGraph );
            }
        } );

        if (log.isDebugEnabled()) {
            log.debug("rule:");
            rulesClassifierMap.forEach((rules, classifiersMap) -> {
                log.debug("{}", rules);
                log.debug("\tclassifiers:");
                classifiersMap.forEach((classifiersMapKey, classifiersMapObject) -> {
                    Map<String, Object> classifierMap = (Map<String, Object>) classifiersMapObject;
                    Classifier classifier = (Classifier) classifierMap.get("classifier");
                    log.debug("\t\t{}, {}, {}, {}", classifiersMapKey, classifier.getShortName(), classifier.getPluralName(), classifier.getDescription());
                    ((Map<String, Object>) classifierMap.get("classifier elements")).forEach((classifierElementsMapKey, classifierElementsMapObject) -> {
                        Map<String, Object> classifiersElementMap = (Map<String, Object>) classifierElementsMapObject;
                        ClassifierElement classifierElement = (ClassifierElement) classifiersElementMap.get("classifier element");
                        log.debug("\t\t\t{}, {}, {}", classifierElementsMapKey, classifierElement.getShortName(), classifierElement.getDescription());
                        ((Map<String, Object>) classifiersElementMap.get("descriptors")).forEach((descriptorKey, descriptorObj) -> {
                            Descriptor descriptor = (Descriptor) descriptorObj;
                            log.debug("\t\t\t\t{}, {}, {}", descriptorKey, descriptor.getShortName(), descriptor.getDescription());
                        });
                    });
                });
            });
        }
    }



}
//MC7SK38VCB
