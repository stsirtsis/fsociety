package gr.ntua.ece.softeng;


import gr.ntua.ece.softeng.E;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ERepository extends MongoRepository<E, String> {

	
	
}