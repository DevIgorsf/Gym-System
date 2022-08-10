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

        Optional<RoleModel> roleModel = roleModelRepository.findByRoleName(RoleName.CLIENTE);
        if(roleModel.isEmpty()) {
            RoleModel roleModelInicial = new RoleModel();
            roleModelInicial.setRoleNome(RoleName.INSTRUTOR);
            RoleModel roleModelSaved = roleModelRepository.save(roleModelInicial);
            usuario.setRoles(roleModelSaved);
        } else {
            usuario.setRoles(roleModel.get());
        }
        usuario.setUsername(form.getUsername());
        usuario.setPassword(form.getPassword());
        Usuario usuarioSaved = usuarioRepository.save(usuario);
        Cliente cliente = new Cliente();
        cliente.setEndereco(enderecoSaved);
        cliente.setUsuario(usuarioSaved);
        cliente.setCpf(form.getCpf());
        cliente.setDataNascimento(form.getDataNascimento());
        cliente.setNome(form.getNome());
        cliente.setAltura(form.getAltura());
        cliente.setMedidaTorax(form.getMedidaTorax());
        cliente.setMedidaCintura(form.getMedidaCintura());
        cliente.setMedidaBraco(form.getMedidaBraco());
        cliente.setMedidaPerna(form.getMedidaPerna());
        Cliente ClienteSaved = clienteRepository.save(cliente);

        return modelMapper.map(ClienteSaved, ClienteDto.class);
    }

    public ClienteDto atualizarCliente(Long id, ClienteForm form) {
        Optional<Cliente> cliente = clienteRepository.findById(id);

        if(cliente.isEmpty()) {
            throw new EntityNotFoundException("Cliente não encontrado");
        }
        Endereco endereco = cliente.get().getEndereco();
        if(!form.getEstado().equals(endereco.getEstado())) endereco.setEstado(form.getEstado());
        if(!form.getCidade().equals(endereco.getCidade())) endereco.setCidade(form.getCidade());
        if(!form.getBairro().equals(endereco.getBairro())) endereco.setBairro(form.getBairro());
        if(!form.getRua().equals(endereco.getRua())) endereco.setRua(form.getRua());
        if(form.getNumero() != endereco.getNumero()) endereco.setNumero(form.getNumero());
        if(form.getComplemento() != endereco.getComplemento()) endereco.setComplemento(form.getComplemento());
        if(!cliente.get().getEndereco().equals(endereco)) enderecoRepository.save(endereco);

        Usuario usuario = cliente.get().getUsuario();
        if(!form.getUsername().equals(usuario.getUsername())) usuario.setUsername(form.getUsername());
        if(!form.getPassword().equals(usuario.getPassword())) usuario.setPassword(form.getPassword());
        usuarioRepository.save(usuario);

        if(form.getAltura() != cliente.get().getAltura()) cliente.get().setAltura(form.getAltura());
        if(form.getMedidaTorax() != cliente.get().getMedidaTorax()) cliente.get().setMedidaTorax(form.getMedidaTorax());
        if(form.getMedidaCintura() != cliente.get().getMedidaCintura()) cliente.get().setMedidaCintura(form.getMedidaCintura());
        if(form.getMedidaBraco() != cliente.get().getMedidaBraco()) cliente.get().setMedidaBraco(form.getMedidaBraco());
        if(form.getMedidaPerna() != cliente.get().getMedidaPerna()) cliente.get().setMedidaPerna(form.getMedidaPerna());

        Cliente ClienteSaved = clienteRepository.save(cliente.get());

        return modelMapper.map(ClienteSaved, ClienteDto.class);
    }
}
