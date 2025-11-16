package com.saudemental.api.model;

import com.saudemental.api.model.enums.TipoRecurso;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "T_RECURSO_APOIO")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RecursoApoio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 150)
    private String titulo;

    @Column(nullable = false, length = 500)
    private String descricao;

    @Column(nullable = false, length = 255)
    private String url;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TipoRecurso tipo;
}
