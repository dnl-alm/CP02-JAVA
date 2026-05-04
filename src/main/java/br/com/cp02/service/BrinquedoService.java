package br.com.cp02.service;

import br.com.cp02.dto.BrinquedoCadastroDTO;
import br.com.cp02.dto.BrinquedoListagemDTO;
import br.com.cp02.entity.Brinquedo;
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
    public BrinquedoCadastroDTO createBrinquedo(BrinquedoCadastroDTO dto) {
        Brinquedo brinquedo = new Brinquedo();
        brinquedo.setNome(dto.nome());
        brinquedo.setTipo(dto.tipo());
        brinquedo.setClassificacao(dto.classificacao());
        brinquedo.setTamanho(dto.tamanho());
        brinquedo.setPreco(dto.preco());

        Brinquedo brinquedoSalvo = brinquedoRepository.save(brinquedo);

        return new BrinquedoCadastroDTO(
                brinquedoSalvo.getNome(),
                brinquedoSalvo.getTipo(),
                brinquedoSalvo.getClassificacao(),
                brinquedoSalvo.getTamanho(),
                brinquedoSalvo.getPreco()
        );
    }

    @Transactional(readOnly = true)
    public List<BrinquedoListagemDTO> findAll() {
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
}
