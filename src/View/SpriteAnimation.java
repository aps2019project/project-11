package View;

import javafx.animation.Interpolator;
import javafx.animation.Transition;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

public class SpriteAnimation extends Transition
{
    private final ImageView imageView;
    private final int count;
    private final int rows;
    private final int offsetX;
    private final int offsetY;
    private final int width;
    private final int height;

    private int lastIndex;

    public SpriteAnimation(ImageView imageView, Duration duration, int count, int rows, int offsetX, int offsetY, int width, int height)
    {
        this.imageView = imageView;
        this.count = count;
        this.rows = rows;
        this.offsetX = offsetX;
        this.offsetY = offsetY;
        this.width = width;
        this.height = height;
        setCycleDuration(duration);
        setInterpolator(Interpolator.LINEAR);
    }

    @Override
    protected void interpolate(double k)
    {
        final int index = Math.min((int) Math.floor(k * count), count - 1);
        if (index != lastIndex)
        {
            final int x = (index / rows) * width + offsetX;
            final int y = (index % rows) * height + offsetY;
            imageView.setViewport(new Rectangle2D(x, y, width, height));
            lastIndex = index;
        }
    }
}
