package br.com.sia.gymsystem.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.SequenceGenerator;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@SequenceGenerator(name="tb_savings_accounts", sequenceName = "tb_sq_savings_accounts", allocationSize = 1, initialValue = 1)
public class Cliente extends Pessoa{
    private Double altura;
    private Double peso;
    private Double medidaTorax;
    private Double medidaCintura;
    private Double medidaBraco;
    private Double medidaPerna;

    public Cliente(Long id, String nome, String cpf, LocalDate dataNascimento, Endereco endereco, Usuario usuario,
                   Double altura, Double peso, Double medidaTorax, Double medidaCintura, Double medidaBraco, Double medidaPerna) {
        super(id, nome, cpf, dataNascimento, endereco, usuario);
        this.altura = altura;
        this.peso = peso;
        this.medidaTorax = medidaTorax;
        this.medidaCintura = medidaCintura;
        this.medidaBraco = medidaBraco;
        this.medidaPerna = medidaPerna;
    }
}
