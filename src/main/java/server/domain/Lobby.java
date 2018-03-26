package server.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import server.game.spells.Effect;
import server.game.spells.Spell;

import java.util.List;
import java.util.Objects;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document
public class Lobby {
    @Id
    private String id;
    private Player player1;
    private Player player2;
    private int turnnumber = 0;
    private int spellcounter = 0;
    private List<Spell> usedspells;
    private List<Effect> Effects;
    public Player getPlayer(String name){
        return Objects.equals(name, player1.getUsername()) ? player1 : player2;
    }
}
