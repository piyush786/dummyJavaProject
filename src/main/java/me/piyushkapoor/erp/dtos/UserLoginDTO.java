package me.piyushkapoor.erp.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class UserLoginDTO {
 
  @NotBlank(message="username should not be blank or null")
  @NotNull(message="username should not be blank or null")
  public String username;

  @NotBlank(message="Password should not be blank or null")
  @NotNull(message="Password should not be blank or null")
  @Size(min=3, max=32, message = "Password should be between 3 to 32 characters")
  public String password;

}
