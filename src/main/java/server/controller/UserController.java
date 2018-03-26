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

    /*@GetMapping("/")
    public Flux<User> get(){
        return userService.get();
    }

    @GetMapping("/{name}")
    public Flux<User> getByUsername(@PathVariable String name){return userService.getByUsername(name);}

    @PostMapping
    public Mono<User> post(@RequestBody User user){
        return userService.save(user);
    }*/

    @GetMapping("/getall")
    public Flux<User> GetAll(){
        return userService.GetAll();
    }

    @PostMapping("/register")
    public Mono<User> Register(@RequestBody String username){return userService.Register(username);}

    @PostMapping("/login")
    public Mono<User> LogIn(@RequestBody String username){return userService.LogIn(username);}

    @PutMapping("/rank/{id}")
    public Mono<User> Update(@PathVariable("id") String id, @RequestBody String name){
        System.out.println(id);
        return userService.Update(id, name);
    }
}
