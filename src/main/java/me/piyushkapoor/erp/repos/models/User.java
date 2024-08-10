package me.piyushkapoor.erp.repos.models;

import java.util.List;

import org.springframework.stereotype.Component;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Component
@Entity
@Table(name="users")
@Inheritance(strategy = InheritanceType.JOINED)
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User extends Base {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="user_sequence_generator")
  @SequenceGenerator(name="user_sequence_generator", sequenceName = "user_sequence", initialValue = 1, allocationSize=1 )
  public Long id;

  @Column(nullable = false)
  public String name;

  @Column(nullable = false, unique = true)
  public String email;

  @Column(nullable = false)
  public String age;

  @OneToMany(cascade = CascadeType.ALL)
  @JoinTable(
    joinColumns = @JoinColumn(name="uid", referencedColumnName = "id"),
    inverseJoinColumns = @JoinColumn(name="aid", referencedColumnName = "id")
    
  )
  public List<Address> address;
}
