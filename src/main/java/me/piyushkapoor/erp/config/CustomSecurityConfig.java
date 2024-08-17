package me.piyushkapoor.erp.config;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
@EnableAutoConfiguration
@Configuration
public class CustomSecurityConfig {
  
  CutsomeUserDetailsServ customUserDetailService ;
  JwtFilter jwtFilter;
  CustomSecurityConfig(CutsomeUserDetailsServ customUserDetailService, JwtFilter jwtFilter){
    this.customUserDetailService = customUserDetailService;
    this.jwtFilter = jwtFilter;
  }


  @Bean
  SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

    http.csrf(csrf->csrf.disable());
    http.authorizeHttpRequests(req->{
      req.requestMatchers("error", "user", "auth/*").permitAll();
      req.anyRequest().authenticated();
    });
    http.sessionManagement(sesion->sesion.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
    http.httpBasic(Customizer.withDefaults());
    http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
    return http.build();
  }

  @Bean
  AuthenticationManager authManager(AuthenticationConfiguration authConfig ) throws Exception {
    return authConfig.getAuthenticationManager();
  }

  @Bean
  DaoAuthenticationProvider dAuthenticationProvider() {
    var authProvider = new DaoAuthenticationProvider();
    authProvider.setPasswordEncoder(passwordEncoder());
    authProvider.setUserDetailsService(customUserDetailService);
    return authProvider;
  }

  @Bean
  PasswordEncoder passwordEncoder(){
    return new BCryptPasswordEncoder(12);
  }

}
