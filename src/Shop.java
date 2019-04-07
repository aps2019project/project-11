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

    public void showCollection(){

    }

    public void searchShop(String name){

    }

    public void searchCollection(String name){
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
