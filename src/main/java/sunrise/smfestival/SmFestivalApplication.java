package sunrise.smfestival;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class SmFestivalApplication {

    public static void main(String[] args) {
        SpringApplication.run(SmFestivalApplication.class, args);
    }

}