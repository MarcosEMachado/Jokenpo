package com.example.demo.service;

import com.example.demo.entity.Partida;
import com.example.demo.entity.Rodada;
import com.example.demo.exception.RegraNegocioException;
import com.example.demo.repository.RodadaRepository;
import com.example.demo.service.dto.RodadaDTO;
import com.example.demo.service.enumeration.JokenpoEnum;
import com.example.demo.service.enumeration.ResultadoEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@Service
@RequiredArgsConstructor
public class RodadaService {

    @Autowired
    private RodadaRepository rodadaRepository;

    @Autowired
    private PartidaService partidaService;

    private Integer max = 3;
    private Integer mim = 1;

    @Transactional
    public Rodada salvar(Rodada rodada) {
        rodada.setId(null);
        return rodadaRepository.save(rodada);
    }

    @Transactional
    public ResultadoEnum salvarRodada(RodadaDTO rodadaDTO) {
        Partida partida = partidaService.getById(rodadaDTO.getIdPartida());
        if (partida.getQtdRodadas() < 10) {
            int jogadaMaquina = ThreadLocalRandom.current().nextInt(mim, max + mim);
            ResultadoEnum resultadoEnum = validarRodada(JokenpoEnum.toEnum(rodadaDTO.getJokenpoEnum()), JokenpoEnum.toEnum(jogadaMaquina));
            if (resultadoEnum != ResultadoEnum.EMPATE) {
                partida.setQtdRodadas(partida.getQtdRodadas() + 1);
                partidaService.atualizar(partida);
                Rodada rodada = new Rodada();
                rodada.setResultado(resultadoEnum);
                rodada.setPartida(partida);
                salvar(rodada);
            }
            return resultadoEnum;
        } else {
            throw new RegraNegocioException("M\u00e1ximo de rodadas por partida atingidas");
        }
    }

    public ResultadoEnum validarRodada(JokenpoEnum player, JokenpoEnum maquina) {
        if (player.getId() == maquina.getId()) {
            return ResultadoEnum.EMPATE;
        }
        if (player.getId() > maquina.getId() || (player == JokenpoEnum.PEDRA && maquina == JokenpoEnum.TESOURA)) {
            if (player == JokenpoEnum.TESOURA && maquina == JokenpoEnum.PEDRA) {
                return ResultadoEnum.MAQUINA;
            } else {
                return ResultadoEnum.PLAYER;
            }
        }
        return ResultadoEnum.MAQUINA;
    }

    public List<Rodada> getRodadasByIdPartida(Long idPartida) {
        List<Rodada> rodadas = rodadaRepository.findByPartidaId(idPartida);
        if (rodadas == null || rodadas.isEmpty()) {
            throw new RegraNegocioException("Rodadas n\u00E3o encontrada para o ID da partida:" + idPartida);
        }
        return rodadas;
    }

    public ResultadoEnum getWinner(Long idPartida) {
        List<Rodada> rodadas = getRodadasByIdPartida(idPartida);
        int player = 0;
        int maquina = 0;
        for (Rodada rodada: rodadas) {
            if (rodada.getResultado() == ResultadoEnum.PLAYER){
                player++;
            }else {
                maquina++;
            }
        }
        if(player > maquina){
            return ResultadoEnum.PLAYER;
        }else {
            return  ResultadoEnum.MAQUINA;
        }
    }
}
