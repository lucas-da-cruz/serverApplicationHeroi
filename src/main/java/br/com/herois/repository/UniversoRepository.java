package br.com.herois.repository;

import br.com.herois.model.entities.Universo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UniversoRepository extends JpaRepository<Universo, Long> {

    List<Universo> findAll();

}
