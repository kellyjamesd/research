package com.carcosadreaming.rpg.monstermark;

import com.carcosadreaming.rpg.common.data.model.Classifier;
import com.carcosadreaming.rpg.common.data.model.jpa.ClassifierJpaImpl;
import com.carcosadreaming.rpg.common.data.repository.jpa.ClassifierRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
@Slf4j
public class MonsterMarkApplication implements CommandLineRunner
{

  @Autowired
  ClassifierRepository classifierRepo;

  public static void main( String[] args )
  {
    SpringApplication.run( MonsterMarkApplication.class, args );
  }

  @Override public void run( String... args ) throws Exception
  {
    log.debug("Creating data");
    ClassifierJpaImpl cls = new ClassifierJpaImpl();
    cls.setName( "Ability" );
    ClassifierJpaImpl saved = classifierRepo.save(  cls );
    log.debug( "Saved:  {}", saved );

    log.debug("returning data");
    for (Classifier found : classifierRepo.findAll()) {
      log.error( "Classifier:  {}", found.toString() );
    }
  }

}
