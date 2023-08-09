package com.estudo.prova.dtos.usuario;

import lombok.*;

@Builder
@Data
public class TokenDto {


    private String type;
    private String token;
}
