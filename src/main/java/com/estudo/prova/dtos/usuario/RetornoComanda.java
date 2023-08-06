package com.estudo.prova.dtos.usuario;

import com.estudo.prova.entities.Comanda;
import com.estudo.prova.entities.Produto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RetornoComanda {
    private Long id;
    private Long idUsuario;
    private String nomeUsuario;
    private String telefoneUsuario;
    private ArrayList<Produto> produtos = new ArrayList<>();

    public RetornoComanda(Comanda p) {

        setId(p.getId());
        setIdUsuario(p.getUsuario().getId());
        setNomeUsuario(p.getUsuario().getNome());
        setTelefoneUsuario(p.getUsuario().getTelefoneUsuario());

        p.getProdutos().forEach(e -> {
            this.produtos.add(e);
        });


    }
}
