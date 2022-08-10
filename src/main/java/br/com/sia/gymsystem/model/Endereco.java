package br.com.sia.gymsystem.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Endereco {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_endereco", unique = true, nullable = false)
    private Long id;
    private String estado;
    private String cidade;
    private String bairro;
    private String rua;
    @NotNull
    private int numero;
    private int complemento;

//    @OneToOne(mappedBy = "endereco")
//    @JsonIgnore
//    private Pessoa pessoa;
}
