package com.estudo.prova.dtos.comanda;

import com.estudo.prova.entities.Comanda;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RertonoComandaGet {

    private Long idComanda;
    private String nomeUsuario;

    private int quantosProdutos;

    public RertonoComandaGet(Comanda c) {
        setIdComanda(c.getId());
        setNomeUsuario(c.getUsuario().getNome());
        setQuantosProdutos(c.getProdutos().size());
    }

}
