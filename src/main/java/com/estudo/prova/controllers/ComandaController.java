package com.estudo.prova.controllers;

import com.estudo.prova.dtos.RetornoDto;
import com.estudo.prova.dtos.comanda.*;
import com.estudo.prova.dtos.produto.ProdutoRetorno;
import com.estudo.prova.entities.Comanda;
import com.estudo.prova.entities.Produto;
import com.estudo.prova.entities.Usuario;
import com.estudo.prova.exception.ConflitedException;
import com.estudo.prova.repositories.ComandaRepository;
import com.estudo.prova.repositories.ProdutoRepository;
import com.estudo.prova.repositories.UsuarioRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping(value = "/comandas")
@SecurityRequirement(name = "Bearer Authorization")
public class ComandaController {

    @Autowired
    private ComandaRepository comandaRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private ProdutoRepository produtoRepository;

    @Operation(summary = "Criação de comanda", description = "Informe os dados para a criação da comanda")
    @PostMapping
    public ResponseEntity<RetornoComanda> novaComanda(@Validated @RequestBody NovaComanda comanda) {
        Comanda nova = new Comanda();
        Usuario u = usuarioRepository.getOne(comanda.getIdUsuario());
        if (u == null) {
            throw new ConflitedException("É necessario informar um usuario valido!");
        }
        nova.setUsuario(u);
        Set<Produto> produtos = new HashSet<>();
        for (ProdutoParaComanda produtoId : comanda.getProdutos()) {
            Produto produto = produtoRepository.getOne(produtoId.getId());
            if (produto == null) {
                new ConflitedException("É necessario informar um produto valido!");
            }

            produtos.add(produto);
        }
        nova.setProdutos(produtos);

        Comanda save = comandaRepository.save(nova);
        RetornoComanda dto = new RetornoComanda(save);

        return ResponseEntity.ok().body(dto);
    }

    @Operation(summary = "Detalhes da comanda", description = "Consulta a comanda utilizando o id")
    @GetMapping(value = "/{id}")
    public ResponseEntity<RetornoComanda> getComanda(@PathVariable Long id) {
        Comanda c = comandaRepository.getOne(id);

        if (c == null) {
            throw new ConflitedException("É necessario informar uma comanda valido!");
        }
        RetornoComanda dto = new RetornoComanda(c);
        return ResponseEntity.ok().body(dto);
    }

    @Operation(summary = "Consultar comandas", description = "Retorna alguns todos de todas as comandas em lista")
    @GetMapping
    public ResponseEntity<List<RertonoComandaGet>> getComandas() {
        List<Comanda> comandas = comandaRepository.findAll();

        List<RertonoComandaGet> retorno = new ArrayList<>();

        comandas.forEach(c -> {
            retorno.add(new RertonoComandaGet(c));
        });


        return ResponseEntity.ok().body(retorno);
    }

    @Operation(summary = "Atualizar comanda", description = "deixar no json apenas os dados que gostaria de atualizar")
    @PutMapping(value = "/{id}")
    public ResponseEntity<RetornoDto> atualizarComanda(@PathVariable Long id, @RequestBody EditarComanda comanda) {
        Comanda comandaAtualizar = comandaRepository.getOne(id);

        if (comandaAtualizar == null) {
            throw new ConflitedException("É necessario informar uma comanda valido!");
        }

        if (comanda.getProdutos().size() > 0) {
            for (ProdutoParaComanda p : comanda.getProdutos()) {
                Produto produto = produtoRepository.getOne(p.getId());
                if (produto == null) {
                    new ConflitedException("É necessario informar um produto valido!");
                }
                comandaAtualizar.getProdutos().add(produto);
            }
        }

        if (comanda.getIdUsuario() != null) {
            Usuario u = usuarioRepository.getOne(comanda.getIdUsuario());

            if (u == null) {
                new ConflitedException("É necessario informar um usuario valido!");
            }

            comandaAtualizar.setUsuario(u);
        }

        comandaRepository.save(comandaAtualizar);

        return ResponseEntity.ok(new RetornoDto("Atualizado com sucesso!"));


    }

    @Operation(summary = "Deletar comanda", description = "Possivel deletar a comanda pelo id")
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<RetornoDto> deletarComanda(@PathVariable Long id) {

        Comanda comanda = comandaRepository.getOne(id);

        if (comanda == null) {
            throw new ConflitedException("É necessario informar uma comanda valido!");
        }
        comandaRepository.delete(comanda);
        return ResponseEntity.ok().body(new RetornoDto("Deletado com sucesso!"));

    }


}
