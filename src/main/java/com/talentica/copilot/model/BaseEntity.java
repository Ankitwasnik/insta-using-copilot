package com.talentica.copilot.model;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

// base entity for all entities
@Getter
@Setter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseEntity {

  // id of the entity
  @Id
  @GeneratedValue(strategy = jakarta.persistence.GenerationType.AUTO)
  @Setter(lombok.AccessLevel.NONE)
  private Long id;

  // time when entity was created
  @CreatedDate
  @Column(name = "created_time", nullable = false)
  private LocalDateTime createdTime;

  // time when entity was updated
  @LastModifiedDate
  @Column(name = "updated_time", nullable = false)
  private LocalDateTime updatedTime;

  // flag to store if entity is deleted
  @Column(name = "deleted", nullable = false)
  private boolean deleted;

  // time when entity was deleted
  @Column(name = "deleted_time")
  private LocalDateTime deletedTime;


}
