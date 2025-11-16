package com.saudemental.api.dto.registrodiario;

import lombok.Data;

import java.time.LocalDate;

@Data
public class RegistroDiarioResponseDTO {
    private Long id;
    private Long pacienteId;
    private LocalDate dataRegistro;
    private Integer nivelHumor;
    private Integer nivelAnsiedade;
    private Integer horasSono;
    private String notas;
}
