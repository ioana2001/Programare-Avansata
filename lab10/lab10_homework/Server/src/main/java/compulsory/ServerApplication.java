package compulsory;

import compulsory.repository.MessageRepository;
import compulsory.repository.PersonRepository;
import compulsory.server.Server;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServerApplication.class, args);
    }

    @Bean
    public CommandLineRunner run(PersonRepository personRepository, MessageRepository messageRepository) {
        return args -> {
            Server.init(personRepository, messageRepository);
        };
    }
}
