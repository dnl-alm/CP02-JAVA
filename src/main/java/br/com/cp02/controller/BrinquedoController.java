package br.com.cp02.controller;

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
    public ResponseEntity<BrinquedoCadastroDTO> create(@Valid @RequestBody BrinquedoCadastroDTO dto) {
        BrinquedoCadastroDTO novo = service.createBrinquedo(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(novo);
    }

    @GetMapping
    public ResponseEntity<List<BrinquedoListagemDTO>> read() {
        List<BrinquedoListagemDTO> brinquedos = service.findAll();
        return ResponseEntity.ok(brinquedos);
    }
}
