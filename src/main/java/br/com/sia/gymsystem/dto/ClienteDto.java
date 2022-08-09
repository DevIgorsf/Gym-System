package br.com.sia.gymsystem.dto;

import br.com.sia.gymsystem.model.Endereco;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ClienteDto {
    private Long id;
    private String nome;
    private String cpf;
    private LocalDate dataNascimento;
    private Endereco endereco;
    private Double altura;
    private Double peso;
    private Double medidaTorax;
    private Double medidaCintura;
    private Double medidaBraco;
    private Double medidaPerna;
}
