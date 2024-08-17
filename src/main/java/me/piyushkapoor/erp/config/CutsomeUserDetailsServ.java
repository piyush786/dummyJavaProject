package me.piyushkapoor.erp.config;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import me.piyushkapoor.erp.customExceptions.ResourceNotFound;
import me.piyushkapoor.erp.repos.UserRepo;
import me.piyushkapoor.erp.repos.models.User;

@Component
public class CutsomeUserDetailsServ implements UserDetailsService {

  UserRepo userRepo;
  CutsomeUserDetailsServ(UserRepo userRepo){
    this.userRepo = userRepo;
  }

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
      User usr = userRepo.findByEmail(username).orElseThrow(()->new ResourceNotFound("Username or password not correect"));
      return new UserPrincipal(usr);
  }
  
}
