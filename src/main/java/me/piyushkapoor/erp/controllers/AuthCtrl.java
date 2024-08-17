package me.piyushkapoor.erp.controllers;

import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import me.piyushkapoor.erp.config.JwtUtil;
import me.piyushkapoor.erp.config.UserPrincipal;
import me.piyushkapoor.erp.customExceptions.ResourceNotFound;
import me.piyushkapoor.erp.dtos.UserLoginDTO;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import java.util.*;

@RestController
public class AuthCtrl {

  AuthenticationManager autManager;
  JwtUtil jwtServ;
  UserDetailsService userDetailsService;

  AuthCtrl(AuthenticationManager autManager, JwtUtil jwtServ, UserDetailsService userDetailsService) {
    this.autManager = autManager;
    this.jwtServ = jwtServ;
    this.userDetailsService = userDetailsService;
  }

  @PostMapping("/auth/login")
  public Map<String, Object> login(@RequestBody @Valid UserLoginDTO requestBody) {
    Map<String, Object> res = new HashMap<>();
    Authentication auth = this.autManager
        .authenticate(new UsernamePasswordAuthenticationToken(requestBody.username, requestBody.password));
        if (auth.isAuthenticated()) {
      UserPrincipal usr = (UserPrincipal) userDetailsService.loadUserByUsername(requestBody.username);
      res.put("token", jwtServ.generate(usr.getUid()));
      return res;
    } else {
      throw new ResourceNotFound("Invalid username or password");
    }

  }

}
