package Model;

import javafx.scene.image.ImageView;

import java.util.ArrayList;

public class Card implements Cloneable {
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



    public int getColumn() {
        return column;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getRequiredMP() {
        return requiredMP;
    }

    public String getCardID() {
        return cardID;
    }

    public int getPrice() {
        return price;
    }

    public static Card findCard(String cardName) {
        for (Card card : Shop.getInstance().getCards()) {
            if (card.getCardName().equals(cardName)) {
                return card;
            }
        }
        return null;
    }

    public boolean isCardSelectedInBattle() {
        return cardSelectedInBattle;
    }

    public void setCardSelectedInBattle(boolean cardSelectedInBattle) {
        this.cardSelectedInBattle = cardSelectedInBattle;
    }

    public String getCardName() {
        return cardName;
    }

    public static void setCards() {
        Spell.setSpells();
        NonSpellCard.setNonSpellCards();
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setRequiredMP(int requiredMP) {
        this.requiredMP = requiredMP;
    }

    public void setCardName(String cardName) {
        this.cardName = cardName;
    }

    public void setCardID(String cardID) {
        this.cardID = cardID;
    }

    public static int findDestination(Card card1, Card card2) {
        return Math.abs(card1.getRow() - card2.getRow()) + Math.abs(card1.getColumn() - card2.getColumn());
    }

    public static boolean checkNeighborhood(Card card1, Card card2) {
        int[][] matrix = new int[5][9];
        int row = card1.getRow();
        int column = card1.getColumn();
        matrix[row][column] = 1;
        for (int rowCounter = row - 1; rowCounter <= row + 1; rowCounter++) {
            for (int columnCounter = column - 1; columnCounter <= column + 1; columnCounter++) {
                matrix[rowCounter][columnCounter] = 1;
            }
        }
        return matrix[card2.getRow()][card2.getColumn()] == 1;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    public void setDefaultCardID() {
        Account account = Account.loggedInAccount;
        String cardID = "SinglePlayer_" + this.getCardName() + "_" + account.getAIAccountDefaultID();
        this.setCardID(cardID);
        account.increaseAIAccountDefaultID();
    }

    public ImageView getCardImageView() {
        return cardImageView;
    }

    public void setCardImageView(ImageView cardImageView) {
        this.cardImageView = cardImageView;
    }

    public int getImageNumber() {
        return imageNumber;
    }

    public void setImageNumber(int imageNumber) {
        this.imageNumber = imageNumber;
    }

    public ImageView getCardIcon() {
        return cardIcon;
    }

    public void setCardIcon(ImageView cardIcon) {
        this.cardIcon = cardIcon;
    }

    public static Card getCard() {
        return card;
    }

    public static ArrayList<ImageView> getCardsIcon() {
        return cardsIcons;
    }

    public static void setCardIcons() {
        ImageView imageView0 = new ImageView("cardIcons/hero1.png");
        ImageView imageView1 = new ImageView("cardIcons/hero2.png");
        ImageView imageView2 = new ImageView("cardIcons/hero3.png");
        ImageView imageView3 = new ImageView("cardIcons/hero4.png");
        ImageView imageView4 = new ImageView("cardIcons/hero5.png");
        ImageView imageView5 = new ImageView("cardIcons/hero6.png");
        ImageView imageView6 = new ImageView("cardIcons/hero7.png");
        ImageView imageView7 = new ImageView("cardIcons/hero8.png");
        ImageView imageView8 = new ImageView("cardIcons/hero9.png");
        ImageView imageView9 = new ImageView("cardIcons/hero10.png");
        ImageView imageView10 = new ImageView("cardIcons/minion1.png");
        ImageView imageView11 = new ImageView("cardIcons/minion2.png");
        ImageView imageView12 = new ImageView("cardIcons/minion3.png");
        ImageView imageView13 = new ImageView("cardIcons/minion4.png");
        ImageView imageView14 = new ImageView("cardIcons/SpellICon.png");
        cardsIcons.add(imageView0);
        cardsIcons.add(imageView1);
        cardsIcons.add(imageView2);
        cardsIcons.add(imageView3);
        cardsIcons.add(imageView4);
        cardsIcons.add(imageView5);
        cardsIcons.add(imageView6);
        cardsIcons.add(imageView7);
        cardsIcons.add(imageView8);
        cardsIcons.add(imageView9);
        cardsIcons.add(imageView10);
        cardsIcons.add(imageView11);
        cardsIcons.add(imageView12);
        cardsIcons.add(imageView13);
        cardsIcons.add(imageView14);

    }

    public static ArrayList<ImageView> getCardsImageView() {
        return cardsImageView;
    }

    public static void setCardsImageView() {
        cardsImageView.add(new ImageView("Cards Images/hero-1.png"));
        cardsImageView.add(new ImageView("Cards Images/hero-2.png"));
        cardsImageView.add(new ImageView("Cards Images/hero-3.png"));
        cardsImageView.add(new ImageView("Cards Images/hero-4.png"));
        cardsImageView.add(new ImageView("Cards Images/hero-5.png"));
        cardsImageView.add(new ImageView("Cards Images/hero-6.png"));
        cardsImageView.add(new ImageView("Cards Images/hero-7.png"));
        cardsImageView.add(new ImageView("Cards Images/hero-8.png"));
        cardsImageView.add(new ImageView("Cards Images/hero-9.png"));
        cardsImageView.add(new ImageView("Cards Images/hero-10.png"));
        cardsImageView.add(new ImageView("Cards Images/minion-1.png"));
        cardsImageView.add(new ImageView("Cards Images/minion-2.png"));
        cardsImageView.add(new ImageView("Cards Images/minion-3.png"));
        cardsImageView.add(new ImageView("Cards Images/minion-4.png"));
    }

}