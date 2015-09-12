package hello.service;

import hello.domain.Greeting;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GreetingService {

    private final GreetingRepository greetingRepository;

    @Autowired
    public GreetingService(GreetingRepository greetingRepository) {
        this.greetingRepository = greetingRepository;
    }

    public Greeting saveGreeting(final Greeting greeting) {
        return greetingRepository.save(greeting);
    }

    public Iterable<Greeting> getGreetings() {
        return greetingRepository.findAll();
    }
}
