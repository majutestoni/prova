package com.estudo.prova.dtos.comanda;

import com.estudo.prova.dtos.produto.ProdutoRetorno;
import com.estudo.prova.entities.Comanda;
import lombok.*;

import java.util.ArrayList;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RetornoComanda {
    private Long id;
    private Long idUsuario;
    private String nomeUsuario;
    private String telefoneUsuario;
    // deve retornar o valor total da comanda
    private double totalComanda = 0;
    private ArrayList<ProdutoRetorno> produtos = new ArrayList<>();

    public RetornoComanda(Comanda p) {

        setId(p.getId());
        setIdUsuario(p.getUsuario().getId());
        setNomeUsuario(p.getUsuario().getNome());
        setTelefoneUsuario(p.getUsuario().getTelefoneUsuario());


        p.getProdutos().forEach(produto -> {
            this.totalComanda += produto.getPreco();
            this.produtos.add(new ProdutoRetorno(produto));
        });



    }
}
