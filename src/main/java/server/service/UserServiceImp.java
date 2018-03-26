package server.service;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import server.domain.User;
import server.repository.UserRepository;

@Service
@AllArgsConstructor
public class UserServiceImp implements UserService{
    private UserRepository userRepository;

    /*@Override
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
    }*/
    @Override
    public Flux<User> GetAll(){
        return userRepository.findAll();
    }

    @Override
    public Mono<User> Register(String username){
        User user = new User();
        user.setUsername(username);
        user.setRank(1);
        return userRepository.save(user);
    }

    @Override
    public Mono<User> LogIn(String username){
        User exUser = new User();
        exUser.setUsername(username);
        ExampleMatcher matcher = ExampleMatcher.matching()
                .withIgnorePaths("rank")
                .withIncludeNullValues();
        Example<User> example = Example.of(exUser, matcher);
        return userRepository.findOne(example);
    }

    @Override
    public Mono<User> Update(String id, String name) {
        return userRepository.findById(id)
                .map(u -> {
                    u.setUsername(name);
                    return u;
                })
                .flatMap(u -> userRepository.save(u));
    }


}
