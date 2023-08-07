package com.estudo.prova.controllers;

import com.estudo.prova.dtos.RetornoDto;
import com.estudo.prova.dtos.comanda.NovaComanda;
import com.estudo.prova.dtos.comanda.RetornoComanda;
import com.estudo.prova.entities.Comanda;
import com.estudo.prova.entities.Produto;
import com.estudo.prova.entities.Usuario;
import com.estudo.prova.exception.ExceptionHandlerAdvice;
import com.estudo.prova.exception.MessageDto;
import com.estudo.prova.repositories.ComandaRepository;
import com.estudo.prova.repositories.ProdutoRepository;
import com.estudo.prova.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
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
    public ResponseEntity<RetornoComanda> novaComanda(@Validated @RequestBody NovaComanda comanda) {
        Comanda nova = new Comanda();
        Usuario u = usuarioRepository.getOne(comanda.getIdUsuario());
        if (u.getId() == null) {
// implementar retorno
        }
        nova.setUsuario(u);
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
        Comanda c = comandaRepository.getOne(id);
        RetornoComanda dto = new RetornoComanda(c);
        return ResponseEntity.ok().body(dto);
    }

    @GetMapping
    public ResponseEntity<List<Usuario>> getComandas() {
        List<Usuario> usuarios = usuarioRepository.findAll();

        return ResponseEntity.ok().body(usuarios);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<RetornoDto> atualizarComanda(@PathVariable Long id, @RequestBody RetornoComanda comanda) {
        Comanda comandaAtualizar = comandaRepository.getOne(id);

        if (comandaAtualizar.getId() == null) {
            // retorna erro
        }

        if (comanda.getProdutos().size() > 0) {

            comandaAtualizar.getProdutos().clear();
            for (Produto p : comanda.getProdutos()) {
                Produto produto = produtoRepository.findById(p.getId()).orElseThrow(() -> new EntityNotFoundException("Produto não encontrado com ID: " + p));
                comandaAtualizar.getProdutos().add(produto);
            }
        }

        if (comanda.getIdUsuario() != null) {
            Usuario u = usuarioRepository.getOne(comanda.getIdUsuario());

            if (u.getId() == null) {
                // retorna erro
            }

            comandaAtualizar.setUsuario(u);
        }

        if(comanda.getTelefoneUsuario() != null){
            Usuario u = usuarioRepository.getOne(comandaAtualizar.getUsuario().getId());
            u.setTelefoneUsuario(comanda.getTelefoneUsuario());
            usuarioRepository.save(u);
            comandaAtualizar.setUsuario(u);

        }


        comandaRepository.save(comandaAtualizar);

        return ResponseEntity.ok(new RetornoDto("Atualizado com sucesso"));



    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<RetornoDto> deletarComanda(@PathVariable Long id) {

        Comanda comanda = comandaRepository.getOne(id);
        comandaRepository.delete(comanda);
        return ResponseEntity.ok().body(new RetornoDto("Deletado com sucesso"));

    }


}
