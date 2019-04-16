import java.util.ArrayList;

public class Shop {
    static Shop shop = new Shop();
    private static int heroID = 10000;
    private static int minionID = 20000;
    private static int spellID = 30000;
    private static int itemID = 40000;
    private ArrayList<Card> cards = new ArrayList<>();
    private ArrayList<Item> items = new ArrayList<>();

    static void help()
    {
        System.out.println("exit");
        System.out.println("show collection");
        System.out.println("search [item name | card name]");
        System.out.println("search collection [item name | card name]");
        System.out.println("buy [card name | item name]");
        System.out.println("sell [card ID | item ID]");
        System.out.println("show");
        System.out.println("help");

    }

    public ArrayList<Card> getCards()
    {
        return cards;
    }

    public ArrayList<Item> getItems()
    {
        return items;
    }

    void showCollection()
    {
        Account.loggedInAccount.getCollection().show();
    }

    void searchShop(String name)
    {
        for (Card card : cards)
        {
            if (card.getCardName().equals(name))
            {
                System.out.println("The card exists in the shop.");
                System.out.println("CardID : " + card.getCardID());
                return;
            }
        }
        for (Item item : items) {
            if (item.getItemName().equals(name)) {
                System.out.println("The item exists in the shop.");
                System.out.println("ItemID : " + item.getItemID());
                return;
            }
        }
        System.out.println("The item  or card doesn't exist in the shop.");
    }

    void searchCollection(String name) {
        Account.loggedInAccount.getCollection().searchCollection(name);
    }

    void buyCard(Card card) {
        if (card.getPrice() > Account.loggedInAccount.getMoney()) {
            System.out.println("you haven't enough money.");
        } else {
            setCardID(card);
            Account.loggedInAccount.getCollection().addCard(card);
            Account.loggedInAccount.decreaseMoney(card.getPrice());
            System.out.println("Successful purchase");
        }
    }

    private void setCardID(Card card) {
        if (card instanceof Hero) {
            card.setCardID(heroID++);
        } else if (card instanceof Minion) {
            card.setCardID(minionID++);
        } else if (card instanceof Spell) {
            card.setCardID(spellID++);
        }
    }

     void buyItem(Item item) {
        if (item.getPrice() > Account.loggedInAccount.getMoney()) {
            System.out.println("you haven't enough money.");
        } else if (Account.loggedInAccount.getCollection().getItems().size() == 3) {
            System.out.println("You have 3 items in your collection and you can't buy another item.");
        } else {
            setItemID(item);
            Account.loggedInAccount.getCollection().addItem(item);
            Account.loggedInAccount.decreaseMoney(item.getPrice());
            System.out.println("Successful purchase");
        }
    }

    private void setItemID(Item item) {
        item.setItemID(itemID++);
    }

    void detectIDToSell(int ID) {
        Card cardToSell = null;
        Item itemToSell = null;
        for (Card card : Account.loggedInAccount.getCollection().getCards()) {
            if (card.getCardID() == ID) {
                cardToSell = card;
            }
        }
        for (Item item : Account.loggedInAccount.getCollection().getItems()) {
            if (item.getItemID() == ID) {
                itemToSell = item;
            }
        }
        if (cardToSell == null && itemToSell == null) {
            System.out.println("You haven't Card or Item with this ID!");
            return;
        }
        this.sell(cardToSell, itemToSell);
    }

    private void sell(Card cardToSell, Item itemToSell) {
        if (cardToSell != null) {
            for (Deck deck : Account.loggedInAccount.getPlayerDecks()) {
                if (cardToSell instanceof Hero) {
                    deck.deleteHeroFromDeck((Hero) cardToSell);
                } else {
                    deck.deleteNonHeroCardFromDeck(cardToSell);
                }
            }
            Account.loggedInAccount.getCollection().getCards().remove(cardToSell);
            Account.loggedInAccount.addMoney(cardToSell.getPrice());
            System.out.println("Successful Sale");
        } else {
            for (Deck deck : Account.loggedInAccount.getPlayerDecks()) {
                deck.deleteItemFromDeck(itemToSell);
            }
            Account.loggedInAccount.getCollection().getItems().remove(itemToSell);
            Account.loggedInAccount.addMoney(itemToSell.getPrice());
            System.out.println("Successful Sale");
        }
    }

    void show()
    {
        int counter = 1;
        for (Hero hero : Hero.getHeroes())
        {
            printStatsOfHero(hero, counter);
            counter ++;
        }
        counter = 1;
        for (Item item : Item.getItems()) {
            printStatsOfItem(item, counter);
            counter ++;
        }
        counter = 1;
        for (Card card : Card.getCards()) {
            if (card instanceof Hero) {
                continue;
            }
            if (card instanceof Spell) {
                Spell spell = (Spell) card;
                printStatsOfSpell(spell, counter);
                counter ++;
            } else if (card instanceof Minion) {
                Minion minion = (Minion) card;
                printStatsOfMinion(minion, counter);
                counter ++;
            }
        }
    }

    private void printStatsOfMinion(Minion minion, int counter) {
        minion.printMinionStats(counter);
    }

    private void printStatsOfSpell(Spell spell, int counter) {
        spell.printSpellCardStats(counter);
    }

    private void printStatsOfItem(Item item, int counter) {
        item.printItemStats(counter);
    }

    private void printStatsOfHero(Hero hero, int counter) {
        hero.printHeroStats(counter);
    }
}
