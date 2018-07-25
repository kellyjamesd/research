package com.carcosadreaming.rpg.common.data;

import com.carcosadreaming.rpg.common.data.model.ClassifierIdentifier;
import com.carcosadreaming.rpg.common.data.model.Descriptor;
import com.carcosadreaming.rpg.common.data.model.Factor;
import com.carcosadreaming.rpg.common.data.model.Qualifier;
import com.carcosadreaming.rpg.common.data.model.jpa.FactorJpaImpl;
import com.carcosadreaming.rpg.common.data.model.jpa.QualifierJpaImpl;
import com.carcosadreaming.rpg.common.data.repository.jpa.FactorRepository;
import com.carcosadreaming.rpg.common.data.repository.jpa.QualifierRepository;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.util.Assert;


@NoArgsConstructor
public class QualifierBuilder
{
  @Setter
  private static ClassifierEngine classifierEngine;

  @Setter
  private static QualifierRepository qualifierRepo;

  @Setter
  private static FactorRepository factorRepo;

  @Setter
  private ClassifierIdentifier currentIdentifier;
  private Qualifier currentQualifier;
  private Descriptor qualifierDescriptor;
  private Descriptor factorDescriptor;
  private Factor currentFactor;

  public QualifierBuilder addQualifier(String qualifierName, String shortName, String description)
  {
    this.currentFactor = null;
    this.factorDescriptor = null;

    this.currentQualifier = qualifierRepo.save( new QualifierJpaImpl( qualifierName, shortName, description, getQualifierDescriptor() ) );
    return this;
  }

  public QualifierBuilder toDescriptor(String classifierName, String elementName, String descriptorName)
  {
    this.factorDescriptor = classifierEngine.getDescriptor( new ClassifierIdentifier( currentIdentifier.getRuleSet(), classifierName, elementName, descriptorName ) );
    return this;
  }

  public QualifierBuilder addFactor(String name, String shortName, String description)
  {
    currentFactor = factorRepo.save( new FactorJpaImpl( name, shortName, description, getCurrentQualifier(), getFactorDescriptor() ) );
    return this;
  }

  private Descriptor getFactorDescriptor()
  {
    Assert.notNull( this.factorDescriptor, "Descriptor for factor is not set" );
    return this.factorDescriptor;
  }

  private Descriptor getQualifierDescriptor()
  {
    if (null == qualifierDescriptor)
    {
      this.qualifierDescriptor = classifierEngine.getDescriptor( getIdentifier() );
    }
    return this.qualifierDescriptor;
  }

  private Qualifier getCurrentQualifier()
  {
    Assert.notNull( this.currentQualifier, "Qualifier not initialized" );
    return this.currentQualifier;
  }

  private ClassifierIdentifier getIdentifier()
  {
    Assert.notNull( this.currentIdentifier, "Identifier not initialized" );
    return this.currentIdentifier;
  }

  public QualifierBuilder and(String classifierName, String elementName, String descriptorName)
  {
    currentIdentifier.setClassifierName( classifierName );
    currentIdentifier.setClassifierElementName( elementName );
    currentIdentifier.setDescriptorName( descriptorName );
    return and();
  }

  public QualifierBuilder and() {
    this.currentFactor = null;
    this.currentQualifier = null;
    this.factorDescriptor = null;
    return this;
  }
}
