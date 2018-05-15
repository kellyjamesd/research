package com.carcosadreaming.rpg.common.data.model.jpa;

import com.carcosadreaming.rpg.common.data.model.CommonEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.time.ZonedDateTime;
import java.util.UUID;

@Data
@MappedSuperclass
@EqualsAndHashCode( exclude = { "creationDate", "modifiedDate", "createdBy", "modifiedBy" } )
public class AbstractEntity implements CommonEntity
{
  @Id
  @GeneratedValue(generator = "uuid2")
  @GenericGenerator(name = "uuid2", strategy = "uuid2")
  @Column(name="id", nullable = false, updatable = false, columnDefinition = "BINARY(16)")
  private UUID id;

  @CreatedDate
  @Column(name="creation_date", nullable = false, updatable = false)
  private ZonedDateTime creationDate;

  @LastModifiedDate
  @Column(name="modified_date", nullable = false)
  private ZonedDateTime modifiedDate;

  @CreatedBy
  @Column(name="created_by")
  private String createdBy;

  @LastModifiedBy
  @Column(name="modified_by")
  private String modifiedBy;

  @PrePersist
  void prePersist()
  {
    modifiedDate = ZonedDateTime.now();
    creationDate = modifiedDate;
  }

  @PreUpdate
  void preUpdate()
  {
    modifiedDate = ZonedDateTime.now();
  }

}
