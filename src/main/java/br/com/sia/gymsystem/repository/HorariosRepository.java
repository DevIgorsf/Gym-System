package br.com.sia.gymsystem.repository;

import br.com.sia.gymsystem.dto.HorarioDto;
import br.com.sia.gymsystem.dto.HorarioFactory;
import br.com.sia.gymsystem.model.Horario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface HorariosRepository extends JpaRepository<Horario, Long> {

//    @Query(value = "SELECT i.nome, h.diaDaSemana, h.horarioEntrada, h.horarioSaida FROM instrutor i AND horario h " +
//            "AND instrutor_horarios j WHERE i.id = j.id AND j.id = h.id",
//            nativeQuery = true)
//    List<HorarioDto> findNomeHorario();

    @Query(value = "SELECT instrutor.nome, horario.dia_da_semana, horario.horario_entrada, horario.horario_saida FROM instrutor " +
            " JOIN instrutor_horarios ON instrutor.id = instrutor_horarios.instrutor_id JOIN horario ON " +
            " instrutor_horarios.horario_id = horario.id",
            nativeQuery = true)
    List<HorarioFactory> findNomeHorario();

//    List<HorarioDto> findByHorarios_id();

}
