package gr.ntua.ece.softeng.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import gr.ntua.ece.softeng.entities.Event;

public interface EventRepository extends JpaRepository<Event, Long> {
  
}
