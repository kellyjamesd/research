package com.carcosadreaming.rpg.common.data;

import com.carcosadreaming.rpg.common.data.model.jpa.ClassifierJpaImpl;
import com.carcosadreaming.rpg.common.data.repository.jpa.ClassifierRepository;
import com.carcosadreaming.rpg.common.data.repository.jpa.DescriptorRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
@Slf4j
public class CommonDataApplication implements CommandLineRunner
{

  @Autowired
  ClassifierRepository classifierRepo;

  @Autowired
  DescriptorRepository descriptorRepo;

  public static void main( String[] args )
  {
    SpringApplication.run( CommonDataApplication.class, args );
  }

  @Override
  public void run ( String... args) throws Exception {
    ClassifierJpaImpl newClsfr = new ClassifierJpaImpl();
    newClsfr.setName( "Ability" );
    newClsfr.setShortName( "Ability" );
    newClsfr.setDescription( "Ability scores for an entity" );
    ClassifierJpaImpl saved = null;
    if (null != classifierRepo) {
      saved = classifierRepo.save( newClsfr );
      log.info( "saved:  {}", saved.toString() );
      for (ClassifierJpaImpl found : classifierRepo.findAll()) {
        log.info( "found:  {}, {}, {}", found.getId().getMostSignificantBits(), found.getId().getLeastSignificantBits(), found.toString() );
      }
    }
  }
}
