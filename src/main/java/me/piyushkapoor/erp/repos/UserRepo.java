package me.piyushkapoor.erp.repos;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import me.piyushkapoor.erp.repos.models.User;

public interface UserRepo extends JpaRepository<User, Long> {
  
  Optional<User> findByEmail(String email);

  Optional<User> findById(Long id);
}
