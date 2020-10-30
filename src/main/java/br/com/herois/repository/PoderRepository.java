package br.com.herois.repository;

import br.com.herois.model.entities.Poder;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PoderRepository extends JpaRepository<Poder, Long> {

    List<Poder> findAll();

}
