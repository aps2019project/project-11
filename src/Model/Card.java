package Model;

import javafx.scene.image.ImageView;

public abstract class Card implements Cloneable
{
    private boolean cardSelectedInBattle = false;
    private String cardID;
    private int price;
    private int requiredMP;
    private String cardName;
    private int row;
    private int column;
    private transient ImageView cardImageView;

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
        for (Card card : Shop.getInstance().getCards())
        {
            if (card.getCardName().equals(cardName))
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

    public void setDefaultCardID()
    {
        Account account = Account.loggedInAccount;
        String cardID = "SinglePlayer_" + this.getCardName() + "_" + account.getAIAccountDefaultID();
        this.setCardID(cardID);
        account.increaseAIAccountDefaultID();
    }

    public ImageView getCardImageView()
    {
        return cardImageView;
    }

    public void setCardImageView(ImageView cardImageView)
    {
        this.cardImageView = cardImageView;
    }
}