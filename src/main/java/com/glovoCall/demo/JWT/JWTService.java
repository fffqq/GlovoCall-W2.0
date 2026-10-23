package com.glovoCall.demo.JWT;

import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SecurityException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

@Component
public class JWTService {

    @Value("${app.jwt.secret_key}")
    private String JWTSecretKey;
    private final Logger
           logger= LoggerFactory.getLogger(JWTService.class);

    public JWTDTO generateAuthToken(String email){
        JWTDTO GenToken=new JWTDTO();
        GenToken.setToken(generateJWTToken(email));
        GenToken.setRefreshToken(generateJWTRefreshToken(email));
        return GenToken;
    }


    public String getEmailFromToken(String token){
        Claims claims=Jwts.parser().
                verifyWith(getJWTSecretKey(JWTSecretKey)).build().parseSignedClaims(token)
                .getPayload();
        return claims.getSubject();
    }
    public boolean validationOfJwtToken(String token){
        try{
            Jwts.parser()
                    .verifyWith(getJWTSecretKey(JWTSecretKey))
                    .build()
                    .parseSignedClaims(token);
            return true;
        }
        catch (ExpiredJwtException e){
            logger.error("JWT Token already expired{}",e.getMessage());
        }
        catch (SecurityException e){
            logger.error("Invalid signature{}",e.getMessage());
        }
        catch (MalformedJwtException e){
            logger.error("Invalid format of data{}",e.getMessage());
        }
        catch (JwtException e){
            logger.error("Invalid structure of JWT Token{}",e.getMessage());
        }
        catch (Exception e){
            logger.error("Unexpected Error{}",e.getMessage());
        }
        return false;
    }

    public JWTDTO refreshBaseToken(String email,String refreshToken){
        JWTDTO GenToken=new JWTDTO();
        GenToken.setToken(generateJWTToken(email));
        GenToken.setRefreshToken(refreshToken);
        return GenToken;
    }

    public  String generateJWTToken(String email){
        Date date=Date.from(Instant.now().plus(1, ChronoUnit.HOURS));
        return Jwts.builder().
                subject(email).
                expiration(date).
                signWith(getJWTSecretKey(JWTSecretKey))
                .compact();
    }
    public  String generateJWTRefreshToken(String email){
        Date date=Date.from(Instant.now().plus(1, ChronoUnit.DAYS));
        return Jwts.builder().
                subject(email).
                expiration(date).
                signWith(getJWTSecretKey(JWTSecretKey))
                .compact();
    }
    public SecretKey getJWTSecretKey(String JWTSecretKey){
        byte[] keyBytes= Decoders.BASE64.decode(JWTSecretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
