package me.piyushkapoor.erp.config;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import me.piyushkapoor.erp.repos.models.User;

@Component
public class UserPrincipal implements UserDetails {
  User usr;
  UserPrincipal(User usr){
    this.usr = usr;
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return null;
  }

  @Override
  public String getPassword() {
    return usr.password;
  }

  @Override
  public String getUsername() {
    return usr.email;
  }

  public Long getUid() {
    return usr.id;
  }
  
}

