package com.estudo.prova.controllers;

import com.estudo.prova.dtos.usuario.NovaComanda;
import com.estudo.prova.dtos.usuario.RetornoComanda;
import com.estudo.prova.entities.Comanda;
import com.estudo.prova.entities.Produto;
import com.estudo.prova.entities.Usuario;
import com.estudo.prova.repositories.ComandaRepository;
import com.estudo.prova.repositories.ProdutoRepository;
import com.estudo.prova.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping(value = "/comandas")
public class ComandaController {
    @Autowired
    private ComandaRepository comandaRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private ProdutoRepository produtoRepository;

    @PostMapping
    public ResponseEntity<RetornoComanda> novaComanda(@RequestBody NovaComanda comanda) {
        Comanda nova = new Comanda();
        Usuario u = usuarioRepository.getReferenceById(comanda.getIdUsuario());
        if (u.getId() == null) {
            throw new RuntimeException("Falha ao encontrar usuario");
        }
        nova.setUsuario(u);
        System.out.println(nova.getUsuario().getId());
        Set<Produto> produtos = new HashSet<>();
        for (Produto produtoId : comanda.getProdutos()) {
            Produto produto = produtoRepository.findById(produtoId.getId()).orElseThrow(() -> new EntityNotFoundException("Produto não encontrado com ID: " + produtoId));
            produtos.add(produto);
        }
        nova.setProdutos(produtos);
        Comanda save = comandaRepository.save(nova);
        RetornoComanda dto = new RetornoComanda(save);

        return ResponseEntity.ok().body(dto);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<RetornoComanda> getComanda(@PathVariable Long id) {
        Comanda c = comandaRepository.getReferenceById(id);
        RetornoComanda dto = new RetornoComanda(c);
        return ResponseEntity.ok().body(dto);

    }

    @GetMapping
    public ResponseEntity<List<Usuario>> getComandas() {
        List<Usuario> usuarios = usuarioRepository.findAll();

        return ResponseEntity.ok().body(usuarios);
    }

//    @PutMapping(value = "/{id}")
//    public ResponseEntity<String> atualizarComanda(@PathVariable Long id, @RequestBody NovaComanda comanda){
//        Comanda c = new Comanda();
//        c.setId(id);
//        c.setProdutos(comanda.getProdutos());
//        comandaRepository.save();
//
//    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<String> deletarComanda(@PathVariable Long id) {

        Comanda comanda = comandaRepository.getReferenceById(id);
        comandaRepository.delete(comanda);
        return ResponseEntity.ok().body("{\\\"success\\\":{\\\"text\\\":\\\"\" + mensagem + \"\\\"}}");

    }


}
