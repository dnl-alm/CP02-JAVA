package br.com.cp02.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record BrinquedoCadastroDTO(
        @NotBlank
        String nome,

        @NotBlank
        String tipo,

        @NotBlank
        String classificacao,

        @NotBlank
        String tamanho,

        @NotNull
        @Positive
        Double preco
) {
}
