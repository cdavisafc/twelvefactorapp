package hello;

import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
public class HelloController {
    private static Logger log = Logger.getLogger(HelloController.class);

    @RequestMapping("/")
    public String index() {
        log.debug("I intend to greet someone");
        return "Greetings from Spring Boot!";
    }

}
