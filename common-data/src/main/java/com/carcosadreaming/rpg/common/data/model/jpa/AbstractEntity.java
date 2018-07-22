package com.carcosadreaming.rpg.common.data.model.jpa;

import com.carcosadreaming.rpg.common.data.model.CommonEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.time.ZonedDateTime;
import java.util.UUID;

@Data
@MappedSuperclass
@NoArgsConstructor
@ToString( exclude = { "creationDate", "modifiedDate", "createdBy", "modifiedBy" } )
@EqualsAndHashCode( exclude = { "creationDate", "modifiedDate", "createdBy", "modifiedBy" } )
public class AbstractEntity implements CommonEntity
{
  @Id
  @Column(name="id", nullable = false, updatable = false, columnDefinition = "BINARY(16)")
  private UUID id = UUID.randomUUID();

  @Column(name="system", nullable = false, updatable = false)
  private String system;

  @CreatedDate
  @Column(name="creation_date", nullable = false, updatable = false)
  @JsonIgnore
  private ZonedDateTime creationDate;

  @LastModifiedDate
  @Column(name="modified_date", nullable = false)
  @JsonIgnore
  private ZonedDateTime modifiedDate;

  @CreatedBy
  @Column(name="created_by")
  @JsonIgnore
  private String createdBy;

  @LastModifiedBy
  @Column(name="modified_by")
  @JsonIgnore
  private String modifiedBy;

  public AbstractEntity( String system )
  {
    this.system = system;
  }

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
