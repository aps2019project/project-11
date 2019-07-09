package Model;

import java.util.ArrayList;

public class SpellEffect
{
    private ArrayList<Target> targets = new ArrayList<>();
    private ArrayList<SpellChange> spellChanges = new ArrayList<>();

    /*public void addCellEffect(int x, int y)
    {
        int startRow = this.getTargets().get(0).getStartRow();
        int startColumn = this.getTargets().get(0).getStartColumn();
        int endRow = this.getTargets().get(0).getEndRow();
        int endColumn = this.getTargets().get(0).getEndColumn();
        int dx = x - startRow;
        int dy = y - startColumn;
        for (int i = startRow; i <= endRow; i++)
        {
            for (int j = startColumn; j <= endColumn; j++)
            {
                getBattle().getBattleField().getBattleFieldMatrix()[i + dx][j + dy].addSpellChange(this.getSpellChanges().get(0));
            }
        }
    }*/

    public ArrayList<Target> getTargets() {
        return targets;
    }

    public void addTarget(Target target)
    {
        targets.add(target);
    }

    public ArrayList<SpellChange> getSpellChanges()
    {
        return spellChanges;
    }

    public void addSpellChange(SpellChange spellChange)
    {
        spellChanges.add(spellChange);
    }
}
