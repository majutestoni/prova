package com.estudo.prova.controllers;

import com.estudo.prova.dtos.usuario.LoginDto;
import com.estudo.prova.dtos.usuario.NovoUsuario;
import com.estudo.prova.dtos.usuario.TokenDto;
import com.estudo.prova.entities.Usuario;
import com.estudo.prova.exception.ConflitedException;
import com.estudo.prova.repositories.UsuarioRepository;
import com.estudo.prova.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.logging.Logger;


@RestController
@RequestMapping(value = "/usuario")
public class UsuarioController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @PostMapping
    public ResponseEntity<Usuario> createUser(@Validated @RequestBody NovoUsuario novoUsuario) {
        Usuario temp = usuarioRepository.findByNome(novoUsuario.getNome());

        if (temp != null) {
            throw new ConflitedException("Já existe um usuário com esse nome!");
        }


        String encryptedPassword = passwordEncoder.encode(novoUsuario.getSenha());

        Usuario usuario = new Usuario(novoUsuario.getNome(), encryptedPassword, novoUsuario.getTelefone());
        return ResponseEntity.ok().body(usuarioRepository.save(usuario));
    }

    @PostMapping(value = "/login")
    public ResponseEntity<TokenDto> login(@RequestBody @Validated LoginDto login) {
        Authentication authentication = new UsernamePasswordAuthenticationToken(login.getNome(), login.getSenha());
        Authentication authenticated = authenticationManager.authenticate(authentication);

        Usuario usuario = (Usuario) authenticated.getPrincipal();
        String token = tokenService.generateToken(usuario);

        return ResponseEntity.ok(TokenDto.builder().type("Bearer").token(token).build());
    }


}
