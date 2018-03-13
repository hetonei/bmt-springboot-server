package server.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import server.domain.User;
import server.service.UserService;

@RestController
@RequestMapping("/users")
@AllArgsConstructor
public class UserController {
    private UserService userService;

    @GetMapping
    public Flux<User> get(){
        return userService.get();
    }

    @PostMapping
    public Mono<User> post(@RequestBody User user){
        return userService.save(user);
    }
}
