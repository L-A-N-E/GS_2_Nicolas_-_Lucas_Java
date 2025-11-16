package com.saudemental.api.dto.registrodiario;

import jakarta.validation.constraints.*;
import lombok.Data;

import java.time.LocalDate;

@Data
public class RegistroDiarioRequestDTO {

    @NotNull(message = "O ID do paciente é obrigatório.")
    private Long pacienteId;

    @NotNull(message = "A data de registro é obrigatória.")
    @PastOrPresent(message = "A data de registro não pode ser futura.")
    private LocalDate dataRegistro;

    @NotNull(message = "O nível de humor é obrigatório.")
    @Min(value = 1, message = "O nível de humor deve ser no mínimo 1.")
    @Max(value = 5, message = "O nível de humor deve ser no máximo 5.")
    private Integer nivelHumor; // 1 (muito ruim) a 5 (muito bom)

    @NotNull(message = "O nível de ansiedade é obrigatório.")
    @Min(value = 1, message = "O nível de ansiedade deve ser no mínimo 1.")
    @Max(value = 5, message = "O nível de ansiedade deve ser no máximo 5.")
    private Integer nivelAnsiedade; // 1 (muito baixo) a 5 (muito alto)

    @NotNull(message = "As horas de sono são obrigatórias.")
    @Min(value = 0, message = "As horas de sono devem ser no mínimo 0.")
    @Max(value = 24, message = "As horas de sono devem ser no máximo 24.")
    private Integer horasSono;

    @Size(max = 500, message = "As notas devem ter no máximo 500 caracteres.")
    private String notas;
}
