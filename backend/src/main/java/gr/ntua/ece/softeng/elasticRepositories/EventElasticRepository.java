package gr.ntua.ece.softeng.elasticRepositories;


import gr.ntua.ece.softeng.elasticEntity.EventElastic;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

public interface EventElasticRepository extends ElasticsearchRepository<EventElastic, String> {
    List<EventElastic> findByPriceBetween(int low, int high);
    List<EventElastic> findByAgeGroup(int low, int high);
    List<EventElastic> findByCategory(int cat);
}
