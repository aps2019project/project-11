package Model;

import java.util.ArrayList;

public class SpellEffect
{
    private ArrayList<Target> targets = new ArrayList<>();
    private ArrayList<SpellChange> spellChanges = new ArrayList<>();

    public ArrayList<Target> getTargets() {
        return targets;
    }

    public void addTarget(Target target)
    {
        targets.add(target);
    }

    public ArrayList<SpellChange> getSpellChanges() {
        return spellChanges;
    }

    public void addSpellChange(SpellChange spellChange) {
        spellChanges.add(spellChange);
    }
}
