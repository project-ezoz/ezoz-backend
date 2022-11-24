package ezoz.backend_ezoz;

import ezoz.backend_ezoz.domain.journal.repository.elasticsearch.JournalSearchRepository;
import ezoz.backend_ezoz.domain.post.repository.elasticsearch.PostSearchRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import javax.annotation.PostConstruct;
import java.util.TimeZone;

@EnableJpaRepositories(excludeFilters = {
		@ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE,classes = PostSearchRepository.class),
		@ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = JournalSearchRepository.class)
})
@SpringBootApplication
public class BackendEzozApplication {

	@PostConstruct
	public void started() {
		TimeZone.setDefault(TimeZone.getTimeZone("KST"));
	}

	public static void main(String[] args) {
		SpringApplication.run(BackendEzozApplication.class, args);
	}

}
