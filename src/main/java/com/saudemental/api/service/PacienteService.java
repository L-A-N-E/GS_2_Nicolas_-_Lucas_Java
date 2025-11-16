package com.saudemental.api.service;

import com.saudemental.api.dto.paciente.PacienteRequestDTO;
import com.saudemental.api.dto.paciente.PacienteResponseDTO;
import com.saudemental.api.exception.PacienteNaoEncontradoException;
import com.saudemental.api.mapper.AppMapper;
import com.saudemental.api.model.Paciente;
import com.saudemental.api.repository.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PacienteService {

    @Autowired
    private PacienteRepository pacienteRepository;

    @Autowired
    private AppMapper mapper;

    public PacienteResponseDTO criar(PacienteRequestDTO dto) {
        Paciente paciente = mapper.toEntity(dto);
        Paciente salvo = pacienteRepository.save(paciente);
        return mapper.toResponseDTO(salvo);
    }

    public List<PacienteResponseDTO> listarTodos() {
        return pacienteRepository.findAll().stream()
                .map(mapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    public PacienteResponseDTO buscarPorId(Long id) {
        Paciente paciente = pacienteRepository.findById(id)
                .orElseThrow(() -> new PacienteNaoEncontradoException("Paciente não encontrado com o ID: " + id));
        return mapper.toResponseDTO(paciente);
    }

    public PacienteResponseDTO atualizar(Long id, PacienteRequestDTO dto) {
        Paciente paciente = pacienteRepository.findById(id)
                .orElseThrow(() -> new PacienteNaoEncontradoException("Paciente não encontrado para atualização com o ID: " + id));

        paciente.setNome(dto.getNome());
        paciente.setEmail(dto.getEmail());
        paciente.setDataNascimento(dto.getDataNascimento());
        paciente.setTelefone(dto.getTelefone());

        Paciente atualizado = pacienteRepository.save(paciente);
        return mapper.toResponseDTO(atualizado);
    }

    public void deletar(Long id) {
        if (!pacienteRepository.existsById(id)) {
            throw new PacienteNaoEncontradoException("Paciente não encontrado para exclusão com o ID: " + id);
        }
        pacienteRepository.deleteById(id);
    }

    // Método auxiliar para uso interno (RegistroDiarioService)
    public Paciente buscarEntidadePorId(Long id) {
        return pacienteRepository.findById(id)
                .orElseThrow(() -> new PacienteNaoEncontradoException("Paciente não encontrado com o ID: " + id));
    }
}
