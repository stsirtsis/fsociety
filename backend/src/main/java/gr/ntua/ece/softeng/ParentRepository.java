package gr.ntua.ece.softeng;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import gr.ntua.ece.softeng.Parent;
import gr.ntua.ece.softeng.Event;;


public interface ParentRepository extends JpaRepository<Parent, Long> {
	Parent findByUsername(String username);
}
