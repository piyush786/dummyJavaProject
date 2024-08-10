package me.piyushkapoor.erp.repos.models;

import org.springframework.stereotype.Component;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Component
@Entity
@Table(name="addresses")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Address extends Base {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="address_sequence_generator")
  @SequenceGenerator(name="address_sequence_generator", sequenceName = "address_sequence", initialValue = 1, allocationSize=1 )
  public Long id;

  @Column(nullable = false)
  public String city;

  @Column(nullable = false)
  public String state;

  @Column(nullable = false)
  public String pincode;
}
