package com.estudo.prova.controllers;

import com.estudo.prova.dtos.produto.CriaProduto;
import com.estudo.prova.dtos.produto.ProdutoRetorno;
import com.estudo.prova.entities.Produto;
import com.estudo.prova.exception.ConflitedException;
import com.estudo.prova.repositories.ProdutoRepository;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(value = "/produtos")
@SecurityRequirement(name = "Bearer Authorization")
public class ProdutoController {

    @Autowired
    private ProdutoRepository produtoRepository;

    @PostMapping
    public ResponseEntity<ProdutoRetorno> criarProduto(@Validated @RequestBody CriaProduto recebido) {


        Produto verifica = produtoRepository.findByNome(recebido.getNome());

        if (verifica != null) {
            throw new ConflitedException("Já existe um produto com esse nome!");
        }

        Produto novo = new Produto();

        novo.setNome(recebido.getNome());
        novo.setPreco(recebido.getPreco());

        ProdutoRetorno retorno = new ProdutoRetorno(produtoRepository.save(novo));

        return ResponseEntity.ok(retorno);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<ProdutoRetorno> getProduto(Long id) {
        Produto produto = produtoRepository.getOne(id);

        if (produto == null) {
            throw new ConflitedException("Não existe um produto com esse id!");
        }

        ProdutoRetorno retorno = new ProdutoRetorno(produto);

        return ResponseEntity.ok(retorno);
    }
}

