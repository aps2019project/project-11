import java.util.ArrayList;

public class Item
{
    private static ArrayList<Item> items = new ArrayList<>();
    private int itemID;
    private String itemName;
    private int price;
    private ItemType itemType;
    private Spell itemEffect;
    private boolean collectibleItemSelectedInBattle = false;

    public Item(String itemName, int price, ItemType itemType, Spell itemEffect)
    {
        this(itemName, itemType, itemEffect);
        this.setPrice(price);
    }

    public Item(String itemName, ItemType itemType, Spell itemEffect)
    {
        this.setItemName(itemName);
        this.setItemType(itemType);
        this.setItemEffect(itemEffect);
        items.add(this);
    }

    public static void setItems()
    {
        new Item("CrownOfWisdom", 300, ItemType.usable, new Spell());
        new Item("Shield", 4000, ItemType.usable, new Spell());
        new Item("DamulArk", 30000, ItemType.usable, new Spell());
        new Item("TheDevastation", ItemType.collectible, new Spell());
        new Item("TwinHornArrow", ItemType.collectible, new Spell());
        new Item("SimurghFeather", 3500, ItemType.usable, new Spell());
        new Item("Elixir", ItemType.collectible, new Spell());
        new Item("ManaPotion", ItemType.collectible, new Spell());
        new Item("InvulnerablePotion", ItemType.collectible, new Spell());
        new Item("DeathCurse", ItemType.collectible, new Spell());
        new Item("Random damage", ItemType.collectible, new Spell());
        new Item("Terror Hood",  5000, ItemType.usable, new Spell());
        new Item("Blades of agility", ItemType.collectible, new Spell());
        new Item("King Wisdom", 9000, ItemType.usable, new Spell());
        new Item("Assassination Dagger", 15000, ItemType.usable, new Spell());
        new Item("Poisonous Dagger", 7000, ItemType.usable, new Spell());
        new Item("Shock Hammer", 15000, ItemType.usable, new Spell());
        new Item("Soul Eater", 25000, ItemType.usable, new Spell());
        new Item("Baptism", 20000, ItemType.usable, new Spell());
        new Item("Chinese sword", ItemType.collectible, new Spell());
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

    public void printItemStats(int counter)
    {
        System.out.println(counter + " : Name : " + getItemName() + " – Desc: " + /*todo*/ " – Sell Cost : " + getPrice());
    }

    public static ArrayList<Item> getItems()
    {
        return items;
    }

    public String getItemName()
    {
        return itemName;
    }

    public boolean isCollectibleItemSelectedInBattle() {
        return collectibleItemSelectedInBattle;
    }

    public void setCollectibleItemSelectedInBattle(boolean collectibleItemSelectedInBattle)
    {
        this.collectibleItemSelectedInBattle = collectibleItemSelectedInBattle;
    }


    public void setItemName(String itemName)
    {
        this.itemName = itemName;
    }

    public int getItemID()
    {
        return itemID;
    }

    public void setItemID(int itemID)
    {
        this.itemID = itemID;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price)
    {
        this.price = price;
    }

    public Spell getItemEffect() {
        return itemEffect;
    }

    public void setItemEffect(Spell itemEffect) {
        this.itemEffect = itemEffect;
    }

    public ItemType getItemType() {
        return itemType;
    }

    public void setItemType(ItemType itemType) {
        this.itemType = itemType;
    }
}
