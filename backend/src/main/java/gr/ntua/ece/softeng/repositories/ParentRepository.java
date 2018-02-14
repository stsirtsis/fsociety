package gr.ntua.ece.softeng.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import gr.ntua.ece.softeng.entities.Parent;


public interface ParentRepository extends JpaRepository<Parent, Long> {
	Parent findByUsername(String username);
}
