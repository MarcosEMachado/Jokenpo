package com.example.demo.service.enumeration;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ResultadoEnum {
    EMPATE("E"),
    PLAYER("P"),
    MAQUINA("M");

    private String value;
}
