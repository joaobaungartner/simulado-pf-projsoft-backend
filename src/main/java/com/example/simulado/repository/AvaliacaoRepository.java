
package com.example.simulado.repository;

import com.example.simulado.entity.Avaliacao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AvaliacaoRepository
        extends JpaRepository<Avaliacao, Long> {
}
