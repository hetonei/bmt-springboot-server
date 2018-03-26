package server.game.managers;

import server.domain.Player;
import server.game.spells.Spell;
import server.game.spells.SpellName;
import server.game.spells.necromancer.Cadarkhas;
import server.game.spells.necromancer.Nacrayo;

public class GameManager {

    public static Spell CastSpell(SpellName sp, Player caster, Player enemy, boolean EnemyTarget){
        Spell spell = null;
        switch (sp){
            case Cadarkhas:
                spell = new Cadarkhas();
                break;
            case Nacrayo:
                spell = new Nacrayo();
                break;
        }
        assert spell != null;
        spell.EnemyCaster = EnemyTarget;
        spell.SetTargets(caster, enemy);
        return spell;
    }
}
