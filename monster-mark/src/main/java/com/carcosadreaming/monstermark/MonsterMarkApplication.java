package com.carcosadreaming.rpg.monstermark;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories( basePackages = { "com.carcosadreaming.rpg.common.data.repository.jpa" } )
@EntityScan( basePackages = { "com.carcosadreaming.rpg.common.data.model.jpa" } )
public class MonsterMarkApplication
{

  public static void main( String[] args )
  {
    SpringApplication.run( MonsterMarkApplication.class, args );
  }
}
