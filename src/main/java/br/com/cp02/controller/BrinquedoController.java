package br.com.cp02.controller;

import br.com.cp02.dto.BrinquedoAtualizarDTO;
import br.com.cp02.dto.BrinquedoCadastroDTO;
import br.com.cp02.dto.BrinquedoListagemDTO;
import br.com.cp02.service.BrinquedoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/brinquedos")
public class BrinquedoController {

    private final BrinquedoService service;

    public BrinquedoController(BrinquedoService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<BrinquedoListagemDTO> create(@Valid @RequestBody BrinquedoCadastroDTO dto) {
        BrinquedoListagemDTO novo = service.createBrinquedo(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(novo);
    }

    @GetMapping
    public ResponseEntity<List<BrinquedoListagemDTO>> read() {
        List<BrinquedoListagemDTO> brinquedos = service.readAllBrinquedos();
        return ResponseEntity.ok(brinquedos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BrinquedoListagemDTO> readById(@PathVariable Long id) {
        BrinquedoListagemDTO brinquedos = service.readBrinquedoById(id);
        return ResponseEntity.ok(brinquedos);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BrinquedoListagemDTO> update(@Valid @PathVariable Long id, @RequestBody BrinquedoAtualizarDTO dto) {
        BrinquedoListagemDTO atualizado = service.updateBrinquedo(id, dto);
        return ResponseEntity.ok(atualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.deleteBrinquedo(id);
        return ResponseEntity.noContent().build();
    }
}
