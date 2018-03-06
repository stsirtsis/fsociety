package gr.ntua.ece.softeng.config;

import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.node.NodeBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

import java.io.File;
import java.io.IOException;
import java.net.InetAddress;

@Configuration

//@EnableElasticsearchRepositories("gr.ntua.ece.softeng.elasticRepositories")

public class ElasticSearchConfig {

//    @Bean
//    public NodeBuilder nodeBuilder() {
//        return new NodeBuilder();
//    }

//    public Client client() throws Exception {
//
//        Settings esSettings = Settings.settingsBuilder()
//                .put("cluster.name", "elasticsearch")
//                .build();
//
//        // https://www.elastic.co/guide/en/elasticsearch/guide/current/_transport_client_versus_node_client.html
//        return TransportClient.builder()
//                .settings(esSettings)
//                .build()
//                .addTransportAddress(
//                        new InetSocketTransportAddress(InetAddress.getByName("localhost"), 9300));
//    }
//
//    @Bean
//    public ElasticsearchOperations elasticsearchTemplate() throws IOException {
//        File tmpDir = File.createTempFile("elastic", Long.toString(System.nanoTime()));
//        System.out.println("Temp directory: " + tmpDir.getAbsolutePath());
//        Settings.Builder elasticsearchSettings =
//                Settings.settingsBuilder()
//                        .put("http.enabled", "true") // 1
//                        .put("index.number_of_shards", "1")
//                        .put("path.data", new File(tmpDir, "data").getAbsolutePath()) // 2
//                        .put("path.logs", new File(tmpDir, "logs").getAbsolutePath()) // 2
//                        .put("path.work", new File(tmpDir, "work").getAbsolutePath()) // 2
//                        .put("path.home", tmpDir); // 3
//
//
//
//        return new ElasticsearchTemplate(nodeBuilder()
//                .local(true)
//                .settings(elasticsearchSettings.build())
//                .node()
//                .client());
//    }
//
//    @Bean
//    public ElasticsearchOperations elasticsearchTemplate() throws Exception {
//        return new ElasticsearchTemplate(client());
//    }





}
