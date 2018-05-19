package com.carcosadreaming.rpg.demographics;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SupportValue
{
  private String tradeName;
  private Integer supportValue;
  private Integer techLevel;
  private Boolean active;
}
