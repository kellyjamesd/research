package com.carcosadreaming.rpg.common.data.repository.jpa.service;

import com.carcosadreaming.rpg.common.data.model.Classifier;
import com.carcosadreaming.rpg.common.data.repository.jpa.ClassifierRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired ))
public class ClassifierServiceImpl
{
  private final ClassifierRepository classifierRepository;

  public List<Classifier> retrieveClassifiers()
  {
    return new ArrayList<>(classifierRepository.findAll());
  }
}
