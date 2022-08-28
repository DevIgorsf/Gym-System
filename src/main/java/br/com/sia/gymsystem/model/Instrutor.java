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
    @JoinTable(joinColumns = @JoinColumn(name = "instrutor_id",
            foreignKey = @ForeignKey(name = "fk_instrutor_id", foreignKeyDefinition = "foreign key /* FK */ (instrutor_id) " +
                    " REFERENCES Instrutor (id) ON DELETE NO ACTION")),
            inverseJoinColumns = @JoinColumn(name = "horario_id", foreignKey = @ForeignKey(name = "fk_horario_id",
                    foreignKeyDefinition = "foreign key /* FK */ (horario_id) REFERENCES Horario (id) ON DELETE CASCADE")))
    private List<Horario> horarios = new ArrayList<>();

    public void setHorarios(Horario horario) {
        this.horarios.add(horario);
    }
}
