package com.estudo.prova.exception;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class MessageDto {
    private String key;
    private String message;

    public MessageDto(String key, String message) {
        this.key = key;
        this.message = message;
    }
}
