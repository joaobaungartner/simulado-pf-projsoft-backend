
package com.example.simulado.controller;

import com.example.simulado.dto.CriarAvaliacaoRequest;
import com.example.simulado.entity.Avaliacao;
import com.example.simulado.repository.AvaliacaoRepository;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/avaliacoes")
public class AvaliacaoController {

    private final AvaliacaoRepository repository;

    public AvaliacaoController(AvaliacaoRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<Avaliacao> listar() {
        return repository.findAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Avaliacao cadastrar(
            @Valid @RequestBody CriarAvaliacaoRequest request,
            @AuthenticationPrincipal Jwt jwt
    ) {
        String email = jwt.getClaimAsString(
                "https://api.prova-final.local/email"
        );

        if (email == null || email.isBlank()) {
            throw new org.springframework.web.server
                    .ResponseStatusException(
                    HttpStatus.UNAUTHORIZED,
                    "Email ausente no token"
            );
        }

        Avaliacao avaliacao = new Avaliacao();
        avaliacao.emailAvaliador = email;
        avaliacao.emailAvaliado = request.emailAvaliado();
        avaliacao.comentarios = request.comentarios();
        avaliacao.nota = request.nota();

        return repository.save(avaliacao);
    }
}
