package sunrise.smfestival;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class SmFestivalApplication {

    public static void main(String[] args) {
//        SpringApplication.run(SmFestivalApplication.class, args);
        new SpringApplicationBuilder(SmFestivalApplication.class)
                .properties(
                        "spring.config.location=" +
                                "classpath:/application.properties" +
                                ", file:/etc/config/application-database.properties"
                )
                .run(args);

    }


}