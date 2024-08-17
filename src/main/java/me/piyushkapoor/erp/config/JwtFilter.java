package me.piyushkapoor.erp.config;

import java.io.IOException;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.security.SignatureException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import me.piyushkapoor.erp.customExceptions.ResourceNotFound;
import me.piyushkapoor.erp.repos.UserRepo;
import me.piyushkapoor.erp.repos.models.User;

@Component
public class JwtFilter extends OncePerRequestFilter {

  JwtUtil jwtUtil;
  UserRepo uRepo;
  CutsomeUserDetailsServ userDetailService;

  JwtFilter(JwtUtil jwtUtil, CutsomeUserDetailsServ userDetailService, UserRepo uRepo) {
    this.jwtUtil = jwtUtil;
    this.userDetailService = userDetailService;
    this.uRepo = uRepo;
  }

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
      throws ServletException, IOException {

    String header = request.getHeader("Authorization");
    if (header != null && SecurityContextHolder.getContext().getAuthentication() == null) {
      var token = header.split(" ")[1];
      try {
        if (token != null && jwtUtil.isTokenAlive(token)) {
          String userId = jwtUtil.getUserID(token);
          User usr = uRepo.findById(Long.parseLong(userId))
              .orElseThrow(() -> new ResourceNotFound("Incorrect Auth Token"));
          UserDetails userDetails = new UserPrincipal(usr);
          UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(userDetails, null,
              userDetails.getAuthorities());
          authToken.setDetails(new WebAuthenticationDetails(request));
          SecurityContextHolder.getContext().setAuthentication(authToken);
        }
      }
      catch (Exception e) {
            // Handle any other JWT-related exceptions
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.setContentType("application/json");
            response.getWriter().write("{ \"status\": \"failure\", \"message\": \"Inavlid jwt token found\" }");
            return;
        }

    }
    filterChain.doFilter(request, response);
  }

}
