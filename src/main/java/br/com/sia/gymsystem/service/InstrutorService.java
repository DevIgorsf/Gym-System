package br.com.sia.gymsystem.service;

import br.com.sia.gymsystem.dto.InstrutorDto;
import br.com.sia.gymsystem.enums.RoleName;
import br.com.sia.gymsystem.form.InstrutorForm;
import br.com.sia.gymsystem.model.*;
import br.com.sia.gymsystem.repository.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.persistence.EntityNotFoundException;
import java.util.Optional;

@Service
public class InstrutorService {

    @Autowired
    InstrutorRepository instrutorRepository;

    @Autowired
    EnderecoRepository enderecoRepository;

    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    RoleModelRepository roleModelRepository;

    @Autowired
    ModelMapper modelMapper;

    public InstrutorDto getDados(Long id) {
        Optional<Instrutor> instrutor = instrutorRepository.findById(id);

        if(instrutor.isEmpty()) {
            throw new EntityNotFoundException("Cliente não encontrado");
        }

        return modelMapper.map(instrutor.get(), InstrutorDto.class);
    }
    public InstrutorDto cadastrarInstrutor(InstrutorForm form) {
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
        roleModel.setRoleNome(RoleName.INSTRUTOR);
        RoleModel roleModelSaved = roleModelRepository.save(roleModel);
        usuario.setUsername(form.getUsername());
        usuario.setPassword(form.getPassword());
        usuario.setRoles(roleModelSaved);
        Usuario usuarioSaved = usuarioRepository.save(usuario);
        Instrutor instrutor = new Instrutor();
        instrutor.setEndereco(enderecoSaved);
        instrutor.setUsuario(usuarioSaved);
        Instrutor instrutorSaved = instrutorRepository.save(instrutor);

        return modelMapper.map(instrutorSaved, InstrutorDto.class);
    }
}
