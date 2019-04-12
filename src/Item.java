import java.util.ArrayList;

public class Item
{
    public boolean isCollectibleItemSelectedInBattle() {
        return collectibleItemSelectedInBattle;
    }

    public void setCollectibleItemSelectedInBattle(boolean collectibleItemSelectedInBattle)
    {
        this.collectibleItemSelectedInBattle = collectibleItemSelectedInBattle;
    }

    public String getItemName() {
        return itemName;
    }

    public int getPrice() {
        return price;
    }

    enum itemType
    {
        collectible, flag, usable
    }

    private static ArrayList<Item> items = new ArrayList<>();
    private int itemID;
    private String itemName;
    private int price;
    //todo item effects
    private boolean collectibleItemSelectedInBattle = false;

    public int getItemID()
    {
        return itemID;
    }

    public static ArrayList<Item> getItems() {
        return items;
    }

    public Item findItem(int itemID, String command)
    {

    }

    public static void setItems()
    {

    }

    public void printItemStats(int counter)
    {
        System.out.println(counter + " : Name : " + getItemName() + " – Desc: " + /*todo*/ " – Sell Cost : " + getPrice());
    }
}
