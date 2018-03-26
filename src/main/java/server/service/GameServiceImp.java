package server.service;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import server.domain.Lobby;
import server.domain.Player;
import server.game.managers.GameManager;
import server.game.managers.SpellManager;
import server.game.spells.Effect;
import server.game.spells.Spell;
import server.game.spells.SpellName;
import server.repository.LobbyRepository;
import server.repository.PlayerRepository;

import java.util.ArrayList;
import java.util.Map;
import java.util.Objects;

@Service
@AllArgsConstructor
public class GameServiceImp implements GameService{

    LobbyRepository lobbyRepository;
    PlayerRepository playerRepository;

    @Override
    public Mono<Lobby> Create(Player player) {
        playerRepository.save(player);
        Lobby lobby = new Lobby();
        lobby.setPlayer1(player);
        lobby.setUsedspells(new ArrayList<>());
        return lobbyRepository.save(lobby);
    }

    @Override
    public Mono<Lobby> Wait(String lobbyId) {
        return lobbyRepository
                .findById(lobbyId)
                .flatMap(l -> lobbyRepository.save(l));

    }

    @Override
    public Mono<Lobby> Search(Player player) {
        Lobby exlobby = new Lobby();
        exlobby.setTurnnumber(0);
        ExampleMatcher matcher = ExampleMatcher.matching()
                .withIgnorePaths("player1", "player2", "usedspells", "effects")
                .withIncludeNullValues();
        Example<Lobby> example = Example.of(exlobby, matcher);
        playerRepository.save(player);

        return lobbyRepository.findOne(example)
                .map(lobby -> {
                    lobby.setPlayer2(player);

                    return lobby;
                })
                .flatMap(lobby -> lobbyRepository.save(lobby));
    }

    @Override
    public Mono<Lobby> Cast(String lobbyID, String playerName, SpellName spellName, boolean EnemyCaster) {
        return lobbyRepository.findById(lobbyID)
                .map(l -> {
                    if(l.getSpellcounter()==2){
                        l.setSpellcounter(0);
                        l.getUsedspells().clear();
                    }


                    /*l.getUsedspells().add(GameManager.CastSpell(spellName, l.getPlayer(playerName),
                            Objects.equals(l.getPlayer1().getHeroname(), player.getHeroname()) ?l.getPlayer2():l.getPlayer1(),
                            EnemyCaster));*/


                    l.getPlayer(playerName).getUsableSpells().put(spellName, false);
                    /*playerRepository.save(l.getPlayer(playerName));
                    lobbyRepository.save(l);*/

                    //from usablespells<T,V> to Spells

                    l.setSpellcounter(l.getSpellcounter()+1);
                    if (l.getSpellcounter()==2) {

                        for (Map.Entry<SpellName, Boolean> entry : l.getPlayer1().getUsableSpells().entrySet()) {
                            l.getUsedspells().add(GameManager.CastSpell(entry.getKey(), l.getPlayer1(),
                                    l.getPlayer2(), EnemyCaster));
                        }

                        for (Map.Entry<SpellName, Boolean> entry : l.getPlayer2().getUsableSpells().entrySet()) {
                            l.getUsedspells().add(GameManager.CastSpell(entry.getKey(), l.getPlayer2(),
                                    l.getPlayer1(), EnemyCaster));
                        }

                        l.setEffects(SpellManager.CastSpells(l.getUsedspells()));

                        l.setEffects(SpellManager.UseEffects(l.getEffects()));
                    }

                    return l;
                })
                .flatMap(l -> lobbyRepository.save(l));
    }
}
