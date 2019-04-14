import java.util.ArrayList;

public class Shop
{
    private ArrayList<Card> cards = new ArrayList<>();
    private ArrayList<Item> items = new ArrayList<>();
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

    public void buyCard(String cardName){

    }

    public void  buyItem(String itemName){

    }

    public void  sell(String name){

    }

    public void show(){

    }

    public void help(){

    }
}
