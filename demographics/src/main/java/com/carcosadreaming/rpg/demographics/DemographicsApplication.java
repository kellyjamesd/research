package com.carcosadreaming.rpg.demographics;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@SpringBootApplication
@Slf4j
public class DemographicsApplication
{

  private static List<SupportValue> sv = Arrays.asList(new TradeSupportValues().getSv());
  public static void main( String[] args )
  {
    ApplicationContext ctx = SpringApplication.run( DemographicsApplication.class, args );
    log.debug("SupportValues:");
    sv.forEach( supportValue ->  { if (supportValue.getActive())log.debug( supportValue.toString() ); } );
  }
}
