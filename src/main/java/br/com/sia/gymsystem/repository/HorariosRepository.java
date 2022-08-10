package br.com.sia.gymsystem.repository;

import br.com.sia.gymsystem.dto.HorarioDto;
import br.com.sia.gymsystem.model.Horario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface HorariosRepository extends JpaRepository<Horario, Long> {

//    @Query("SELECT i.nome, h.diaDaSemana, h.horarioEntrada, h.horarioSaida FROM Instrutor i AND Horario h WHERE f.id = h.id")
//    List<HorarioDto> findNomeHorario();

}
