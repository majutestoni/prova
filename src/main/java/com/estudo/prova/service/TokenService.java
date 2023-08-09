package com.estudo.prova.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.estudo.prova.entities.Usuario;
import com.estudo.prova.repositories.UsuarioRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;

@Service
public class TokenService {

    private Long expiration = 10800l; // 3 horas

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Value("${jwt.secret}")
    private String secret;

//    public String generateToken(Authentication authentication) {
//        Usuario usuario = (Usuario) authentication.getPrincipal();
//        Usuario usuario = usuarioRepository.findByNome(authentication.getPrincipal();
//        String username = authentication.getName(); // Obtém o nome de usuário
//        Usuario usuario = usuarioRepository.findByNome(username); // Busca o usuário pelo nome

//        Date now = new Date();
//        Date exp = new Date(now.getTime() + expiration);
//
//        return Jwts.builder().setIssuer("Prova")
//                .setSubject(usuario.getId().toString())
//                .setIssuedAt(new Date())
//                .setExpiration(exp)
//                .signWith(SignatureAlgorithm.HS256, secret.getBytes()).compact();
//
//        return Jwts.builder().setIssuer("IRS")
//                .setSubject(usuario.getId().toString())
//                .setIssuedAt(new Date())
//                .setExpiration(exp)
//                .signWith(SignatureAlgorithm.ES256, secret).compact();

//    }

    public String generateToken(Usuario usuario) {
        return Jwts.builder()
                .setIssuer("Prova")
                .setSubject(usuario.getUsername())
                .claim("id", usuario.getId())
                .setExpiration(new Date(System.currentTimeMillis() + expiration))
                .signWith(SignatureAlgorithm.HS256, secret.getBytes())
                .compact();
    }


    public String getTokenId(String token) {
        Claims claims = Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
        return claims.getSubject();
    }


    public boolean isValidToken(String token) {
        try {
            Jwts.parser().setSigningKey(secret).parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }


}
