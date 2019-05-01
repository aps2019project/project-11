package Model;

import java.util.ArrayList;

public class ItemEffect
{
    private ArrayList<ItemChange> itemChanges = new ArrayList<>();
    private ArrayList<ItemTarget> itemTargets = new ArrayList<>();
    private ItemActivateTime itemActivateTimes;

    public ArrayList<ItemChange> getItemChanges() {
        return itemChanges;
    }

    public void addItemChange(ItemChange itemChange) {
        itemChanges.add(itemChange);
    }

    public ArrayList<ItemTarget> getItemTargets() {
        return itemTargets;
    }

    public void addItemTarget(ItemTarget itemTarget) {
        itemTargets.add(itemTarget);
    }

    public ItemActivateTime getItemActivateTimes() {
        return itemActivateTimes;
    }

    public void setItemActivateTimes(ItemActivateTime itemActivateTimes) {
        this.itemActivateTimes = itemActivateTimes;
    }
}
