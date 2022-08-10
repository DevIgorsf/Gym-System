package br.com.sia.gymsystem.repository;

import br.com.sia.gymsystem.model.Instrutor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface InstrutorRepository extends JpaRepository<Instrutor, Long> {

    Optional<Instrutor> findByNome(String nome);
}
