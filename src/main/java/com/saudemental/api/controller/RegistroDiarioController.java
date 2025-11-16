package com.saudemental.api.controller;

import com.saudemental.api.dto.registrodiario.RegistroDiarioRequestDTO;
import com.saudemental.api.dto.registrodiario.RegistroDiarioResponseDTO;
import com.saudemental.api.service.RegistroDiarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/registros-diarios")
public class RegistroDiarioController {

    @Autowired
    private RegistroDiarioService registroDiarioService;

    @PostMapping
    public ResponseEntity<RegistroDiarioResponseDTO> criar(@RequestBody @Valid RegistroDiarioRequestDTO dto) {
        RegistroDiarioResponseDTO novoRegistro = registroDiarioService.criar(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoRegistro);
    }

    @GetMapping
    public ResponseEntity<List<RegistroDiarioResponseDTO>> listarTodos() {
        List<RegistroDiarioResponseDTO> registros = registroDiarioService.listarTodos();
        return ResponseEntity.ok(registros);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RegistroDiarioResponseDTO> buscarPorId(@PathVariable Long id) {
        RegistroDiarioResponseDTO registro = registroDiarioService.buscarPorId(id);
        return ResponseEntity.ok(registro);
    }

    @PutMapping("/{id}")
    public ResponseEntity<RegistroDiarioResponseDTO> atualizar(@PathVariable Long id, @RequestBody @Valid RegistroDiarioRequestDTO dto) {
        RegistroDiarioResponseDTO registroAtualizado = registroDiarioService.atualizar(id, dto);
        return ResponseEntity.ok(registroAtualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        registroDiarioService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
