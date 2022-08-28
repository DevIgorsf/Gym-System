package br.com.sia.gymsystem.dto;

import br.com.sia.gymsystem.enums.DiaDaSemana;

public interface HorarioFactory {

    String getNome();
    DiaDaSemana getDia_da_semana();
    String getHorario_entrada();
    String getHorario_saida();

}
