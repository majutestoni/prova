    package com.estudo.prova.controllers;

    import com.estudo.prova.dtos.usuario.NovoUsuario;
    import com.estudo.prova.entities.Usuario;
    import com.estudo.prova.repositories.UsuarioRepository;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.http.ResponseEntity;
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
        public ResponseEntity<Usuario> createUser(@RequestBody NovoUsuario novoUsuario) {
            Usuario usuario = new Usuario();
            usuario.setNome(novoUsuario.getNome());
            usuario.setTelefoneUsuario(novoUsuario.getTelefone());
            Usuario novo = usuarioRepository.save(usuario);

            return ResponseEntity.ok().body(novo);

        }

    }
