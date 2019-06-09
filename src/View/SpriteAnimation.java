package View;

import javafx.animation.Transition;
import javafx.scene.image.ImageView;

public class SpriteAnimation extends Transition {
    private final ImageView imageView;
    private int row;
    private int column;


    public SpriteAnimation(ImageView imageView){
        this.imageView = imageView;
    }

    @Override
    protected void interpolate(double frac) {

    }



}
