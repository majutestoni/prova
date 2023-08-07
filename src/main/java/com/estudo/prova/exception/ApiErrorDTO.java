package com.estudo.prova.exception;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class ApiErrorDTO {
    private Date timestamp;
    private Integer status;
    private String code;
    private Set<MessageDto> errors;


    public ApiErrorDTO(Date timestamp, Integer status, String code, Set<MessageDto> errors) {
        this.timestamp = timestamp;
        this.status = status;
        this.code = code;
        this.errors = errors;
    }

}