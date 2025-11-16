package com.saudemental.api.dto.paciente;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDate;

@Data
public class PacienteRequestDTO {

    @NotBlank(message = "O nome é obrigatório.")
    @Size(max = 100, message = "O nome deve ter no máximo 100 caracteres.")
    private String nome;

    @NotBlank(message = "O e-mail é obrigatório.")
    @Email(message = "Formato de e-mail inválido.")
    @Size(max = 100, message = "O e-mail deve ter no máximo 100 caracteres.")
    private String email;

    @Past(message = "A data de nascimento deve ser uma data passada.")
    private LocalDate dataNascimento;

    @NotBlank(message = "O telefone é obrigatório.")
    @Size(max = 15, message = "O telefone deve ter no máximo 15 caracteres.")
    private String telefone;
}
