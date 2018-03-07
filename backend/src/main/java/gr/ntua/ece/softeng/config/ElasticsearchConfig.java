package gr.ntua.ece.softeng.config;

import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;

import java.net.InetAddress;
import java.net.UnknownHostException;

@Configuration
public class ElasticsearchConfig {

    @Value("${cluster-name}")
    private String clusterName;
    @Value("${cluster.nodes}")
    private String clusterNodes;

    @Bean
    public ElasticsearchTemplate elasticsearchTemplate() throws UnknownHostException {
        String server = clusterNodes.split(":")[0];
        Integer port = Integer.parseInt(clusterNodes.split(":")[1]);
        Settings settings = Settings.builder()
                .put("cluster.name", clusterName).build();
        TransportClient client =  TransportClient.builder()
                .settings(settings)
                .build()
                .addTransportAddress(
                        new InetSocketTransportAddress(
                                InetAddress.getByName(server),
                                port
                        )
                );

        if (!indexExists(client, "events")) {
            createIndex(client, "events");
            addMapping(client, "events");

            client
                    .admin()
                    .cluster()
                    .prepareHealth()
                    .setWaitForYellowStatus()
                    .execute()
                    .actionGet();
        }

        return new ElasticsearchTemplate(client);
    }

    protected boolean indexExists(TransportClient client, String indexName) {
        return client
                .admin()
                .indices()
                .prepareExists(indexName)
                .execute()
                .actionGet()
                .isExists();
    }

    protected void createIndex(TransportClient client, String indexName) {

//        client.admin().indices().prepareCreate(indexName).setSettings([
//                refresh_interval      : refreshInterval,
//                number_of_shards      : numberOfShards,
//                number_of_replicas    : numberOfReplicas,
//                'index.mapper.dynamic': false,
//                analysis: [
//        analyzer: [
//        'default': [
//        tokenizer: 'standard',
//                type     : 'custom',
//                filter   : ['snowball', 'prefixFilter']
//                                ],
//        'case_insensitive_keyword': [
//        tokenizer: 'keyword',
//                type     : 'custom',
//                filter   : ['lowercase']
//                                ]
//                        ],
//        filter  : [
//        prefixFilter: [
//        type    : 'edgeNGram',
//                max_gram: 20,
//                min_gram: 3
//                                ]
//                        ]
//                ]
//        ]).execute().actionGet()
//
        client
                .admin()
                .indices()
                .prepareCreate(indexName)
                .execute()
                .actionGet();
    }

    protected void addMapping(TransportClient client, String indexName) {
        String json = "{\"properties\": { \"location\" : {\"type\" : \"geo_point\"} } }";
        client.admin()
                .indices()
                .preparePutMapping(indexName)
                .setType("eventelastic")
                .setSource(json)
                .execute()
                .actionGet();
    }


}

