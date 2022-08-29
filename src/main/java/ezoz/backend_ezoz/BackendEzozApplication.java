package ezoz.backend_ezoz;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

import javax.annotation.PostConstruct;
import java.util.TimeZone;

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
