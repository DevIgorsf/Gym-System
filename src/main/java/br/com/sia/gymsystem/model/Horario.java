package br.com.sia.gymsystem.model;

import br.com.sia.gymsystem.enums.DiaDaSemana;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Horario {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "dia_da_semana")
    private DiaDaSemana diaDaSemana;
    @Column(name = "horario_entrada")
    private LocalTime horarioEntrada;
    @Column(name = "horario_saida")
    private LocalTime horarioSaida;

    public Horario(DiaDaSemana diaDaSemana, LocalTime horarioEntrada, LocalTime horarioSaida) {
        this.diaDaSemana = diaDaSemana;
        this.horarioEntrada = horarioEntrada;
        this.horarioSaida = horarioSaida;
    }
}
