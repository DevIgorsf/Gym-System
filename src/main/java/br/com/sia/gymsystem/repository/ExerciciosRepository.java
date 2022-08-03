package br.com.sia.gymsystem.repository;

import br.com.sia.gymsystem.model.Exercicio;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExerciciosRepository extends JpaRepository<Exercicio, Long> {
}
