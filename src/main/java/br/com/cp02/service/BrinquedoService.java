package br.com.cp02.service;

import br.com.cp02.dto.BrinquedoAtualizarDTO;
import br.com.cp02.dto.BrinquedoCadastroDTO;
import br.com.cp02.dto.BrinquedoListagemDTO;
import br.com.cp02.entity.Brinquedo;
import br.com.cp02.exception.IdNaoEncontradoException;
import br.com.cp02.repository.BrinquedoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class BrinquedoService {

    private final BrinquedoRepository brinquedoRepository;

    public BrinquedoService(BrinquedoRepository brinquedoRepository) {
        this.brinquedoRepository = brinquedoRepository;
    }

    @Transactional
    public BrinquedoListagemDTO createBrinquedo(BrinquedoCadastroDTO dto) {
        Brinquedo brinquedo = new Brinquedo();
        brinquedo.setNome(dto.nome());
        brinquedo.setTipo(dto.tipo());
        brinquedo.setClassificacao(dto.classificacao());
        brinquedo.setTamanho(dto.tamanho());
        brinquedo.setPreco(dto.preco());

        Brinquedo salvo = brinquedoRepository.save(brinquedo);

        return new BrinquedoListagemDTO(
                salvo.getId(),
                salvo.getNome(),
                salvo.getTipo(),
                salvo.getClassificacao(),
                salvo.getTamanho(),
                salvo.getPreco()
        );
    }

    @Transactional(readOnly = true)
    public List<BrinquedoListagemDTO> readAllBrinquedos() {
        return brinquedoRepository.findAll()
                .stream()
                .map(b -> new BrinquedoListagemDTO(
                        b.getId(),
                        b.getNome(),
                        b.getTipo(),
                        b.getClassificacao(),
                        b.getTamanho(),
                        b.getPreco()
                ))
                .toList();
    }

    @Transactional(readOnly = true)
    public BrinquedoListagemDTO readBrinquedoById(Long id) {
        Brinquedo brinquedo = brinquedoRepository.findById(id)
                .orElseThrow(() -> new IdNaoEncontradoException("Brinquedo não encontrado"));

        return new BrinquedoListagemDTO(
                brinquedo.getId(),
                brinquedo.getNome(),
                brinquedo.getTipo(),
                brinquedo.getClassificacao(),
                brinquedo.getTamanho(),
                brinquedo.getPreco()
        );
    }

    @Transactional
    public BrinquedoListagemDTO updateBrinquedo(Long id, BrinquedoAtualizarDTO dto) {
        Brinquedo brinquedo = brinquedoRepository.findById(id)
                .orElseThrow(() -> new IdNaoEncontradoException("Brinquedo não encontrado"));

        brinquedo.setNome(dto.nome());
        brinquedo.setTipo(dto.tipo());
        brinquedo.setClassificacao(dto.classificacao());
        brinquedo.setTamanho(dto.tamanho());
        brinquedo.setPreco(dto.preco());

        Brinquedo atualizado = brinquedoRepository.save(brinquedo);

        return new BrinquedoListagemDTO(
                atualizado.getId(),
                atualizado.getNome(),
                atualizado.getTipo(),
                atualizado.getClassificacao(),
                atualizado.getTamanho(),
                atualizado.getPreco()
        );
    }

    @Transactional
    public void deleteBrinquedo(Long id) {
        Brinquedo brinquedo = brinquedoRepository.findById(id)
                .orElseThrow(() -> new IdNaoEncontradoException("Brinquedo não encontrado"));
        brinquedoRepository.delete(brinquedo);
    }
}
