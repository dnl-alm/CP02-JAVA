package br.com.cp02.dto;

public record BrinquedoAtualizarDTO(
        String nome,
        String tipo,
        String classificacao,
        String tamanho,
        Double preco
) {
}
