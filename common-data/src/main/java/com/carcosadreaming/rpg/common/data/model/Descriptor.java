package com.carcosadreaming.rpg.common.data.model;

import com.carcosadreaming.rpg.common.data.model.jpa.ClassifierElementJpaImpl;

public interface Descriptor extends Morpheme
{
  ClassifierElementJpaImpl getClassifierElement();

  void setClassifierElement( ClassifierElementJpaImpl classifierElement );
}
