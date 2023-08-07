package com.estudo.prova.controllers;

import com.estudo.prova.dtos.usuario.NovoUsuario;
import com.estudo.prova.entities.Usuario;
import com.estudo.prova.exception.ConflitedException;
import com.estudo.prova.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @PostMapping
    public ResponseEntity<Usuario> createUser(@Validated @RequestBody NovoUsuario novoUsuario) {
        Usuario temp = usuarioRepository.findByNome(novoUsuario.getNome());

        if(temp != null){
            throw new ConflitedException("Já existe um usuario com esse nome!");
        }


        Usuario usuario = new Usuario(novoUsuario.getNome(), novoUsuario.getSenha(), novoUsuario.getTelefone());
        return ResponseEntity.ok().body(usuarioRepository.save(usuario));

    }

}
