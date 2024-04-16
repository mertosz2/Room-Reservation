package com.example.utccroomreservation.security;

import com.example.utccroomreservation.student.Students;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;
import io.jsonwebtoken.Claims;

import java.util.Date;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

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

    public String generateToken(Students students){
        String token = Jwts
                .builder()
                .setSubject(students.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + TimeUnit.MINUTES.toMillis(10)))
                .signWith(Keys.hmacShaKeyFor(SECRET_KET.getBytes()), SignatureAlgorithm.HS256)
                .compact();
        return token;
    }
}
