package Model;

import java.util.ArrayList;

public class ItemEffect
{
    private ArrayList<ItemChange> itemChanges = new ArrayList<>();
    private ArrayList<ItemTarget> itemTargets = new ArrayList<>();
    private ArrayList<ItemActivateTime> itemActivateTimes = new ArrayList<>();

    public ArrayList<ItemChange> getItemChanges()
    {
        return itemChanges;
    }

    public void addItemChange(ItemChange itemChange)
    {
        itemChanges.add(itemChange);
    }

    public ArrayList<ItemTarget> getItemTargets()
    {
        return itemTargets;
    }

    public void addItemTarget(ItemTarget itemTarget)
    {
        itemTargets.add(itemTarget);
    }

    public ArrayList<ItemActivateTime> getItemActivateTimes()
    {
        return itemActivateTimes;
    }

    public void addItemActivateTime(ItemActivateTime itemActivateTime)
    {
        itemActivateTimes.add(itemActivateTime);
    }
}
