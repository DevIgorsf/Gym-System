package br.com.sia.gymsystem.dto;

import br.com.sia.gymsystem.enums.DiaDaSemana;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class HorarioDto {

    private String nome;
    private DiaDaSemana diaDaSemana;
    private LocalTime horarioEntrada;
    private LocalTime horarioSaida;
}
