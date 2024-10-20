package com.ssspamqe.roomrent.security;

import com.ssspamqe.roomrent.security.entities.SecurityUserDetails;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtService {

    private JwtParser jwtParser;

    @Value("${jwt.secret-key}")
    private String jwtSigningKey;

    @Value("${jwt.time-to-live-ms}")
    private Long tokenTimeToLive;

    @PostConstruct
    private void init() {
        jwtParser = buildJwtParser();
    }

    private JwtParser buildJwtParser() {
        return Jwts.parser()
                .setSigningKey(getSigningKey())
                .build();
    }

    private Key getSigningKey() {
        byte[] keyBytes = Decoders.BASE64.decode(jwtSigningKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }


    private <T> T extractClaim(String token, Function<Claims, T> claimResolver) {
        var claims = extractAllClaims(token);
        return claimResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        return jwtParser.parseClaimsJws(token).getBody();
    }

    public boolean isTokenValid(String token, UserDetails userDetails) {
        var username = extractUsername(token);
        return username.equals(userDetails.getUsername()) && !isTokenExpired(token);
    }

    private boolean isTokenExpired(String token) {
        var currentDate = new Date();
        return extractExpiration(token).before(currentDate);
    }

    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }


    public String generateToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();

        if (userDetails instanceof SecurityUserDetails user) {
            claims.put("id", user.getId());
            claims.put("email", user.getEmail());
            claims.put("roles", user.getAuthorities());
        }
        return generateToken(claims, userDetails);
    }

    private String generateToken(Map<String, Object> extraClaims, UserDetails userDetails) {
        var currentTime = System.currentTimeMillis();
        var expirationTime = currentTime + tokenTimeToLive;

        return Jwts.builder().setClaims(extraClaims).setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(currentTime))
                .setExpiration(new Date(expirationTime))
                .signWith(getSigningKey(), SignatureAlgorithm.HS256)
                .compact();
    }

}
