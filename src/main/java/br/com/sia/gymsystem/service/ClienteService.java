package br.com.sia.gymsystem.service;

import br.com.sia.gymsystem.dto.ClienteDto;
import br.com.sia.gymsystem.model.Cliente;
import br.com.sia.gymsystem.repository.ClienteRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

@Service
public class ClienteService {

    @Autowired
    ClienteRepository clienteRepository;

    @Autowired
    ModelMapper modelMapper;

    public ClienteDto getDados(Long id) {
       Optional<Cliente> cliente = clienteRepository.findById(id);

       if(cliente.isEmpty()) {
           throw new EntityNotFoundException("Cliente não encontrado");
       }

       return modelMapper.map(cliente.get(), ClienteDto.class);
    }
}
