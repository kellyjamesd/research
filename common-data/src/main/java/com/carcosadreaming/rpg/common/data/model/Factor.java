package com.carcosadreaming.rpg.common.data.model;

public interface Factor extends CommonEntity
{
  com.carcosadreaming.rpg.common.data.model.jpa.DescriptorJpaImpl getDescriptor();

  void setDescriptor( com.carcosadreaming.rpg.common.data.model.jpa.DescriptorJpaImpl descriptor );
}
