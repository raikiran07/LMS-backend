package com.lms.security;

import java.security.Key;
import java.util.Date;

import javax.crypto.SecretKey;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import com.lms.exceptions.EmployeeException;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtTokenProvider {
	@Value("${app.jwt-secret}")
    private String jwtSecret;
    
    @Value("${app.expiration-milliseconds}")
    private long expirationDate;
    
    public Key key() {
		return Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtSecret));
    }
    
    
    public String generateToken(Authentication authentication) {
    	
    	String username = authentication.getName();
    	
    	Date currentDate = new Date();
    	Date expiryDate = new Date(currentDate.getTime()+expirationDate);
    	
		return Jwts.builder().subject(username)
				.issuedAt(currentDate).expiration(expiryDate)
				.signWith(key()).compact();
    	
    }
    
    
    public boolean validateToken(String token) {
        
        try {
            Jwts.parser().verifyWith((SecretKey) key()).build()
            .parse(token);
            return true;
            
        }
        catch(MalformedJwtException ex) {
            throw new EmployeeException(HttpStatus.BAD_REQUEST,"Invalid token");
        }
        catch(ExpiredJwtException ex) {
            throw new EmployeeException(HttpStatus.BAD_REQUEST,"Expired token");
        }
        catch(UnsupportedJwtException ex) {
            throw new EmployeeException(HttpStatus.BAD_REQUEST,"Unsupported token");
        }
        catch(IllegalArgumentException ex) {
            throw new EmployeeException(HttpStatus.BAD_REQUEST,"Claims string is null or empty");
        }
    }
    
    public String getUsername(String token) {
    	return Jwts.parser().verifyWith((SecretKey) key())
    			.build().parseSignedClaims(token).getPayload()
    			.getSubject();
    }
    
    

}
