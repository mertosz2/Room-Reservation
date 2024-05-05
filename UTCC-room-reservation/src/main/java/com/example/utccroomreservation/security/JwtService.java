package com.example.utccroomreservation.security;

import com.example.utccroomreservation.student.Students;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;
import io.jsonwebtoken.Claims;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class JwtService {

    private final String SECRET_KET ="as!@@!$DSA4asd5124@$%^@)sdxzxasd";

    public Claims extractAllClaims(String token){
        return (Claims) Jwts.parserBuilder()
                .setSigningKey(SECRET_KET.getBytes()).build()
                .parse(token)
                .getBody();
    }

    public<T> T extractClaim(String token, Function<Claims, T> claimsResolver){
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    public String extractUsername(String token){
        return extractClaim(token, Claims::getSubject);
    }

    public Date extractExpiration(String token){
        return extractClaim(token, Claims::getExpiration);
    }

    public boolean isTokenExpired(String token){
        return extractExpiration(token).before(new Date());
    }

    public String createToken(Map<String, Object> claims, String subject){
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(subject)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + TimeUnit.MINUTES.toMillis(60)))
                .signWith(Keys.hmacShaKeyFor(SECRET_KET.getBytes()), SignatureAlgorithm.HS256)
                .compact();
    }

    public String generateToken(Students students){
        Map<String, Object> claims = new HashMap<>();
        List<String> authorities = students.getAuthorities().stream().map(GrantedAuthority::getAuthority).toList();
        claims.put("authorities", authorities);
        return createToken(claims, students.getStudentNumber());
    }

    public List<GrantedAuthority> getAuthorities(String token){
        List<String> authorities = extractClaim(token, claims -> claims.get("authorities",List.class));
        return authorities.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList());
    }



}
