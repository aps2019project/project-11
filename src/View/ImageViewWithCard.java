package View;

import Model.Hero;
import Model.NonSpellCard;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.ArrayList;

public class ImageViewWithCard extends ImageView {
    private int row;
    private int column;
    private NonSpellCard card;
    private ArrayList<ImageViewWithCard> imageViewWithCards = new ArrayList<>();

    public ImageViewWithCard(){}

    public ImageViewWithCard(String url) {
        setImage(new Image(url));
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getColumn() {
        return column;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    public NonSpellCard getCard() {
        return card;
    }

    public void setCard(NonSpellCard card) {
        this.card = card;
    }
}
