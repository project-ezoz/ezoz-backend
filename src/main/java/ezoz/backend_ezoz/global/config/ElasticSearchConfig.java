package ezoz.backend_ezoz.global.config;

import ezoz.backend_ezoz.domain.post.repository.elasticsearch.PostSearchRepository;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.client.ClientConfiguration;
import org.springframework.data.elasticsearch.client.elc.ElasticsearchConfiguration;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

@EnableElasticsearchRepositories(basePackageClasses = {PostSearchRepository.class})

@Configuration
public class ElasticSearchConfig extends ElasticsearchConfiguration {


    @Override
    public ClientConfiguration clientConfiguration() {
        return ClientConfiguration.builder()
                .connectedTo("localhost:9200")
                .build();
    }
}
