package com.saudemental.api.dto.paciente;

import lombok.Data;

import java.time.LocalDate;

@Data
public class PacienteResponseDTO {
    private Long id;
    private String nome;
    private String email;
    private LocalDate dataNascimento;
    private String telefone;
}
