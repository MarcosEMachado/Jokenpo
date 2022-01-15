package com.example.demo.resource;

import com.example.demo.entity.Partida;
import com.example.demo.service.PartidaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping(value = "/partida")
public class PartidaResouce {

    @Autowired
    private PartidaService partidaService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Partida> insert(@RequestBody Partida obj){
        Partida partida = partidaService.iniciarPartida(obj);
        return new ResponseEntity<>(partida, CREATED);
    }
}
