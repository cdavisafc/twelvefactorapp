package helloadmin;

import org.apache.log4j.Logger;
import org.flywaydb.core.Flyway;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.sql.DataSource;

@SpringBootApplication
@RestController
public class Application {
    private Logger log = Logger.getLogger(Application.class);
    private boolean migrationDone = false;

    @RequestMapping(value="/")
    public String hello() {
        if (migrationDone) {
            return "the migration is complete";
        }
        return "the migration is incomplete";
    }

    @Bean
    public Flyway flyway(DataSource theDataSource) {
        Flyway flyway = new Flyway();
        flyway.setDataSource(theDataSource);
        flyway.setLocations("classpath:db/migration");
        flyway.clean();
        flyway.migrate();
        log.info("The migrations are done man!");
        migrationDone = true;
        return flyway;
    }


    public static void main(String[] args) {
        ApplicationContext ctx = SpringApplication.run(Application.class, args);
    }

}
