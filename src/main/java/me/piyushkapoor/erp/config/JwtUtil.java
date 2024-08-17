package me.piyushkapoor.erp.config;


import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import javax.crypto.SecretKey;

import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtUtil {
  private final String ISSUER = "ApplicatinName";
  private final SecretKey SecretKey = Keys.hmacShaKeyFor("2ZE[3T_mA^Ye|n@}FM'0uVDN.*BqYVC{z{OJ>Z*PR.#fN1P\"\"&)+0loyxaG^^tW".getBytes());

  public String generate(Long userId) {
    Date today  = new Date();
    return Jwts.builder()
      .subject(userId.toString())
      .issuer(ISSUER)
      .expiration(new Date(today.getTime()+ 60*60*24*1000))
      .signWith(SecretKey)
      .compact();
  }

  public String getUserID(String token) {
    return Jwts.parser().verifyWith(SecretKey).build().parseSignedClaims(token).getPayload().getSubject();
  }

  public Boolean isTokenAlive(String token) {
    return Jwts.parser().verifyWith(SecretKey).build().parseSignedClaims(token).getPayload().getExpiration().getTime() > new Date().getTime();
  }

}
