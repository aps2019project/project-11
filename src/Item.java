public class Item
{
    public boolean isCollectibleItemSelectedInBattle() {
        return collectibleItemSelectedInBattle;
    }

    public void setCollectibleItemSelectedInBattle(boolean collectibleItemSelectedInBattle) {
        this.collectibleItemSelectedInBattle = collectibleItemSelectedInBattle;
    }

    enum itemType
    {
        collectible, flag, usable
    }
    private int itemID;
    private boolean collectibleItemSelectedInBattle = false;

    public int getItemID()
    {
        return itemID;
    }

    public Item findItem(int itemID, String command)
    {

    }
}
