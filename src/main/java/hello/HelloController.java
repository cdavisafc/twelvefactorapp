package hello;

import hello.domain.Greeting;
import hello.service.GreetingService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.Environment;

import java.time.Instant;

@RestController
public class HelloController implements EnvironmentAware {
    private static Logger log = Logger.getLogger(HelloController.class);

    private GreetingService greetingService;

	private String ip;
	private String ports;
	
	@Override
	public void setEnvironment(Environment environment) {
		this.ip = environment.getProperty("CF_INSTANCE_IP");
		this.ports = environment.getProperty("CF_INSTANCE_PORTS");
	}
	
    @Autowired
    public HelloController(GreetingService greetingService) {
        this.greetingService = greetingService;
    }

    @RequestMapping("/")
    public String index(
            @RequestParam(value = "person", required=false, defaultValue="nobody")
                String person)
    {

        log.debug("I intend to greet " + person);
        Greeting greeting = greetingService.saveGreeting(
                new Greeting(person, Instant.now().getEpochSecond()));
        return greeting.toString() + " From ip " + ip + " and ports " + ports;
    }

    @RequestMapping("/history")
    public @ResponseBody Iterable<Greeting> greetingList() {
        return greetingService.getGreetings();
    }

    @RequestMapping("/broken")
    public String broken() {
        log.debug("Huh Oh");
		System.exit(1);
        return "won't get here";
    }
}
