package br.com.sia.gymsystem.form;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.UniqueElements;
import org.hibernate.validator.constraints.br.CPF;
import org.springframework.format.annotation.DateTimeFormat;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;
import java.time.LocalDate;

@Setter
@Getter
public class ClienteForm {

    @NotBlank
    @Length(min = 3)
    private String nome;
    @CPF
    private String cpf;
    @NotBlank @Past @Pattern(regexp = "^\\d{4}-\\d{2}-\\d{2}$")
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
