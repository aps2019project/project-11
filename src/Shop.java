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

    public void  sell(String name){

    }

    public void show(){

    }

    public void help(){

    }
}
