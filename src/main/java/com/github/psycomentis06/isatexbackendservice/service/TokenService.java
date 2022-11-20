package com.github.psycomentis06.isatexbackendservice.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.util.Date;
import java.util.stream.Collectors;

@Service
public class TokenService {

    @Value("${jwt.public.key}")
    RSAPublicKey publicKey;

    @Value("${jwt.private.key}")
    RSAPrivateKey privateKey;


    @Value("${jwt.refresh-token.public.key}")
    RSAPublicKey refreshPublicKey;

    @Value("${jwt.refresh-token.private.key}")
    RSAPrivateKey refreshPrivateKey;

   public String generateAccessToken(Authentication authentication, String issuer) {
       Algorithm algorithm = Algorithm.RSA256(publicKey, privateKey);
       return generateToken(authentication, issuer, algorithm);
   }


    public String generateRefreshToken(Authentication authentication, String issuer) {
        Algorithm algorithm = Algorithm.RSA256(refreshPublicKey, refreshPrivateKey);
        return generateToken(authentication, issuer, algorithm);
    }

   public String generateToken(Authentication authentication, String issuer, Algorithm algorithm) {
       User user = (User) authentication.getPrincipal();
       return JWT.create()
               .withSubject(user.getUsername())
               .withExpiresAt(new Date(System.currentTimeMillis() + 10 * 60 * 1000))
               .withIssuer(issuer)
               .withClaim("roles",
                       user.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList())
               )
               .sign(algorithm);
   }
}
