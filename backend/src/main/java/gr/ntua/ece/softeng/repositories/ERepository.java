package gr.ntua.ece.softeng.repositories;


import gr.ntua.ece.softeng.entities.E;
import java.util.List;
import org.springframework.data.mongodb.core.query.TextCriteria;
import org.springframework.data.mongodb.repository.MongoRepository;



public interface ERepository extends MongoRepository<E, String> {
	List<E> findAllByOrderByScoreDesc(TextCriteria criteria);
}