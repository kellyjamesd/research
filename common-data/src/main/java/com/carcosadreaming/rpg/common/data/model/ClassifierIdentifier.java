package com.carcosadreaming.rpg.common.data.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ClassifierIdentifier {
    private String ruleSet;
    private String classifierName;
    private String classifierElementName;
    private String descriptorName;

    public void changeElementAndDescriptor(String classifierElementName, String descriptorName)
    {
        this.classifierElementName = classifierElementName;
        this.descriptorName = descriptorName;
    }

    public void changeClassifier(String classifierName, String classifierElementName, String descriptorName)
    {
        this.classifierName = classifierName;
        this.classifierElementName = classifierElementName;
        this.descriptorName = descriptorName;
    }
}
