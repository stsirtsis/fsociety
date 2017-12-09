package gr.ntua.ece.softeng;

import org.springframework.data.repository.CrudRepository;
import gr.ntua.ece.softeng.Event;

public interface EventRepository extends CrudRepository<Event, Long> {
	
}
