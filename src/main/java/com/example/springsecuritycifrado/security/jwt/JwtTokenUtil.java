package com.example.springsecuritycifrado.security.jwt;


import io.jsonwebtoken.*;
import io.jsonwebtoken.security.SignatureException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * Valida y genera los token jwt
 */

@Component
public class JwtTokenUtil {

    private Logger log = LoggerFactory.getLogger(JwtTokenUtil.class);

    @Value("${app.jwt.secret}")
    private String jwtSecret;


    @Value("${app.jwt.expiration-ms}")
    private int jwtExpirationMs;

    public String generateToken(Authentication authentication){
        UserDetails userPrincipal = (UserDetails) authentication.getPrincipal();

        return Jwts.builder()
                .setSubject(userPrincipal.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(new Date((new Date()).getTime() + jwtExpirationMs))
                .signWith(SignatureAlgorithm.HS256, jwtSecret).compact();
    }

    public String getUsernameFromToken(String token){
        return Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody().getSubject();
    }

    public boolean validateToken(String authToken){
        try{
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(authToken);
            return true;
        }
        catch(SignatureException e){
            log.error("Invalid signature: {}", e.getMessage());
        }
        catch (MalformedJwtException e){
            log.error("Invalid JWT token: {}", e.getMessage());
        }
        catch (ExpiredJwtException e){
            log.error("Tokem JWT expired: {}", e.getMessage());
        }
        catch (UnsupportedJwtException e){
            log.error("Token JWT is unsupported: {}", e.getMessage());
        }
        catch(IllegalArgumentException e){
            log.error("Claims string is empty: {}", e.getMessage());
        }
        return false;
    }
}
