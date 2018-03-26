package server.game.spells.necromancer;

import server.game.spells.Effect;
import server.game.spells.EffectType;
import server.game.spells.Spell;
import server.game.spells.SpellName;

import java.util.ArrayList;

public class Nacrayo extends Spell {
    public Nacrayo (){
        this.Name = SpellName.Nacrayo;
        this.Description = "Наносит цели 8 урона.";
        this.Priority = 4;
        this.Ultimate = true;
        Effects = new ArrayList<>();
    }
    @Override
    public void PerformEffect(){
        NacrayoDamageTarget(EnemyCaster);
        this.ReadyToUse = false;
    }

    private void NacrayoDamageTarget(boolean EnemyCaster){
        Effect effect = new Effect();
        effect.Priority = 4;
        effect.Type = EffectType.Damage;
        effect.Caster = this.Caster;
        effect.Target = EnemyCaster ? this.Enemy : this.Caster;
        effect.Value = 8;
        effect.LifeSpan = 1;
        Effects.add(effect);
    }
}
