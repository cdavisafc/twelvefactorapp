package hello;

import hello.domain.Greeting;
import hello.service.GreetingService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;

@RestController
public class HelloController {
    private static Logger log = Logger.getLogger(HelloController.class);

    private GreetingService greetingService;

    @Autowired
    public HelloController(GreetingService greetingService) {
        this.greetingService = greetingService;
    }

    @RequestMapping("/")
    public String index(@RequestParam(value = "person") String person) {
        log.debug("I intend to greet " + person);
        Greeting greeting = greetingService.saveGreeting(
                new Greeting(person, Instant.now().getEpochSecond()));
        return greeting.toString();
    }

    @RequestMapping("/history")
    public @ResponseBody Iterable<Greeting> greetingList() {
        return greetingService.getGreetings();
    }
}
