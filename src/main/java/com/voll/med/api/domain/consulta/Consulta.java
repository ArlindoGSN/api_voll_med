package com.voll.med.api.domain.consulta;


import com.voll.med.api.domain.medico.Medico;
import com.voll.med.api.domain.paciente.Paciente;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Table(name = "consultas")
@Entity(name = "Consulta")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Consulta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "medico_id")
    private Medico medico;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "paciente_id")
    private Paciente paciente;

    private LocalDateTime data;

    @Enumerated(EnumType.STRING)
    private StatusConsulta status;
    @Column(name = "motivo_cancelamento")
    private String motivoCancelamento;

    public Consulta( Medico medico, Paciente paciente, LocalDateTime data) {
        this.medico = medico;
        this.paciente = paciente;
        this.data = data;
        this.status = StatusConsulta.AGENDADA;
    }



    public void cancelar(String motivo) {
        this.motivoCancelamento = motivo;
        this.status = StatusConsulta.CANCELADA;
    }

    public void confirmar() {
        this.status = StatusConsulta.REALIZADA;
    }

    public boolean podeSerCancelada() {
        if (!StatusConsulta.AGENDADA.equals(status)) {
            return false;
        }

        return LocalDateTime.now().minusHours(24).isBefore(this.data);
    }
}
