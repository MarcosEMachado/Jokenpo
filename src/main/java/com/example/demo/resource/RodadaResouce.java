package com.example.demo.resource;

import com.example.demo.entity.Rodada;
import com.example.demo.service.RodadaService;
import com.example.demo.service.dto.RodadaDTO;
import com.example.demo.service.enumeration.ResultadoEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping(value = "/rodada")
public class RodadaResouce {

    @Autowired
    private RodadaService rodadaService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<ResultadoEnum> insert(@RequestBody RodadaDTO obj){
        ResultadoEnum resultadoEnum = rodadaService.salvarRodada(obj);
        return new ResponseEntity<>(resultadoEnum, CREATED);
    }

    @GetMapping(value = "/{idPartida}")
    public ResponseEntity<List<Rodada>> findAllByIdPartida(@PathVariable Long idPartida){
        List<Rodada> rodadas = rodadaService.getRodadasByIdPartida(idPartida);
        return ResponseEntity.ok().body(rodadas);
    }

    @GetMapping(value = "/getwinner/{idPartida}")
    public ResponseEntity<ResultadoEnum> findWiner(@PathVariable Long idPartida){
        ResultadoEnum result = rodadaService.getWinner(idPartida);
        return ResponseEntity.ok().body(result);
    }
}
