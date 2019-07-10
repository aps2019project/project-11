package Model;

import Network.Server;
import javafx.scene.image.ImageView;

import java.util.ArrayList;

public class Card implements Cloneable
{
    private boolean cardSelectedInBattle = false;
    private String cardID;
    private int price;
    private int requiredMP;
    private String cardName;
    private int row;
    private int column;
    private transient ImageView cardImageView;
    private transient ImageView cardIcon;
    private transient int imageNumber;
    private static final Card card = new Card();
    private static ArrayList<ImageView> cardsIcons = new ArrayList<>();
    private static ArrayList<ImageView> cardsImageView = new ArrayList<>();
    private int capacityOfSell = 5;
    private Account bidWinner;
    private int bidWinnerPrice;

    public void increaseCapacityOfSell()
    {
        capacityOfSell++;
    }

    public void decreaseCapacityOfSell()
    {
        capacityOfSell--;
    }

    public static ImageView getCardStandingImageView(Card card)
    {
        if (card instanceof Hero)
        {
            return new ImageView("Cards Images/" + card.getCardName() + ".png");
        }
        if (card instanceof Minion)
        {
            return new ImageView("Cards Images/" + ((Minion) card).getImpactType() + ".png");
        }
        return null;
    }

    public static ImageView getCardAttackImageView(Card card)
    {
        if (card instanceof Hero)
        {
            return new ImageView("Cards Images/" + card.getCardName() + "Attack.png");
        }
        if (card instanceof Minion)
        {
            return new ImageView("Cards Images/" + ((Minion) card).getImpactType() + "Attack.png");
        }
        return null;
    }

    public static ImageView getCardIcon(Card card)
    {
        if (card instanceof Hero)
        {
            return new ImageView("cardIcons/" + card.getCardName() + ".png");
        }
        else if (card instanceof Minion)
        {
            return new ImageView("cardIcons/" + ((Minion) card).getImpactType() + ".png");
        }
        else if (card instanceof Spell)
        {
            return new ImageView("cardIcons/Spell.png");
        }
        return null;
    }

    public int getColumn()
    {
        return column;
    }

    public void setColumn(int column)
    {
        this.column = column;
    }

    public int getRow()
    {
        return row;
    }

    public void setRow(int row)
    {
        this.row = row;
    }

    public int getRequiredMP()
    {
        return requiredMP;
    }

    public String getCardID()
    {
        return cardID;
    }

    public int getPrice()
    {
        return price;
    }

    public static Card findCard(String cardName)
    {
        for (Card card : Server.getShop().getHeroes())
        {
            if (card.getCardName().equals(cardName))
            {
                return card;
            }
        }
        for (Card card : Server.getShop().getMinions())
        {
            if (card.getCardName().equals(cardName))
            {
                return card;
            }
        }
        for (Card card : Server.getShop().getSpells())
        {
            if (card.getCardName().equals(cardName))
            {
                return card;
            }
        }
        return null;
    }

    public static Card findCard(String cardID, Account account)
    {
        for (Card card : account.getCollection().getHeroes())
        {
            if (card.getCardID().equals(cardID))
            {
                return card;
            }
        }
        for (Card card : account.getCollection().getMinions())
        {
            if (card.getCardID().equals(cardID))
            {
                return card;
            }
        }
        for (Card card : account.getCollection().getSpells())
        {
            if (card.getCardID().equals(cardID))
            {
                return card;
            }
        }
        return null;
    }

    public boolean isCardSelectedInBattle()
    {
        return cardSelectedInBattle;
    }

    public void setCardSelectedInBattle(boolean cardSelectedInBattle)
    {
        this.cardSelectedInBattle = cardSelectedInBattle;
    }

    public String getCardName()
    {
        return cardName;
    }

    public static void setCards()
    {
        Spell.setSpells();
        NonSpellCard.setNonSpellCards();
    }

    public void setPrice(int price)
    {
        this.price = price;
        this.bidWinnerPrice = price / 2;
    }

    public void setRequiredMP(int requiredMP)
    {
        this.requiredMP = requiredMP;
    }

    public void setCardName(String cardName)
    {
        this.cardName = cardName;
    }

    public void setCardID(String cardID)
    {
        this.cardID = cardID;
    }

    public static int findDestination(Card card1, Card card2)
    {
        return Math.abs(card1.getRow() - card2.getRow()) + Math.abs(card1.getColumn() - card2.getColumn());
    }

    public static boolean checkNeighborhood(Card card1, Card card2)
    {
        int[][] matrix = new int[5][9];
        int row = card1.getRow();
        int column = card1.getColumn();
        matrix[row][column] = 1;
        for (int rowCounter = row - 1; rowCounter <= row + 1; rowCounter++)
        {
            for (int columnCounter = column - 1; columnCounter <= column + 1; columnCounter++)
            {
                matrix[rowCounter][columnCounter] = 1;
            }
        }
        return matrix[card2.getRow()][card2.getColumn()] == 1;
    }

    @Override
    public Object clone() throws CloneNotSupportedException
    {
        return super.clone();
    }

    public void setDefaultCardID(Account account)
    {
        String cardID = "SinglePlayer_" + this.getCardName() + "_" + account.getAIAccountDefaultID();
        this.setCardID(cardID);
        account.increaseAIAccountDefaultID();
    }

    public ImageView getCardStandingImageView()
    {
        return cardImageView;
    }

    public void setCardImageView(ImageView cardImageView)
    {
        this.cardImageView = cardImageView;
    }

    public int getImageNumber()
    {
        return imageNumber;
    }

    public void setImageNumber(int imageNumber)
    {
        this.imageNumber = imageNumber;
    }

    public void setCardIcon(ImageView cardIcon)
    {
        this.cardIcon = cardIcon;
    }

    public static Card getCard()
    {
        return card;
    }

    public static ArrayList<ImageView> getCardsIcon()
    {
        return cardsIcons;
    }

    public static void setCardIcons()
    {
        for (Hero hero : Server.getHeroes())
        {
            ImageView imageView = new ImageView("cardIcons/" + hero.getCardName() + ".png");
            cardsIcons.add(imageView);
        }
    }

    public static ArrayList<ImageView> getCardsImageView()
    {
        return cardsImageView;
    }

    public static void setCardsImageView()
    {
        for (Hero hero : Server.getHeroes())
        {
            ImageView imageView = new ImageView("Cards Images/" + hero.getCardName() + ".png");
            cardsImageView.add(imageView);
        }
        cardsImageView.add(new ImageView("Cards Images/ranged.png"));
        cardsImageView.add(new ImageView("Cards Images/melee.png"));
        cardsImageView.add(new ImageView("Cards Images/hybrid.png"));
    }


    public int getCapacityOfSell()
    {
        return capacityOfSell;
    }

    public Account getBidWinner()
    {
        return bidWinner;
    }

    public void setBidWinner(Account bidWinner)
    {
        this.bidWinner = bidWinner;
    }

    public int getBidWinnerPrice()
    {
        return bidWinnerPrice;
    }

    public void setBidWinnerPrice(int bidWinnerPrice)
    {
        this.bidWinnerPrice = bidWinnerPrice;
    }
}