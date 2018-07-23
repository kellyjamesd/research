package com.carcosadreaming.rpg.common.data;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
@Slf4j
public class CommonDataApplication implements CommandLineRunner
{

  public static void main( String[] args )
  {
    SpringApplication.run( CommonDataApplication.class, args );
  }

  @Override
  public void run ( String... args) throws Exception
  {
  }
}
