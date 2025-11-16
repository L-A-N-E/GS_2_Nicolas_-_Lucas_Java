package com.saudemental.api.mapper;

import com.saudemental.api.dto.paciente.PacienteRequestDTO;
import com.saudemental.api.dto.paciente.PacienteResponseDTO;
import com.saudemental.api.dto.registrodiario.RegistroDiarioRequestDTO;
import com.saudemental.api.dto.registrodiario.RegistroDiarioResponseDTO;
import com.saudemental.api.model.Paciente;
import com.saudemental.api.model.RegistroDiario;
import org.springframework.stereotype.Component;

@Component
public class AppMapper {

    // --- Paciente Mappers ---

    public Paciente toEntity(PacienteRequestDTO dto) {
        if (dto == null) return null;
        Paciente paciente = new Paciente();
        paciente.setNome(dto.getNome());
        paciente.setEmail(dto.getEmail());
        paciente.setDataNascimento(dto.getDataNascimento());
        paciente.setTelefone(dto.getTelefone());
        return paciente;
    }

    public PacienteResponseDTO toResponseDTO(Paciente paciente) {
        if (paciente == null) return null;
        PacienteResponseDTO dto = new PacienteResponseDTO();
        dto.setId(paciente.getId());
        dto.setNome(paciente.getNome());
        dto.setEmail(paciente.getEmail());
        dto.setDataNascimento(paciente.getDataNascimento());
        dto.setTelefone(paciente.getTelefone());
        return dto;
    }

    // --- RegistroDiario Mappers ---

    public RegistroDiario toEntity(RegistroDiarioRequestDTO dto, Paciente paciente) {
        if (dto == null || paciente == null) return null;
        RegistroDiario registro = new RegistroDiario();
        registro.setDataRegistro(dto.getDataRegistro());
        registro.setNivelHumor(dto.getNivelHumor());
        registro.setNivelAnsiedade(dto.getNivelAnsiedade());
        registro.setHorasSono(dto.getHorasSono());
        registro.setNotas(dto.getNotas());
        registro.setPaciente(paciente);
        return registro;
    }

    public RegistroDiarioResponseDTO toResponseDTO(RegistroDiario registro) {
        if (registro == null) return null;
        RegistroDiarioResponseDTO dto = new RegistroDiarioResponseDTO();
        dto.setId(registro.getId());
        dto.setPacienteId(registro.getPaciente().getId());
        dto.setDataRegistro(registro.getDataRegistro());
        dto.setNivelHumor(registro.getNivelHumor());
        dto.setNivelAnsiedade(registro.getNivelAnsiedade());
        dto.setHorasSono(registro.getHorasSono());
        dto.setNotas(registro.getNotas());
        return dto;
    }
}
