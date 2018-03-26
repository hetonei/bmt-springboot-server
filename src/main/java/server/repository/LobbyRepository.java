package server.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import server.domain.Lobby;

public interface LobbyRepository extends ReactiveMongoRepository<Lobby, String> {
}
