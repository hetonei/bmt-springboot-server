package server.service;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import server.domain.User;

public interface UserService {
    Flux<User> GetAll();
    Mono<User> Register(String username);
    Mono<User> LogIn(String username);
    Mono<User> Update(String id, String name);
}
