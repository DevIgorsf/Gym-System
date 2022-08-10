package br.com.sia.gymsystem.model;

import br.com.sia.gymsystem.enums.DiaDaSemana;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import java.time.LocalTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Horario {

    @Id
    private Long id;

    @Enumerated(EnumType.STRING)
    private DiaDaSemana diaDaSemana;

    private LocalTime horarioEntrada;

    private LocalTime horarioSaida;

    public Horario(DiaDaSemana diaDaSemana, LocalTime horarioEntrada, LocalTime horarioSaida) {
        this.diaDaSemana = diaDaSemana;
        this.horarioEntrada = horarioEntrada;
        this.horarioSaida = horarioSaida;
    }
}
