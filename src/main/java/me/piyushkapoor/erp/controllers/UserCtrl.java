package me.piyushkapoor.erp.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import me.piyushkapoor.erp.dtos.UserDTO;
import me.piyushkapoor.erp.repos.UserRepo;
import me.piyushkapoor.erp.repos.models.Address;
import me.piyushkapoor.erp.repos.models.User;

import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
public class UserCtrl {

  private UserRepo userRepo;
  private PasswordEncoder passwordEncoder;

  public UserCtrl(UserRepo userRepo, PasswordEncoder passwordEncoder) {
    this.userRepo = userRepo;
    this.passwordEncoder = passwordEncoder;
  }

  @GetMapping("/users")
  public List<User> getAll() {
    return userRepo.findAll();
  }

  @GetMapping("/user/:id")
  @ResponseStatus(code = HttpStatus.OK)
  public User getAll(@PathVariable Long id) {
    User user = userRepo.findById(id).orElse(null);
    return user;
  }

  @PostMapping("/user")
  @CrossOrigin
  @ResponseStatus(code = HttpStatus.OK)
  public User save(@Valid @RequestBody UserDTO user) {

    Optional<User> existingUser = userRepo.findByEmail(user.email);
    if (existingUser.isPresent()) {
      throw new RuntimeException("User Already Exist");
    }

    List<Address> adrList = new ArrayList<Address>();
    Address adr = Address.builder()
        .city(user.city)
        .state(user.state)
        .pincode(user.pincode)
        .build();
    adrList.add(adr);
    User u = User.builder()
        .age(user.age)
        .email(user.email)
        .name(user.name)
        .address(adrList)
        .password(passwordEncoder.encode(user.password))
        .build();

    return userRepo.save(u);
  }


}
