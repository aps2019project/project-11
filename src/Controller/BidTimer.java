package Controller;

import Model.*;
import Network.Server;
import javafx.scene.text.Text;

import java.util.Random;

public class BidTimer extends Thread
{
    @Override
    public void run()
    {
        try
        {
            setNewCardsAndItemToBid();
            timerLoop();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    private void timerLoop() throws Exception
    {
        while (true)
        {
            for (int counter = 30; counter >= 0; counter--)
            {
                Thread.sleep(1000);
                System.out.println("00:" + counter);
            }
            //confirmBidWinners();
            setNewCardsAndItemToBid();
        }
    }

    private void confirmBidWinners() throws Exception
    {
        confirmHeroWinner();
        confirmMinionWinner();
        confirmSpellWinner();
        confirmItemWinner();
    }

    private void setNewCardsAndItemToBid()
    {
        Server.setBidHero(getRandomHero());
        Server.setBidMinion(getRandomMinion());
        Server.setBidSpell(getRandomSpell());
        Server.setBidItem(getRandomItem());
    }

    private void confirmHeroWinner() throws Exception
    {
        Hero hero = Server.getBidHero();
        Account account = hero.getBidWinner();
        if (account != null)
        {
            Server.getShop().buyCard(hero, account, true);
        }
    }

    private void confirmMinionWinner() throws Exception
    {
        Minion minion = Server.getBidMinion();
        Account account = minion.getBidWinner();
        if (account != null)
        {
            Server.getShop().buyCard(minion, account, true);
        }
    }

    private void confirmSpellWinner() throws Exception
    {
        Spell spell = Server.getBidSpell();
        Account account = spell.getBidWinner();
        if (account != null)
        {
            Server.getShop().buyCard(spell, account, true);
        }
    }

    private void confirmItemWinner() throws Exception
    {
        Item item = Server.getBidItem();
        Account account = item.getBidWinner();
        if (account != null)
        {
            Server.getShop().buyItem(item, account, true);
        }
    }

    private Hero getRandomHero()
    {
        Random random = new Random();
        int randomNumber = random.nextInt(Server.getHeroes().size());
        return Server.getHeroes().get(randomNumber);
    }

    private Minion getRandomMinion()
    {
        Random random = new Random();
        int randomNumber = random.nextInt(Server.getMinions().size());
        return Server.getMinions().get(randomNumber);
    }

    private Spell getRandomSpell()
    {
        Random random = new Random();
        int randomNumber = random.nextInt(Server.getSpells().size());
        return Server.getSpells().get(randomNumber);
    }

    private Item getRandomItem()
    {
        Random random = new Random();
        int randomNumber = random.nextInt(Server.getItems().size());
        Item item = Server.getItems().get(randomNumber);
        if (item.getItemType() == ItemType.collectible)
        {
            getRandomItem();
        }
        return item;
    }
}
