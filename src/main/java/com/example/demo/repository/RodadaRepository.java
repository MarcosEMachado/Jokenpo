package com.example.demo.repository;

import com.example.demo.entity.Rodada;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RodadaRepository extends JpaRepository<Rodada, Long> {
    List<Rodada> findByPartidaId(Long id);
}
