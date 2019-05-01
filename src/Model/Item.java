package Model;

import java.util.ArrayList;

public class Item
{
    private static ArrayList<Item> items = new ArrayList<>();
    private int itemID;
    private String itemName;
    private int price;
    private ItemType itemType;
    private String descriptionTypeOfItem;
    private Spell itemEffect;
    private boolean collectibleItemSelectedInBattle = false;

    public Item(String itemName, int price, ItemType itemType, String descriptionTypeOfItem)
    {
        this(itemName, itemType, descriptionTypeOfItem);
        this.setPrice(price);
    }

    public Item(String itemName, ItemType itemType, String descriptionTypeOfItem)
    {
        this.setItemName(itemName);
        this.setItemType(itemType);
        this.setDescriptionTypeOfItem(descriptionTypeOfItem);
        items.add(this);
    }

    public static void setItems()
    {
        new Item("CrownOfWisdom", 300, ItemType.usable, "Increase MP in first 3 turns once");
        new Item("Shield", 4000, ItemType.usable, "Activate 12 holyBuffs on own hero");
        new Item("DamulArk", 30000, ItemType.usable, "Own hero disarm opponent while own hybrid or ranged force attacked");
        new Item("TheDevastation", ItemType.collectible, "Increase HP 6 units");
        new Item("TwinHornArrow", ItemType.collectible, "Increase one hybrid or ranged force twice");
        new Item("SimurghFeather", 3500, ItemType.usable, "Decrease opponent hero's AP twice if hybrid or ranged");
        new Item("Elixir", ItemType.collectible, "Increase Hp 3 units and one powerBuff with +3 for random minion");
        new Item("ManaPotion", ItemType.collectible, "Increase MP 3 units in next turn");
        new Item("InvulnerablePotion", ItemType.collectible, "Activate 10 hollyBuffs on random own force for 2 turns");
        new Item("DeathCurse", ItemType.collectible, "Minion attack one of the closest opponent forces 8 units");
        new Item("Random damage", ItemType.collectible, "add 2 units to random force AP");
        new Item("Terror Hood",  5000, ItemType.usable, "Activate weaknessBuff with decreasing AP 2 units for 1 turn on random opponent when attacked");
        new Item("Blades of agility", ItemType.collectible, "add 6 units to random force AP");
        new Item("King Wisdom", 9000, ItemType.usable, "Increase MP 1 unit in all turns");
        new Item("Assassination Dagger", 15000, ItemType.usable, "Attack opponent hero 1 unit when inserting any force");
        new Item("Poisonous Dagger", 7000, ItemType.usable, "Activate poisonBuff on opponent random force for 1 turn while own force attacked");
        new Item("Shock Hammer", 15000, ItemType.usable, "Stun opponent when own hero attacked");
        new Item("Soul Eater", 25000, ItemType.usable, "Activate 1 powerBuff with 1 unit on random own force while any own minion killed");
        new Item("Baptism", 20000, ItemType.usable, "Each minion has powerBuff for 2 turns when spawned");
        new Item("Chinese sword", ItemType.collectible, "Increase AP 5 units for melee forces");
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

    public String getDescriptionTypeOfItem() {
        return descriptionTypeOfItem;
    }

    public void setDescriptionTypeOfItem(String descriptionTypeOfItem) {
        this.descriptionTypeOfItem = descriptionTypeOfItem;
    }
}
