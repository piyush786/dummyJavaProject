package me.piyushkapoor.erp.repos.models;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.stereotype.Component;
import jakarta.persistence.MappedSuperclass;
import lombok.Data;

@Component
@MappedSuperclass
@Data
public class Base {

  @CreationTimestamp
  public LocalDateTime createdAt;

  @UpdateTimestamp
  public LocalDateTime updatedAt;

  
}

