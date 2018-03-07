package gr.ntua.ece.softeng.searchController;

import gr.ntua.ece.softeng.elasticEntity.EventElastic;
import gr.ntua.ece.softeng.elasticRepositories.EventElasticRepository;
import org.elasticsearch.action.ActionFuture;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.client.Requests;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.IndexQuery;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path = "/elasticsearch")
public class ElasticSearchController {

    @Autowired
    private ElasticsearchTemplate elasticsearchTemplate;

    @Autowired
    private EventElasticRepository eventElasticRepository;

    @PostMapping(path = "/create")
    public String create() {
//            @RequestBody Map<String, Object> jsonBody) {

        EventElastic event = new EventElastic(
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

        );

        EventElastic _event = new EventElastic(
                "1234567",
                "newpop",
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

        );


        elasticsearchTemplate
                .putMapping(EventElastic.class);
        IndexQuery indexQuery = new IndexQuery();
        indexQuery.setId(event.getId());
        indexQuery.setObject(event);
        elasticsearchTemplate.index(indexQuery);
        elasticsearchTemplate.refresh(EventElastic.class);

        eventElasticRepository.save(_event);


        return "ok";
    }

    @GetMapping(path = "/query")
    public String query(@RequestParam String text, @RequestParam int age, @RequestParam int price_low,
                        @RequestParam int price_high) {


//        try {
        Client client = elasticsearchTemplate.getClient();

        BoolQueryBuilder boolQuery = QueryBuilders.boolQuery();
        if (age != 0) {
            boolQuery.must(
                    QueryBuilders.termQuery("age", age)
            );
        }

        if(price_low <= price_high) {
            boolQuery.must(QueryBuilders.rangeQuery("price").from(price_low).to(price_high));
        }


        if (text != null) {
            boolQuery.must(
                    QueryBuilders.queryStringQuery(text) //search everywhere for the given text
            );
        }


        SearchSourceBuilder builder = new SearchSourceBuilder();
        builder
                .from(0)
                .size(10)
                .query(boolQuery);

        SearchRequest req;
        req = Requests
                .searchRequest("events")
                .source(builder);

        ActionFuture<SearchResponse> response = client.search(req);
        SearchHits searchHits = response.actionGet().getHits();
        SearchHit[] hits = searchHits.getHits();
        List<String> ids = new ArrayList<>();

        for (SearchHit hit : hits) {
            ids.add(hit.getId());
        }


        int hitsLength;
        hitsLength = hits.length;

        ids.forEach(System.out::println);

//        } catch(IOException e) {
//            throw new RuntimeException(e.getMessage(), e);
//        }


        return "ok";
    }

}
