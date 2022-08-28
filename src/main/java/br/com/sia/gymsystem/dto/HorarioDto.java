package br.com.sia.gymsystem.dto;

import br.com.sia.gymsystem.enums.DiaDaSemana;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalTime;

@Getter
@AllArgsConstructor
public class HorarioDto {

    private String nome;
    private DiaDaSemana diaDaSemana;
    private LocalTime horarioEntrada;
    private LocalTime horarioSaida;
}
