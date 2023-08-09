package com.estudo.prova.dtos.comanda;


import com.estudo.prova.entities.Produto;
import lombok.*;

@Getter
@Setter
@Data
public class ProdutoRetorno {

    private Long id;
    private String nome;
    private Double preco;

    public ProdutoRetorno(Produto produto) {
        setId(produto.getId());
        setNome(produto.getNome());
        setPreco(produto.getPreco());
    }
}
