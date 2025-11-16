package com.saudemental.api.service;

import com.saudemental.api.dto.registrodiario.RegistroDiarioRequestDTO;
import com.saudemental.api.dto.registrodiario.RegistroDiarioResponseDTO;
import com.saudemental.api.exception.RegistroDiarioNaoEncontradoException;
import com.saudemental.api.mapper.AppMapper;
import com.saudemental.api.model.Paciente;
import com.saudemental.api.model.RegistroDiario;
import com.saudemental.api.repository.RegistroDiarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RegistroDiarioService {

    @Autowired
    private RegistroDiarioRepository registroDiarioRepository;

    @Autowired
    private PacienteService pacienteService; // Para buscar o Paciente

    @Autowired
    private AppMapper mapper;

    public RegistroDiarioResponseDTO criar(RegistroDiarioRequestDTO dto) {
        Paciente paciente = pacienteService.buscarEntidadePorId(dto.getPacienteId());
        RegistroDiario registro = mapper.toEntity(dto, paciente);
        RegistroDiario salvo = registroDiarioRepository.save(registro);
        return mapper.toResponseDTO(salvo);
    }

    public List<RegistroDiarioResponseDTO> listarTodos() {
        return registroDiarioRepository.findAll().stream()
                .map(mapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    public RegistroDiarioResponseDTO buscarPorId(Long id) {
        RegistroDiario registro = registroDiarioRepository.findById(id)
                .orElseThrow(() -> new RegistroDiarioNaoEncontradoException("Registro Diário não encontrado com o ID: " + id));
        return mapper.toResponseDTO(registro);
    }

    public RegistroDiarioResponseDTO atualizar(Long id, RegistroDiarioRequestDTO dto) {
        RegistroDiario registro = registroDiarioRepository.findById(id)
                .orElseThrow(() -> new RegistroDiarioNaoEncontradoException("Registro Diário não encontrado para atualização com o ID: " + id));

        // Verifica se o Paciente existe (o ID do paciente não deve ser alterado em um PUT)
        Paciente paciente = pacienteService.buscarEntidadePorId(dto.getPacienteId());

        registro.setDataRegistro(dto.getDataRegistro());
        registro.setNivelHumor(dto.getNivelHumor());
        registro.setNivelAnsiedade(dto.getNivelAnsiedade());
        registro.setHorasSono(dto.getHorasSono());
        registro.setNotas(dto.getNotas());
        registro.setPaciente(paciente); // Garante que o paciente está correto

        RegistroDiario atualizado = registroDiarioRepository.save(registro);
        return mapper.toResponseDTO(atualizado);
    }

    public void deletar(Long id) {
        if (!registroDiarioRepository.existsById(id)) {
            throw new RegistroDiarioNaoEncontradoException("Registro Diário não encontrado para exclusão com o ID: " + id);
        }
        registroDiarioRepository.deleteById(id);
    }
}
