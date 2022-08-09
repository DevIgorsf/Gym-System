package br.com.sia.gymsystem.service;

import br.com.sia.gymsystem.dto.ClienteDto;
import br.com.sia.gymsystem.enums.RoleName;
import br.com.sia.gymsystem.form.ClienteForm;
import br.com.sia.gymsystem.model.Cliente;
import br.com.sia.gymsystem.model.Endereco;
import br.com.sia.gymsystem.model.RoleModel;
import br.com.sia.gymsystem.model.Usuario;
import br.com.sia.gymsystem.repository.ClienteRepository;
import br.com.sia.gymsystem.repository.EnderecoRepository;
import br.com.sia.gymsystem.repository.RoleModelRepository;
import br.com.sia.gymsystem.repository.UsuarioRepository;
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
    EnderecoRepository enderecoRepository;

    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    RoleModelRepository roleModelRepository;

    @Autowired
    ModelMapper modelMapper;

    public ClienteDto getDados(Long id) {
       Optional<Cliente> cliente = clienteRepository.findById(id);

       if(cliente.isEmpty()) {
           throw new EntityNotFoundException("Cliente não encontrado");
       }

       return modelMapper.map(cliente.get(), ClienteDto.class);
    }

    public ClienteDto cadastrarCliente(ClienteForm form) {
        Endereco endereco = new Endereco();
        endereco.setEstado(form.getEstado());
        endereco.setCidade(form.getCidade());
        endereco.setBairro(form.getBairro());
        endereco.setRua(form.getRua());
        endereco.setNumero(form.getNumero());
        if(form.getComplemento() != 0) endereco.setComplemento(form.getComplemento());
        Endereco enderecoSaved = enderecoRepository.save(endereco);
        Usuario usuario = new Usuario();
        RoleModel roleModel = new RoleModel();
        roleModel.setRoleNome(RoleName.CLIENTE);
        RoleModel roleModelSaved = roleModelRepository.save(roleModel);
        usuario.setUsername(form.getUsername());
        usuario.setPassword(form.getPassword());
        usuario.setRoles(roleModelSaved);
        Usuario usuarioSaved = usuarioRepository.save(usuario);
        Cliente cliente = new Cliente();
        cliente.setEndereco(enderecoSaved);
        cliente.setUsuario(usuarioSaved);
        cliente.setAltura(form.getAltura());
        cliente.setMedidaTorax(form.getMedidaTorax());
        cliente.setMedidaCintura(form.getMedidaCintura());
        cliente.setMedidaBraco(form.getMedidaBraco());
        cliente.setMedidaPerna(form.getMedidaPerna());
        Cliente ClienteSaved = clienteRepository.save(cliente);

        return modelMapper.map(ClienteSaved, ClienteDto.class);
    }
}
