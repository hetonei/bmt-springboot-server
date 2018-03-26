package server.service;

import reactor.core.publisher.Mono;
import server.domain.Lobby;
import server.domain.Player;
import server.game.spells.SpellName;

public interface GameService {
    //player
    Mono<Lobby> Create(Player player);
    //Lobby

    //player
    Mono<Lobby> Search(Player player);
    //Lobby

    Mono<Lobby> Wait(String lobbyId);

    //lobbyid, playerid, spellname, enemycaster
    Mono<Lobby> Cast(String lobbyID, String playerName, SpellName spellName, boolean EnemyCaster);
    //Lobby
}
