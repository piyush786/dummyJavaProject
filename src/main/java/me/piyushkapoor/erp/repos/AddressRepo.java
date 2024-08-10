package me.piyushkapoor.erp.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import me.piyushkapoor.erp.repos.models.Address;

public interface AddressRepo extends JpaRepository<Address, Long> {
  
}
