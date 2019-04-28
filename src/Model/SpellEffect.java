package Model;

import java.util.ArrayList;

public class SpellEffect
{
    private ArrayList<Target> targets = new ArrayList<>();
    private ArrayList<SpellChange> spellChanges = new ArrayList<>();

    public ArrayList<Target> getTargets() {
        return targets;
    }

    public void setTargets(ArrayList<Target> targets) {
        this.targets = targets;
    }

    public ArrayList<SpellChange> getSpellChanges() {
        return spellChanges;
    }

    public void setSpellChanges(ArrayList<SpellChange> spellChanges) {
        this.spellChanges = spellChanges;
    }
}
