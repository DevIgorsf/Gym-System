package br.com.sia.gymsystem.form;

import br.com.sia.gymsystem.enums.DiaDaSemana;
import lombok.Getter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;
import java.time.LocalTime;

@Getter
public class HorarioForm {

    private String nome;

    private DiaDaSemana diaDaSemana;

    @NotBlank
    @Pattern(regexp = "^\\d{2}:\\d{2}$")
    private LocalTime horarioEntrada;

    @NotBlank
    @Pattern(regexp = "^\\d{2}:\\d{2}$")
    private LocalTime horarioSaida;
}
