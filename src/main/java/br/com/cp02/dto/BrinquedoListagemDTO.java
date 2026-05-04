package br.com.cp02.dto;

public record BrinquedoListagemDTO(
        Long id,
        String nome,
        String tipo,
        String classificacao,
        String tamanho,
        Double preco
) {
}
