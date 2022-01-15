package com.example.demo.service.enumeration;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Objects;

@AllArgsConstructor
@Getter
public enum JokenpoEnum {
    PEDRA(1),
    PAPEL(2),
    TESOURA(3);

    private Integer id;

    public static JokenpoEnum toEnum(Integer cod) {

        for (JokenpoEnum enumeration : JokenpoEnum.values()) {
            if (Objects.equals(cod, enumeration.getId())) {
                return enumeration;
            }
        }
        return null;
    }
}
