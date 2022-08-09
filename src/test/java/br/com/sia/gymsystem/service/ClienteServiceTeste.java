package br.com.sia.gymsystem.service;

import br.com.sia.gymsystem.dto.ClienteDto;
import br.com.sia.gymsystem.model.Cliente;
import br.com.sia.gymsystem.model.Endereco;
import br.com.sia.gymsystem.model.Usuario;
import br.com.sia.gymsystem.repository.ClienteRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

@SpringBootTest
class ClienteServiceTeste {

    private static final long ID = 1L;
    private static final String NOME = "Igor";
    private static final String CPF = "038.916.750-97";
    private static final LocalDate DATA_NASCIMENTO = LocalDate.parse("1998-09-13");
    private static final Endereco ENDERECO = new Endereco(1L, "test", "test", "mg", "test", 123, 564);
    private static final Usuario USUARIO = new Usuario();
    private static final Double ALTURA = 1.70;
    private static final Double PESO = 67.0;
    private static final Double MEDIDA_TORAX = 98.4;
    private static final Double MEDIDA_CINTURA = 78.3;
    private static final Double MEDIDA_BRACO = 32.1;
    private static final Double MEDIDA_PERNA = 54.7;

    @InjectMocks
    private ClienteService clienteService;

    @Mock
    private ClienteRepository clienteRepository;

    @Spy
    private ModelMapper modelMapper;

    private Cliente cliente;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        startCliente();
    }

    @Test
    void quandoBuscarUmClienteExistenteRetornarUmClienteDTO() {
        Mockito.when(clienteRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(cliente));

        ClienteDto clienteDto = clienteService.getDados(ID);

        Assertions.assertNotNull(clienteDto);
        Assertions.assertEquals(ID, clienteDto.getId());
        Assertions.assertEquals(NOME, clienteDto.getNome());
        Assertions.assertEquals(CPF, clienteDto.getCpf());
        Assertions.assertEquals(DATA_NASCIMENTO, clienteDto.getDataNascimento());
        Assertions.assertEquals(ENDERECO, clienteDto.getEndereco());
        Assertions.assertEquals(ALTURA, clienteDto.getAltura());
        Assertions.assertEquals(PESO, clienteDto.getPeso());
        Assertions.assertEquals(MEDIDA_CINTURA, clienteDto.getMedidaCintura());
        Assertions.assertEquals(MEDIDA_TORAX, clienteDto.getMedidaTorax());
        Assertions.assertEquals(MEDIDA_BRACO, clienteDto.getMedidaBraco());
        Assertions.assertEquals(MEDIDA_PERNA, clienteDto.getMedidaPerna());

    }

    @Test
    void quandoBuscarUmClienteNaoExistente() {

        try {
            ClienteDto clienteDto = clienteService.getDados(ID);
            fail("nao deu exception");
        } catch (EntityNotFoundException e) {
            assertEquals("Cliente não encontrado", e.getMessage());
        }
    }

    public void startCliente() {
        this.cliente = new Cliente(ID, NOME, CPF, DATA_NASCIMENTO, ENDERECO, USUARIO, ALTURA, PESO, MEDIDA_TORAX,
                MEDIDA_CINTURA, MEDIDA_BRACO, MEDIDA_PERNA);
    }
}
