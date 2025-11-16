package com.saudemental.api.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "T_PROFISSIONAL_SAUDE")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProfissionalSaude {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String nome;

    @Column(nullable = false, unique = true, length = 100)
    private String email;

    @Column(nullable = false, unique = true, length = 50)
    private String registroProfissional; // Ex: CRP, CRM, etc.

    @Column(nullable = false, length = 50)
    private String especialidade;
}
