package com.carcosadreaming.rpg.common.data.model;

import java.util.UUID;

public interface Datum extends CommonEntity {
    UUID getEntityId();

    UUID getFactorId();

    String getDatumValue();

    void setEntityId(UUID entityId);

    void setFactorId(UUID factorId);

    void setDatumValue(String datumValue);
}
