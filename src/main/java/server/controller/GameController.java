package server.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;
import server.domain.Lobby;
import server.domain.Player;
import server.game.spells.SpellName;
import server.service.GameService;

@RestController
@RequestMapping("/game")
@AllArgsConstructor
public class GameController {

    private GameService gameService;

    @PostMapping("/create")
    public Mono<Lobby> CreateLobby(@RequestBody Player player){
        return gameService.Create(player);
    }

    @PostMapping("/search")
    public Mono<Lobby> SearchLobby(@RequestBody Player player){
        return gameService.Search(player);
    }

    @GetMapping("/wait/{lobbyId}")
    public Mono<Lobby> Wait(@PathVariable String lobbyId) {
        return gameService.Wait(lobbyId);
    }
        /*Лобби хранит продолжительные спеллы, игроков, скастованные спеллы
        Получаем героя, свитч по енам, создаём игрока
        Записываем в мапу героя спеллы
        Получаем спелл, свитч по енам, создаём спелл
        Юзаем спеллменедже для юзания спеллов получаем список эффектов
        Добавляем полученные эффекты к продолжительным
        Юзаем спеллменеджер для юзания эфектов, он принимает список эффектов, получаем список эффектов, где лайфспан не 0
        Обновляем лобби и игроков*/

    @PutMapping("/cast/{lobbyID}/{playerName}/{spellName}/{EnemyCaster}")
    public Mono<Lobby> CastSpell(@PathVariable String lobbyID,@PathVariable String playerName,
                                 @PathVariable SpellName spellName,
                                 @PathVariable boolean EnemyCaster){
        return gameService.Cast(lobbyID, playerName, spellName, EnemyCaster);
    }
}
