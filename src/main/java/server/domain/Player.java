package server.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import server.game.spells.SpellName;

import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document
public class Player {
    @Id
    private String id;
    private String username;
    Map<SpellName, Boolean> usableSpells;
    private String heroname;
    private int health;
}
