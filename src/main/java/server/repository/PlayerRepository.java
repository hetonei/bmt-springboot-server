package server.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import server.domain.Player;

public interface PlayerRepository extends ReactiveMongoRepository<Player, String> {
}
