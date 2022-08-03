package br.com.sia.gymsystem.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Cliente extends Pessoa{
    private Double altura;
    private Double peso;
    private Double medidaTorax;
    private Double medidaCintura;
    private Double medidaBraco;
    private Double medidaPerna;
}
