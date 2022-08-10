package br.com.sia.gymsystem.service;


import br.com.sia.gymsystem.dto.HorarioFactory;
import br.com.sia.gymsystem.repository.HorariosRepository;
import br.com.sia.gymsystem.repository.InstrutorRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class HorarioService {

    @Autowired
    InstrutorRepository instrutorRepository;
    @Autowired
    HorariosRepository horariosRepository;
    @Autowired
    ModelMapper modelMapper;
    public List<HorarioFactory> BuscarAulas() {

        List<HorarioFactory> horarioDto = horariosRepository.findNomeHorario();

        if(horarioDto.isEmpty()) {
            throw new EntityNotFoundException("Horário não encontrado");
        }

        return horarioDto;
    }
}
