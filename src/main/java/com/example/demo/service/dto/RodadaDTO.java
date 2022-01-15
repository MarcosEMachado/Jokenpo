package com.example.demo.service.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class RodadaDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long idPartida;
    private int jokenpoEnum;

}
