package server.service;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import server.domain.User;

public interface UserService {
    Flux<User> get();
    Mono<User> save(User user);
    Flux<User> getByUsername(final String username);
}
