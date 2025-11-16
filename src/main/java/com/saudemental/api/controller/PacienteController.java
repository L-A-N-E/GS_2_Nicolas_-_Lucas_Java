package com.saudemental.api.controller;

import com.saudemental.api.dto.paciente.PacienteRequestDTO;
import com.saudemental.api.dto.paciente.PacienteResponseDTO;
import com.saudemental.api.service.PacienteService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pacientes")
public class PacienteController {

    @Autowired
    private PacienteService pacienteService;

    @PostMapping
    public ResponseEntity<PacienteResponseDTO> criar(@RequestBody @Valid PacienteRequestDTO dto) {
        PacienteResponseDTO novoPaciente = pacienteService.criar(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoPaciente);
    }

    @GetMapping
    public ResponseEntity<List<PacienteResponseDTO>> listarTodos() {
        List<PacienteResponseDTO> pacientes = pacienteService.listarTodos();
        return ResponseEntity.ok(pacientes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PacienteResponseDTO> buscarPorId(@PathVariable Long id) {
        PacienteResponseDTO paciente = pacienteService.buscarPorId(id);
        return ResponseEntity.ok(paciente);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PacienteResponseDTO> atualizar(@PathVariable Long id, @RequestBody @Valid PacienteRequestDTO dto) {
        PacienteResponseDTO pacienteAtualizado = pacienteService.atualizar(id, dto);
        return ResponseEntity.ok(pacienteAtualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        pacienteService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
