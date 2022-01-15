package com.example.demo.entity;

import com.example.demo.service.enumeration.ResultadoEnum;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Getter
@Setter
public class Rodada implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name="partida_id")
    private Partida partida;

    @Enumerated(EnumType.STRING)
    private ResultadoEnum resultado;
}
