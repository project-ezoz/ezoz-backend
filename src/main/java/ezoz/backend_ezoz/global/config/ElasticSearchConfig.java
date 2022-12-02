package ezoz.backend_ezoz.global.config;

import ezoz.backend_ezoz.domain.journal.repository.elasticsearch.JournalSearchRepository;
import ezoz.backend_ezoz.domain.marker.repository.elasticsearch.MarkerSearchRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.client.ClientConfiguration;
import org.springframework.data.elasticsearch.client.elc.ElasticsearchConfiguration;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

@EnableElasticsearchRepositories(basePackageClasses = {MarkerSearchRepository.class, JournalSearchRepository.class})

@Configuration
public class ElasticSearchConfig extends ElasticsearchConfiguration {

    @Value("${elastic.url}")
    private String elasticUrl;

    @Override
    public ClientConfiguration clientConfiguration() {
        return ClientConfiguration.builder()
                .connectedTo(elasticUrl)
                .build();
    }
}
