package me.piyushkapoor.erp.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class UserDTO {
  
  @NotBlank(message="name should not be blank or null")
  @NotNull(message="name should not be blank or null")
  public String name;

  @NotBlank(message="email should not be blank or null")
  @NotNull(message="email should not be blank or null")
  public String email;

  @NotBlank(message="age should not be blank or null")
  @NotNull(message="age should not be blank or null")
  public String age;

  @NotBlank(message="city should not be blank or null")
  @NotNull(message="city should not be blank or null")
  public String city;

  @NotBlank(message="state should not be blank or null")
  @NotNull(message="state should not be blank or null")
  public String state;

  @NotBlank(message="napincodeme should not be blank or null")
  @NotNull(message="pincode should not be blank or null")
  public String pincode;

  @NotBlank(message="Password should not be blank or null")
  @NotNull(message="Password should not be blank or null")
  @Size(min=3, max=32, message = "Password should be between 3 to 32 characters")
  public String password;

}
