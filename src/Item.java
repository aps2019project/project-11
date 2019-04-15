import java.util.ArrayList;

public class Item
{
    private static ArrayList<Item> items = new ArrayList<>();
    private int itemID;
    private String itemName;
    private int price;
    private Spell itemEffect;
    private boolean collectibleItemSelectedInBattle = false;

    public int getItemID()
    {
        return itemID;
    }

    public static ArrayList<Item> getItems() {
        return items;
    }

    public static Item findItem(int itemID)
    {
        for (Item item : items)
        {
            if (item.getItemID() == itemID)
            {
                return item;
            }
        }
        return null;
    }

    public static Item findItem(String itemName)
    {
        for (Item item : items)
        {
            if (item.getItemName().equals(itemName))
            {
                return item;
            }
        }
        return null;
    }

    public static void setItems()
    {

    }

    public void printItemStats(int counter)
    {
        System.out.println(counter + " : Name : " + getItemName() + " – Desc: " + /*todo*/ " – Sell Cost : " + getPrice());
    }

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
}
