import java.util.ArrayList;

public class Shop
{
    private ArrayList<Card> cards = new ArrayList<>();
    private ArrayList<Item> items = new ArrayList<>();
    static Shop shop = new Shop();
    public ArrayList<Card> getCards()
    {
        return cards;
    }
    public ArrayList<Item> getItems()
    {
        return items;
    }

    public void showCollection()
    {
        Account.loggedInAccount.getCollection().show();
    }

    public void searchShop(String name){
        for(Card card : cards){
            if(card.getCardName().equals(name)){
                System.out.println("The card exists in the shop.");
                System.out.println("CardID : " + card.getCardID());
                return;
            }
        }
        for(Item item : items){
            if(item.getItemName().equals(name)){
                System.out.println("The item exists in the shop.");
                System.out.println("ItemID : " + item.getItemID());
                return;
            }
        }
        System.out.println("The item  or card doesn't exist in the shop.");
    }

    public void searchCollection(String name)
    {
        Account.loggedInAccount.getCollection().searchCollection(name);
    }

    public void buyCard(Card card){
        if(card.getPrice() > Account.loggedInAccount.getMoney()){
            System.out.println("you haven't enough money.");
        }
        else {
            Account.loggedInAccount.getCollection().addCard(card);
            Account.loggedInAccount.decreaseMoney(card.getPrice());
            System.out.println("Successful purchase");
        }

    }

    public void  buyItem(Item item){
        if(item.getPrice() > Account.loggedInAccount.getMoney()){
            System.out.println("you haven't enough money.");
        }
        else if(Account.loggedInAccount.getCollection().getItems().size() == 3){
            System.out.println("You have 3 items in your collection and you can't buy another item.");
        }
        else {
            Account.loggedInAccount.getCollection().addItem(item);
            Account.loggedInAccount.decreaseMoney(item.getPrice());
            System.out.println("Successful purchase");
        }

    }

    public void  sell(int ID){
        int condition = 0;
        Card cardToSell = null;
        Item itemToSell = null;
        for(Card card : Account.loggedInAccount.getCollection().getCards()){
            if(card.getCardID() == ID){
                condition = 1;
                cardToSell = card;
            }
        }
        if(condition == 0) {
            for (Item item : Account.loggedInAccount.getCollection().getItems()) {
                if (item.getItemID() == ID) {
                    condition = 1;
                    itemToSell = item;
                }
            }
        }
        if(condition == 0){
            System.out.println("You have'nt Card or Item with this ID!");
        }
        else {
            if(cardToSell != null){
                Account.loggedInAccount.getCollection().getCards().remove(cardToSell);
                Account.loggedInAccount.addMoney(cardToSell.getPrice());
                System.out.println("Successful Sale");
            }
            else {
                Account.loggedInAccount.getCollection().getItems().remove(itemToSell);
                Account.loggedInAccount.addMoney(itemToSell.getPrice());
                System.out.println("Successful Sale");
            }
        }
    }

    public void show(){

    }

    public void help(){
        System.out.println("exit");
        System.out.println("show collection");
        System.out.println("search [item name | card name]");
        System.out.println("search collection [item name | card name]");
        System.out.println("buy [card name | item name]");
        System.out.println("sell [card ID | item ID]");
        System.out.println("show");
        System.out.println("help");

    }
}
