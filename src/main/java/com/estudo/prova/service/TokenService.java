package com.estudo.prova.service;

import com.estudo.prova.entities.Usuario;
import com.estudo.prova.repositories.UsuarioRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class TokenService {

    private Long expiration = 10800l; // 3 horas

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Value("${jwt.secret}")
    private String secret;

    public String generateToken(Usuario usuario) {

        Date currentDate = new Date(System.currentTimeMillis());
        Date expirationDate = new Date(currentDate.getTime() + expiration * 1000);
//        return Jwts.builder().setIssuer("Prova").setSubject(usuario.getUsername()).claim("id", usuario.getId()).setExpiration(new Date(System.currentTimeMillis() + expiration)).signWith(SignatureAlgorithm.HS256, secret).compact();
        return Jwts.builder()
                .setIssuer("Prova")
                .setSubject(usuario.getUsername())
                .claim("id", usuario.getId())
                .setExpiration(expirationDate)
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();
    }


    public String getTokenId(String token) {
        Claims claims = Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
        return claims.get("id").toString();
    }


    public boolean isValidToken(String token) {
        try {
            Jwts.parser()
                    .setSigningKey(secret)
                    .setAllowedClockSkewSeconds(60) // Permitir desvio de 60 segundos
                    .parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }


}
