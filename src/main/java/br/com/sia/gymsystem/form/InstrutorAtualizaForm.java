package br.com.sia.gymsystem.form;

import lombok.Getter;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.UniqueElements;
import org.hibernate.validator.constraints.br.CPF;
import org.springframework.format.annotation.DateTimeFormat;
import javax.validation.constraints.Past;
import java.time.LocalDate;

@Getter
public class InstrutorAtualizaForm {

    @Length(min = 3)
    private String nome;
    @CPF
    private String cpf;
    @Past
    @DateTimeFormat(iso= DateTimeFormat.ISO.DATE)
    private LocalDate dataNascimento;
    private String estado;
    private String cidade;
    private String bairro;
    private String rua;
    private int numero;
    private int complemento;
    @UniqueElements
    private String username;
    private String password;
}
