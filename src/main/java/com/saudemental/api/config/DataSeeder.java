package com.saudemental.api.config;

import com.saudemental.api.model.*;
import com.saudemental.api.model.enums.StatusConsulta;
import com.saudemental.api.model.enums.TipoRecurso;
import com.saudemental.api.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.Arrays;

@Component
public class DataSeeder implements CommandLineRunner {

    @Autowired
    private PacienteRepository pacienteRepository;

    @Autowired
    private ProfissionalSaudeRepository profissionalSaudeRepository;

    @Autowired
    private RegistroDiarioRepository registroDiarioRepository;

    @Autowired
    private ConsultaRepository consultaRepository;

    @Autowired
    private RecursoApoioRepository recursoApoioRepository;

    @Override
    public void run(String... args) throws Exception {
        seedProfissionais();
        seedPacientes();
        seedRegistrosDiarios();
        seedConsultas();
        seedRecursosApoio();
    }

    private void seedProfissionais() {
        ProfissionalSaude p1 = new ProfissionalSaude(null, "Dra. Ana Silva", "ana.silva@saude.com", "CRP-12345", "Psicóloga Clínica");
        ProfissionalSaude p2 = new ProfissionalSaude(null, "Dr. Bruno Costa", "bruno.costa@saude.com", "CRM-67890", "Psiquiatra");
        profissionalSaudeRepository.saveAll(Arrays.asList(p1, p2));
    }

    private void seedPacientes() {
        Paciente pac1 = new Paciente(null, "Carlos Eduardo", "carlos.eduardo@email.com", LocalDate.of(1995, Month.MAY, 15), "11987654321", null, null);
        Paciente pac2 = new Paciente(null, "Mariana Santos", "mariana.santos@email.com", LocalDate.of(2000, Month.JANUARY, 20), "21998765432", null, null);
        pacienteRepository.saveAll(Arrays.asList(pac1, pac2));
    }

    private void seedRegistrosDiarios() {
        Paciente pac1 = pacienteRepository.findById(1L).orElseThrow();
        Paciente pac2 = pacienteRepository.findById(2L).orElseThrow();

        RegistroDiario r1 = new RegistroDiario(null, LocalDate.now().minusDays(2), 3, 4, 6, "Dia estressante no trabalho.", pac1);
        RegistroDiario r2 = new RegistroDiario(null, LocalDate.now().minusDays(1), 4, 2, 8, "Me senti mais calmo hoje.", pac1);
        RegistroDiario r3 = new RegistroDiario(null, LocalDate.now(), 2, 5, 5, "Ansiedade muito alta, dificuldade para dormir.", pac2);

        registroDiarioRepository.saveAll(Arrays.asList(r1, r2, r3));
    }

    private void seedConsultas() {
        Paciente pac1 = pacienteRepository.findById(1L).orElseThrow();
        ProfissionalSaude prof1 = profissionalSaudeRepository.findById(1L).orElseThrow();

        Consulta c1 = new Consulta(null, LocalDateTime.now().plusDays(5).withHour(10).withMinute(0), StatusConsulta.AGENDADA, pac1, prof1);
        Consulta c2 = new Consulta(null, LocalDateTime.now().minusDays(10).withHour(14).withMinute(30), StatusConsulta.REALIZADA, pac1, prof1);

        consultaRepository.saveAll(Arrays.asList(c1, c2));
    }

    private void seedRecursosApoio() {
        RecursoApoio rec1 = new RecursoApoio(null, "Guia de Meditação para Iniciantes", "Técnicas simples para reduzir o estresse.", "https://link.meditacao.com", TipoRecurso.MEDITACAO);
        RecursoApoio rec2 = new RecursoApoio(null, "Artigo: O Impacto do Burnout", "Entenda os sinais e como prevenir o esgotamento profissional.", "https://link.artigo.com", TipoRecurso.ARTIGO);
        recursoApoioRepository.saveAll(Arrays.asList(rec1, rec2));
    }
}
