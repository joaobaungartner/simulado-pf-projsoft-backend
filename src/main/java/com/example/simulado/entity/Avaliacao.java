
package com.example.simulado.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "avaliacoes")
public class Avaliacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @Column(nullable = false)
    public String emailAvaliador;

    @Column(nullable = false)
    public String emailAvaliado;

    @Column(nullable = false)
    public String comentarios;

    @Column(nullable = false)
    public Integer nota;

    @Column(nullable = false)
    public LocalDateTime dataAvaliacao;

    @PrePersist
    public void antesDeSalvar() {
        dataAvaliacao = LocalDateTime.now();
    }
}
