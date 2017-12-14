package gr.ntua.ece.softeng;

import org.springframework.data.jpa.repository.JpaRepository;
import gr.ntua.ece.softeng.Event;

public interface EventRepository extends JpaRepository<Event, Long> {
	
}
