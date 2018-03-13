package server.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import server.domain.User;

public interface UserRepository extends ReactiveMongoRepository<User,String> {
}
