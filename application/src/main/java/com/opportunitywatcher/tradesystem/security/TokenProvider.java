package com.opportunitywatcher.tradesystem.security;

import com.opportunitywatcher.tradesystem.WebConfig;
import io.jsonwebtoken.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@Slf4j
public class TokenProvider {

    private WebConfig webConfig;

    @Autowired
    public TokenProvider(final WebConfig webConfig) {
        this.webConfig = webConfig;
    }

    public String createToken(Authentication authentication) {
        UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();

        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + webConfig.getAuth().getTokenExpirationMsec());

        return Jwts.builder()
                .setSubject(userPrincipal.getId())
                .setIssuedAt(new Date())
                .setExpiration(expiryDate)
                .signWith(SignatureAlgorithm.HS512, webConfig.getAuth().getTokenSecret())
                .compact();
    }

    public String getUserIdFromToken(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(webConfig.getAuth().getTokenSecret())
                .parseClaimsJws(token)
                .getBody();

        return claims.getSubject();
    }

    public boolean validateToken(String authToken) {
        try {
            Jwts.parser().setSigningKey(webConfig.getAuth().getTokenSecret()).parseClaimsJws(authToken);
            return true;
        } catch (SignatureException ex) {
            log.error("Invalid JWT signature",ex);
        } catch (MalformedJwtException ex) {
            log.error("Invalid JWT token,ex");
        } catch (ExpiredJwtException ex) {
            log.error("Expired JWT token",ex);
        } catch (UnsupportedJwtException ex) {
            log.error("Unsupported JWT token",ex);
        } catch (IllegalArgumentException ex) {
            log.error("JWT claims string is empty.",ex);
        }
        return false;
    }

}
