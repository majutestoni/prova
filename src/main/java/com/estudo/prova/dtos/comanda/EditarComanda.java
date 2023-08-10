package com.estudo.prova.dtos.comanda;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EditarComanda {

    private Long idUsuario;
    private ArrayList<ProdutoRetorno> produtos = new ArrayList<>();
}
