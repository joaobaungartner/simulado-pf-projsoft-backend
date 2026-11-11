
package com.example.simulado.dto;

import jakarta.validation.constraints.*;

public record CriarAvaliacaoRequest(
        @NotBlank @Email String emailAvaliado,
        @NotBlank String comentarios,
        @NotNull @Min(0) @Max(10) Integer nota
) {}
