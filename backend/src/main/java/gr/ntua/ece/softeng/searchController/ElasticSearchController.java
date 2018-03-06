package gr.ntua.ece.softeng.searchController;

import gr.ntua.ece.softeng.elasticEntity.EventElastic;
import gr.ntua.ece.softeng.elasticRepositories.EventElasticRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/elasticsearch")
public class ElasticSearchController {

    @Autowired
    private EventElasticRepository eventElasticRepository;

    @GetMapping(path = "/create")
    public String create() {
        eventElasticRepository.save(new EventElastic(
                "1234",
                "new",
                "new",
                "zografou",
                "papagou",
                17,
                1,
                42,
                17,
                1,
                "panbou",
                2.00016767,
                3.92398,
                "2018 - 04 - 21",
                "OPEN"

        ));
        return "ok";
    }

    @GetMapping(path="/all")
    public List<EventElastic> all() {
        return (List<EventElastic>) eventElasticRepository.findByCategory(1);
    }


}
