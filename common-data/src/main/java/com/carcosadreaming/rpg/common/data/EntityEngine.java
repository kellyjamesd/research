package com.carcosadreaming.rpg.common.data;

import com.carcosadreaming.rpg.common.data.model.Classifier;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor(onConstructor=@__({@Autowired}))
public class EntityEngine
{

    Map<String, Classifier> classifierMap = new HashMap<>();



}
