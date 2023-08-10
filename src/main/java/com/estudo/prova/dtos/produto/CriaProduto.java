package com.estudo.prova.dtos.produto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CriaProduto {

    @NotNull(message = "É obrigatorio informar um nome!")
    @NotBlank(message = "É obrigatorio informar um nome!")
    private String nome;
    @NotNull(message = "É obrigatorio informar um preco!")
    @NotBlank(message = "É obrigatorio informar um preco!")
    private double preco;
        }
