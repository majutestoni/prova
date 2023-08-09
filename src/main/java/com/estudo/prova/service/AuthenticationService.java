package com.estudo.prova.service;

import com.estudo.prova.entities.Usuario;
import com.estudo.prova.exception.ConflitedException;
import com.estudo.prova.repositories.UsuarioRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class AuthenticationService implements UserDetailsService {

    private final String SECRET_KEY = "maju"; // Replace with your secret key
    private final long EXPIRATION_TIME = 5 * 60 * 60;

    @Autowired
    private UsuarioRepository usuarioRepository;


    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepository.findByNome(s);

        if (usuario != null) {
            return usuario;
        }
        throw new UsernameNotFoundException("User not found");
    }
}
