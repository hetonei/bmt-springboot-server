package server.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import server.domain.User;
import server.repository.UserRepository;

@Service
@AllArgsConstructor
public class UserServiceImp implements UserService{
    private UserRepository userRepository;

    @Override
    public Flux<User> get() {
        return userRepository.findAll();
    }

    @Override
    public Mono<User> save(User user) {
        return userRepository.save(user);
    }

    @Override
    public Flux<User> getByUsername(String username) {
        return userRepository
                .findAll()
                .filter(user -> user.getUsername().equals(username));
    }
}
