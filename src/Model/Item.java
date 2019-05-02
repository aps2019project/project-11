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
    private ItemEffect itemEffect;
    private boolean collectibleItemSelectedInBattle = false;

    public Item(String itemName, int price, ItemType itemType, String descriptionTypeOfItem, ItemEffect itemEffect)
    {
        this(itemName, itemType, descriptionTypeOfItem, itemEffect);
        this.setPrice(price);
    }

    public Item(String itemName, ItemType itemType, String descriptionTypeOfItem, ItemEffect itemEffect)
    {
        this.setItemName(itemName);
        this.setItemType(itemType);
        this.setDescriptionTypeOfItem(descriptionTypeOfItem);
        this.setItemEffect(itemEffect);
        items.add(this);
    }

    public static void setItems()
    {
        ItemEffect itemEffectItem1 = new ItemEffect();
        itemEffectItem1.addItemChange(new ItemChange(3, false, 1, 0, 0, false, false, 0, false, 0, false, 0, false, 0));
        itemEffectItem1.addItemTarget(ItemTarget.OWN_PLAYER);
        itemEffectItem1.addItemActivateTime(ItemActivateTime.START_BATTLE);
        new Item("CrownOfWisdom", 300, ItemType.usable, "Increase MP in first 3 turns once", itemEffectItem1);

        ItemEffect itemEffectItem2 = new ItemEffect();
        itemEffectItem2.addItemChange(new ItemChange(0, true, 0, 0, 0, false, true, 12, false, 0, false, 0, false, 0));
        itemEffectItem2.addItemTarget(ItemTarget.OWN_HERO);
        itemEffectItem2.addItemActivateTime(ItemActivateTime.USE_ITEM);
        new Item("Shield", 4000, ItemType.usable, "Activate 12 holyBuffs on own hero", itemEffectItem2);

        ItemEffect itemEffectItem3 = new ItemEffect();  //todo
        itemEffectItem3.addItemChange(new ItemChange(1, false, 0, 0, 0, true, false, 0, false, 0, false, 0, false,0));
        itemEffectItem3.addItemTarget(ItemTarget.OWN_RANGED_HYBRID_HERO);
        itemEffectItem3.addItemActivateTime(ItemActivateTime.ON_ATTACK);
        new Item("DamulArk", 30000, ItemType.usable, "Own hero disarm opponent while own hybrid or ranged force attacked", itemEffectItem3);

        ItemEffect itemEffectItem4 = new ItemEffect();
        itemEffectItem4.addItemChange(new ItemChange(0, true, 0, 6, 0, false, false, 0, false, 0, false, 0, false, 0));
        itemEffectItem4.addItemTarget(ItemTarget.OWN_RANDOM_FORCE);
        itemEffectItem4.addItemActivateTime(ItemActivateTime.ON_ATTACK);
        new Item("TheDevastation", ItemType.collectible, "Increase HP 6 units", itemEffectItem4);

        ItemEffect itemEffectItem5 = new ItemEffect();
        itemEffectItem5.addItemChange(new ItemChange(0, true, 0, 0, 2, false, false, 0, false, 0, false, 0, false, 0));
        itemEffectItem5.addItemTarget(ItemTarget.OWN_RANDOM_RANGED_HYBRID);
        itemEffectItem5.addItemActivateTime(ItemActivateTime.USE_ITEM);
        new Item("TwinHornArrow", ItemType.collectible, "Increase one hybrid or ranged force twice", itemEffectItem5);

        ItemEffect itemEffectItem6 = new ItemEffect();
        itemEffectItem6.addItemChange(new ItemChange(0, true, 0, 0, -2, false, false, 0, false, 0, false, 0, false, 0));
        itemEffectItem6.addItemTarget(ItemTarget.OPPONENT_RANGED_HYBRID_HERO);
        itemEffectItem6.addItemActivateTime(ItemActivateTime.USE_ITEM);
        new Item("SimurghFeather", 3500, ItemType.usable, "Decrease opponent hero's AP twice if hybrid or ranged", itemEffectItem6);

        ItemEffect itemEffectItem7 = new ItemEffect();
        itemEffectItem7.addItemChange(new ItemChange(0, true, 0, 3, 0, false, false, 0, false, 0, false, 0, false, 0));
        itemEffectItem7.addItemTarget(ItemTarget.OWN_RANDOM_MINION);
        itemEffectItem7.addItemActivateTime(ItemActivateTime.USE_ITEM);
        itemEffectItem7.addItemChange(new ItemChange(0, true, 0, 0, 0, false, false, 0, true, 3, false, 0, false, 0));
        itemEffectItem7.addItemTarget(ItemTarget.OWN_RANDOM_MINION);
        itemEffectItem7.addItemActivateTime(ItemActivateTime.USE_ITEM);
        new Item("Elixir", ItemType.collectible, "Increase Hp 3 units and one powerBuff with +3 for random minion", itemEffectItem7);

        ItemEffect itemEffectItem8 = new ItemEffect();
        itemEffectItem8.addItemChange(new ItemChange(1, false, 3, 0, 0, false, false, 0, false, 0, false, 0, false, 0));
        itemEffectItem8.addItemTarget(ItemTarget.OWN_PLAYER);
        itemEffectItem8.addItemActivateTime(ItemActivateTime.USE_ITEM);
        new Item("ManaPotion", ItemType.collectible, "Increase MP 3 units in next turn", itemEffectItem8);

        ItemEffect itemEffectItem9 = new ItemEffect();
        itemEffectItem9.addItemChange(new ItemChange(2, false, 0, 0, 0, false, true, 10, false, 0, false, 0, false, 0));
        itemEffectItem9.addItemTarget(ItemTarget.OWN_RANDOM_FORCE);
        itemEffectItem9.addItemActivateTime(ItemActivateTime.USE_ITEM);
        new Item("InvulnerablePotion", ItemType.collectible, "Activate 10 hollyBuffs on random own force for 2 turns", itemEffectItem9);

        ItemEffect itemEffectItem10 = new ItemEffect();  //todo
        itemEffectItem10.addItemChange(new ItemChange(0, true, 0, -8, 0, false, false, 0, false, 0, false, 0, false, 0));
        itemEffectItem10.addItemTarget(ItemTarget.OPPONENT_CLOSEST_FORCE);
        itemEffectItem10.addItemActivateTime(ItemActivateTime.ON_DEATH);
        new Item("DeathCurse", ItemType.collectible, "Minion attack one of the closest opponent forces 8 units", itemEffectItem10);

        ItemEffect itemEffectItem11 = new ItemEffect();
        itemEffectItem11.addItemChange(new ItemChange(0, true, 0, 0, 2, false, false, 0, false, 0, false, 0, false, 0));
        itemEffectItem11.addItemTarget(ItemTarget.OWN_RANDOM_FORCE);
        itemEffectItem11.addItemActivateTime(ItemActivateTime.USE_ITEM);
        new Item("Random damage", ItemType.collectible, "add 2 units to random force AP", itemEffectItem11);

        ItemEffect itemEffectItem12 = new ItemEffect();
        itemEffectItem12.addItemChange(new ItemChange(1, false, 0, 0, 0, false, false, 0, false, 0, true, 2, false, 0));
        itemEffectItem12.addItemTarget(ItemTarget.OPPONENT_RANDOM_FORCE);
        itemEffectItem12.addItemActivateTime(ItemActivateTime.ON_ATTACK);
        new Item("Terror Hood",  5000, ItemType.usable, "Activate weaknessBuff with decreasing AP 2 units for 1 turn on random opponent when attacked", itemEffectItem12);

        ItemEffect itemEffectItem13 = new ItemEffect();
        itemEffectItem13.addItemChange(new ItemChange(0, true, 0, 0, 6, false, false, 0, false, 0, false, 0, false, 0));
        itemEffectItem13.addItemTarget(ItemTarget.OWN_RANDOM_FORCE);
        itemEffectItem13.addItemActivateTime(ItemActivateTime.USE_ITEM);
        new Item("Blades of agility", ItemType.collectible, "add 6 units to random force AP", itemEffectItem13);

        ItemEffect itemEffectItem14 = new ItemEffect();
        itemEffectItem14.addItemChange(new ItemChange(0, true, 1, 0, 0, false, false, 0, false, 0, false, 0, false, 0));
        itemEffectItem14.addItemTarget(ItemTarget.OWN_PLAYER);
        itemEffectItem14.addItemActivateTime(ItemActivateTime.START_BATTLE);
        new Item("King Wisdom", 9000, ItemType.usable, "Increase MP 1 unit in all turns", itemEffectItem14);

        ItemEffect itemEffectItem15 = new ItemEffect();
        itemEffectItem15.addItemChange(new ItemChange(0, true, 0, -1, 0, false, false, 0, false, 0, false, 0, false, 0));
        itemEffectItem15.addItemTarget(ItemTarget.OPPONENT_HERO);
        itemEffectItem15.addItemActivateTime(ItemActivateTime.ON_SPAWN);
        new Item("Assassination Dagger", 15000, ItemType.usable, "Attack opponent hero 1 unit when inserting any force", itemEffectItem15);

        ItemEffect itemEffectItem16 = new ItemEffect();
        itemEffectItem16.addItemChange(new ItemChange(1, false, 0, 0, 0, false, false, 0, false, 0, false, 0, true, 1));
        itemEffectItem16.addItemTarget(ItemTarget.OPPONENT_RANDOM_FORCE);
        itemEffectItem16.addItemActivateTime(ItemActivateTime.ON_ATTACK);
        new Item("Poisonous Dagger", 7000, ItemType.usable, "Activate poisonBuff on opponent random force for 1 turn while own force attacked", itemEffectItem16);

        ItemEffect itemEffectItem17 = new ItemEffect();
        itemEffectItem17.addItemChange(new ItemChange(1, false, 0, 0, 0, true, false, 0, false, 0, false, 0, false, 0));
        itemEffectItem17.addItemTarget(ItemTarget.OPPONENT_FORCE);
        itemEffectItem17.addItemActivateTime(ItemActivateTime.ON_ATTACK);
        new Item("Shock Hammer", 15000, ItemType.usable, "Stun opponent when own hero attacked", itemEffectItem17);

        ItemEffect itemEffectItem18 = new ItemEffect();
        itemEffectItem18.addItemChange(new ItemChange(0, true, 0, 0, 0, false, false, 0, true, 1, false, 0, false, 0));
        itemEffectItem18.addItemTarget(ItemTarget.OWN_RANDOM_FORCE);
        itemEffectItem18.addItemActivateTime(ItemActivateTime.ON_DEATH);
        new Item("Soul Eater", 25000, ItemType.usable, "Activate 1 powerBuff with 1 unit on random own force while any own minion killed", itemEffectItem18);

        ItemEffect itemEffectItem19 = new ItemEffect();
        itemEffectItem19.addItemChange(new ItemChange(2, false, 0, 0, 0, false, true, 1, false, 0, false, 0, false, 0));
        itemEffectItem19.addItemTarget(ItemTarget.OWN_MINION);
        itemEffectItem19.addItemActivateTime(ItemActivateTime.ON_SPAWN);
        new Item("Baptism", 20000, ItemType.usable, "Each minion has powerBuff for 2 turns when spawned", itemEffectItem19);

        ItemEffect itemEffectItem20 = new ItemEffect();
        itemEffectItem20.addItemChange(new ItemChange(0, true, 0, 0, 5, false, false, 0, false, 0, false, 0, false, 0));
        itemEffectItem20.addItemTarget(ItemTarget.OWN_MELEE_FORCE);
        itemEffectItem20.addItemActivateTime(ItemActivateTime.USE_ITEM);
        new Item("Chinese sword", ItemType.collectible, "Increase AP 5 units for melee forces", itemEffectItem20);
    }

    public void applyCollectibleItem(int x, int y)
    {
        for (int i = 0; i < this.getItemEffect().getItemTargets().size(); i++)
        {
            ItemTarget itemTarget = this.getItemEffect().getItemTargets().get(i);
            ItemChange itemChange = this.getItemEffect().getItemChanges().get(i);
            switch (itemTarget)
            {
                case OWN_RANDOM_FORCE:
                    Battle.getCurrentBattle().getPlayerTurn().getMainDeck().getHero().get(0).addActiveItemOnThisCard(itemChange);
                    break;
                case OWN_RANDOM_RANGED_HYBRID:
                    //todo
                    break;
                case OWN_RANDOM_MINION:
                    //todo
                    break;
                case OWN_PLAYER:
                    Battle.getCurrentBattle().getPlayerTurn().addActiveItemOnPlayer(itemChange);
                    break;
                case OWN_MELEE_FORCE:
                    //todo
                    break;
            }
        }
    }

    public void applyUsableItem()
    {
        for (int i = 0; i < this.getItemEffect().getItemTargets().size(); i++)
        {
            ItemTarget itemTarget = this.getItemEffect().getItemTargets().get(i);
            ItemChange itemChange = this.getItemEffect().getItemChanges().get(i);
            switch (itemTarget)
            {
                case OWN_PLAYER:
                    Battle.getCurrentBattle().getPlayerTurn().addActiveItemOnPlayer(itemChange);
                    break;
                case OWN_HERO:
                    Battle.getCurrentBattle().getPlayerTurn().getMainDeck().getHero().get(0).addActiveItemOnThisCard(itemChange);
                    break;
                case OWN_RANGED_HYBRID_HERO:
                    Hero hero = Battle.getCurrentBattle().getPlayerTurn().getMainDeck().getHero().get(0);
                    if (hero.getImpactType() == ImpactType.ranged || hero.getImpactType() == ImpactType.hybrid)
                    {
                        hero.addActiveItemOnThisCard(itemChange);
                    }
                    break;
                    //todo

            }
        }
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
        System.out.println(counter + " : Name : " + getItemName() + " – Desc: " + getDescriptionTypeOfItem() + " – Sell Cost : " + getPrice());
    }

    public void printItemStats()
    {
        System.out.println("Name : " + getItemName() + " – Desc: " + getDescriptionTypeOfItem() + " – Sell Cost : " + getPrice());
    }

    public static ArrayList<Item> getItems()
    {
        return items;
    }

    public String getItemName()
    {
        return itemName;
    }

    public boolean isCollectibleItemSelectedInBattle()
    {
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

    public ItemEffect getItemEffect() {
        return itemEffect;
    }

    public void setItemEffect(ItemEffect itemEffect) {
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
