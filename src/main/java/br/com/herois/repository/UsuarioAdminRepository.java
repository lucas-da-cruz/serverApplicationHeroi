package br.com.herois.repository;

import br.com.herois.model.entities.UsuarioAdmin;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UsuarioAdminRepository extends JpaRepository<UsuarioAdmin, Long> {

	Optional<UsuarioAdmin> findById(Long id);

	List<UsuarioAdmin> findAll();

	UsuarioAdmin save(UsuarioAdmin usuarioAdmin);

	void deleteById(Long id);
	
	Optional<UsuarioAdmin> findByEmail(String email);

}
