package com.saudemental.api.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "T_REGISTRO_DIARIO")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegistroDiario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDate dataRegistro;

    @Column(nullable = false)
    private Integer nivelHumor; // 1 (muito ruim) a 5 (muito bom)

    @Column(nullable = false)
    private Integer nivelAnsiedade; // 1 (muito baixo) a 5 (muito alto)

    @Column(nullable = false)
    private Integer horasSono;

    @Column(length = 500)
    private String notas;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "paciente_id", nullable = false)
    private Paciente paciente;
}
