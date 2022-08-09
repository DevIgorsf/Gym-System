package br.com.sia.gymsystem.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Exercicio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_exercicio", unique=true, nullable = false)
    private Long id;
    private String nome;
    private int series;
    private int repeticoes;

    @ManyToOne
    @JoinColumn(name = "id", nullable = false, referencedColumnName = "id_ficha_treino")
    private FichaTreino fichaTreino;
}
