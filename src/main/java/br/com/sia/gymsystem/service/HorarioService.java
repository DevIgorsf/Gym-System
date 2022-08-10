package br.com.sia.gymsystem.service;

import br.com.sia.gymsystem.dto.HorarioDto;
import br.com.sia.gymsystem.model.Instrutor;
import br.com.sia.gymsystem.repository.HorariosRepository;
import br.com.sia.gymsystem.repository.InstrutorRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class HorarioService {

    @Autowired
    InstrutorRepository instrutorRepository;
    @Autowired
    HorariosRepository horariosRepository;
    @Autowired
    ModelMapper modelMapper;
    public List<HorarioDto> BuscarAulas() {
        List<Instrutor> instrutores = instrutorRepository.findAll();

        List<HorarioDto> horarioDto = instrutores.stream().map( horarios -> modelMapper.map(horarios.getHorarios(), HorarioDto.class)).collect(Collectors.toList());

        return horarioDto;
    }
}
