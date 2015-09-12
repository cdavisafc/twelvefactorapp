package hello;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.Environment;

@RestController
public class HelloController implements EnvironmentAware {
	
	private String name;
	
	@Override
	public void setEnvironment(Environment environment) {
		this.name = environment.getProperty("who");
	}

    @RequestMapping("/")
    public String index() {
        return "Greetings " + name + " from Spring Boot!";
    }

}