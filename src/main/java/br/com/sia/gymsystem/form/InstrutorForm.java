package br.com.sia.gymsystem.form;

import lombok.Getter;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.UniqueElements;
import org.hibernate.validator.constraints.br.CPF;
import org.springframework.format.annotation.DateTimeFormat;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;
import java.time.LocalDate;

@Getter
public class InstrutorForm {

    @NotBlank
    @Length(min = 3)
    private String nome;
    @CPF
    private String cpf;
    @NotBlank @Past
    @DateTimeFormat(iso= DateTimeFormat.ISO.DATE)
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
    @NotBlank @UniqueElements
    private String username;
    @NotBlank
    private String password;
}
