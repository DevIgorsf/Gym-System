package br.com.sia.gymsystem.service;

import br.com.sia.gymsystem.dto.HorarioDto;
import br.com.sia.gymsystem.dto.HorarioFactory;
import br.com.sia.gymsystem.repository.HorariosRepository;
import br.com.sia.gymsystem.repository.InstrutorRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.persistence.EntityNotFoundException;
import java.time.LocalTime;
import java.util.List;

@Service
public class HorarioService {

    @Autowired
    InstrutorRepository instrutorRepository;
    @Autowired
    HorariosRepository horariosRepository;
    @Autowired
    ModelMapper modelMapper;
    public List<HorarioDto> BuscarAulas() {

        List<HorarioFactory> horarioFactory = horariosRepository.findNomeHorario();

        if(horarioFactory.isEmpty()) {
            throw new EntityNotFoundException("Horário não encontrado");
        }

        List<HorarioDto> listHorarioDto = horarioFactory.stream().map( horario -> {
            return new HorarioDto(horario.getNome(), horario.getDia_da_semana(), LocalTime.parse(horario.getHorario_entrada())
                    , LocalTime.parse(horario.getHorario_saida()));
        } ).toList();

        return listHorarioDto;
    }
}
