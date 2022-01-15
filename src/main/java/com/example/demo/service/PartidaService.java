package com.example.demo.service;

import com.example.demo.entity.Partida;
import com.example.demo.exception.RegraNegocioException;
import com.example.demo.repository.PartidaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class PartidaService {

    private final PartidaRepository partidaRepository;


    @Transactional
    public Partida iniciarPartida(Partida partida) {
        partida.setId(null);
        partida.setQtdRodadas(0);
        return partidaRepository.save(partida);
    }

    public Partida getById(Long id) {
        return partidaRepository.findById(id).orElseThrow(() -> {
            throw new RegraNegocioException("Partida n\u00E3o encontrada.");
        });
    }

    public Partida atualizar(Partida partida) {
        return partidaRepository.save(partida);
    }
}
