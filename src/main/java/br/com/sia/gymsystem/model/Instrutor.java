package br.com.sia.gymsystem.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Instrutor extends Pessoa{

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(joinColumns = @JoinColumn(name = "instrutor_id"),
            inverseJoinColumns = @JoinColumn(name = "horarios_id"))
    private List<Horario> horarios = new ArrayList<>();

    public void setHorarios(Horario horario) {
        this.horarios.add(horario);
    }
}
