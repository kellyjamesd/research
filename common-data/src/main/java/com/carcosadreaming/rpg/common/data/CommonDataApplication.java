package com.carcosadreaming.rpg.common.data;

import com.carcosadreaming.rpg.common.data.model.jpa.ClassifierJpaImpl;
import com.carcosadreaming.rpg.common.data.model.jpa.ClassifierElementJpaImpl;
import com.carcosadreaming.rpg.common.data.model.jpa.DescriptorJpaImpl;
import com.carcosadreaming.rpg.common.data.repository.jpa.ClassifierRepository;
import com.carcosadreaming.rpg.common.data.repository.jpa.ClassifierElementRepository;
import com.carcosadreaming.rpg.common.data.repository.jpa.DescriptorRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.util.Assert;

import java.util.Arrays;
import java.util.Collection;

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
