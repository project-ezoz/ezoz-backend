package ezoz.backend_ezoz;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
public class BackendEzozApplication {

	public static void main(String[] args) {
		SpringApplication.run(BackendEzozApplication.class, args);
	}

}
