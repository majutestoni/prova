package com.estudo.prova.dtos.produto;


import com.estudo.prova.entities.Produto;
import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ProdutoRetorno {

    private Long id;

    private String nome;

    private double preco;

    @JsonCreator
    public ProdutoRetorno(String nome, double preco){
        this.nome = nome;
        this.preco = preco;
    }

    public ProdutoRetorno(Produto produto) {
        setId(produto.getId());
        setNome(produto.getNome());
        setPreco(produto.getPreco());
    }
}
