package com.carcosadreaming.rpg.common.data;

import com.carcosadreaming.rpg.common.data.model.ClassifierIdentifier;
import com.carcosadreaming.rpg.common.data.repository.jpa.FactorRepository;
import com.carcosadreaming.rpg.common.data.repository.jpa.QualifierRepository;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@NoArgsConstructor
public class QualifierBuilder
{
  @Setter
  private static ClassifierEngine classifierEngine;

  @Setter
  private static QualifierRepository qualifierRepo;

  @Setter
  private static FactorRepository factorRepo;

  private ClassifierIdentifier identifier;



  public QualifierBuilder and() { return this; }
}
