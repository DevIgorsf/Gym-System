package br.com.sia.gymsystem.dto;

import br.com.sia.gymsystem.enums.DiaDaSemana;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import java.time.LocalTime;

public class HorarioDto {


    private DiaDaSemana diaDaSemana;

    private LocalTime horarioEntrada;

    private LocalTime horarioSaida;
}
