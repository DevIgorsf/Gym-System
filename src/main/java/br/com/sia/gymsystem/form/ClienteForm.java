package br.com.sia.gymsystem.form;

import br.com.sia.gymsystem.model.Usuario;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.UniqueElements;
import org.hibernate.validator.constraints.br.CPF;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Past;
import java.time.LocalDate;

@Setter
@Getter
public class ClienteForm {

    @NotBlank
    @Length(min = 3)
    private String nome;
    @CPF
    private String cpf;
    @NotBlank @Past @DateTimeFormat(iso= DateTimeFormat.ISO.DATE)
    private LocalDate dataNascimento;
    @NotBlank
    private String estado;
    @NotBlank
    private String cidade;
    @NotBlank
    private String bairro;
    @NotBlank
    private String rua;
    @NotBlank
    private int numero;
    private int complemento;
    @NotBlank
    private Usuario usuario;
    @NotBlank
    private Double altura;
    @NotBlank
    private Double peso;
    @NotBlank
    private Double medidaTorax;
    @NotBlank
    private Double medidaCintura;
    @NotBlank
    private Double medidaBraco;
    @NotBlank
    private Double medidaPerna;
    @NotBlank @UniqueElements
    private String username;
    @NotBlank
    private String password;

}
