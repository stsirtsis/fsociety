package gr.ntua.ece.softeng;

import org.springframework.data.jpa.repository.JpaRepository;
import gr.ntua.ece.softeng.Parent;


public interface ParentRepository extends JpaRepository<Parent, Long> {
	Parent findByUsername(String username);
}
