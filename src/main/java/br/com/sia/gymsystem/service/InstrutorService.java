package br.com.sia.gymsystem.service;

import br.com.sia.gymsystem.dto.HorarioDto;
import br.com.sia.gymsystem.dto.InstrutorDto;
import br.com.sia.gymsystem.enums.RoleName;
import br.com.sia.gymsystem.form.ClienteForm;
import br.com.sia.gymsystem.form.HorarioForm;
import br.com.sia.gymsystem.form.InstrutorForm;
import br.com.sia.gymsystem.model.*;
import br.com.sia.gymsystem.repository.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
    HorariosRepository horariosRepository;

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

        Optional<RoleModel> roleModel = roleModelRepository.findByRoleName(RoleName.INSTRUTOR);
        if(roleModel.isEmpty()) {
            RoleModel roleModelInicial = new RoleModel();
            roleModelInicial.setRoleNome(RoleName.INSTRUTOR);
            RoleModel roleModelSaved = roleModelRepository.save(roleModelInicial);
            usuario.setRoles(roleModelSaved);
        } else {
            usuario.setRoles(roleModel.get());
        }

        usuario.setUsername(form.getUsername());
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        usuario.setPassword(encoder.encode(form.getPassword()));

        Usuario usuarioSaved = usuarioRepository.save(usuario);
        Instrutor instrutor = new Instrutor();
        instrutor.setEndereco(enderecoSaved);
        instrutor.setUsuario(usuarioSaved);
        instrutor.setCpf(form.getCpf());
        instrutor.setDataNascimento(form.getDataNascimento());
        instrutor.setNome(form.getNome());
        Instrutor instrutorSaved = instrutorRepository.save(instrutor);

        return modelMapper.map(instrutorSaved, InstrutorDto.class);
    }

    public InstrutorDto atualizarInstrutor(Long id, ClienteForm form) {
        Optional<Instrutor> instrutor = instrutorRepository.findById(id);

        if(instrutor.isEmpty()) {
            throw new EntityNotFoundException("Cliente não encontrado");
        }
        Endereco endereco = instrutor.get().getEndereco();
        if(!form.getEstado().equals(endereco.getEstado())) endereco.setEstado(form.getEstado());
        if(!form.getCidade().equals(endereco.getCidade())) endereco.setCidade(form.getCidade());
        if(!form.getBairro().equals(endereco.getBairro())) endereco.setBairro(form.getBairro());
        if(!form.getRua().equals(endereco.getRua())) endereco.setRua(form.getRua());
        if(form.getNumero() != endereco.getNumero()) endereco.setNumero(form.getNumero());
        if(form.getComplemento() != endereco.getComplemento()) endereco.setComplemento(form.getComplemento());
        if(!instrutor.get().getEndereco().equals(endereco)) enderecoRepository.save(endereco);

        Usuario usuario = instrutor.get().getUsuario();
        if(!form.getUsername().equals(usuario.getUsername())) usuario.setUsername(form.getUsername());
        if(!form.getPassword().equals(usuario.getPassword())) usuario.setPassword(form.getPassword());
        usuarioRepository.save(usuario);

        Optional<Instrutor> instrutorSaved = instrutorRepository.findById(id);

        if(instrutorSaved.get().getHorarios().isEmpty()) {
            throw new EntityNotFoundException("Erro - Tente novamente");
        }
        return modelMapper.map(instrutorSaved.get(), InstrutorDto.class);
    }

    public HorarioDto RegistrarAula(HorarioForm form) {
        Optional<Instrutor> instrutor = instrutorRepository.findByNome(form.getNome());
        Horario horario = new Horario(form.getDiaDaSemana(), form.getHorarioEntrada(), form.getHorarioSaida());
        Horario horarioSaved = horariosRepository.save(horario);
        instrutor.get().setHorarios(horarioSaved);
        Instrutor instrutorSaved = instrutorRepository.save(instrutor.get());
        HorarioDto horarioDto = modelMapper.map(form, HorarioDto.class);
        return horarioDto;
    }

    public ResponseEntity ExcluirAula(Long id) {
        Optional<Horario> horario = horariosRepository.findById(id);

        if(horario.isEmpty()) {
            throw new EntityNotFoundException("Cliente não encontrado");
        }
        horariosRepository.delete(horario.get());

        return ResponseEntity.ok().build();
    }
}
