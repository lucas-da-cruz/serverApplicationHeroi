package br.com.herois.repository;

import br.com.herois.model.entities.Heroi;
import br.com.herois.model.form.HeroiForm;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface HeroiRepository extends JpaRepository<Heroi, Long> {

    List<Heroi> findAll();

    Optional<Heroi> findById(Long id);

    Heroi save(HeroiForm heroi);

    List<Heroi> findByStatus(Boolean status);

    List<Heroi> findByStatusAndUsuarioAdminId(Boolean status, Long usuario_admin_id);

}
