package hello;

import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.Environment;

@RestController
public class HelloController implements EnvironmentAware {
    private static Logger log = Logger.getLogger(HelloController.class);
	
	private String name;

	@Override
	public void setEnvironment(Environment environment) {
		this.name = environment.getProperty("who");
	}

    @RequestMapping("/")
    public String index() {
        log.debug("I intend to greet someone");
        return "Greetings " + name + " from Spring Boot!";
    }

}
