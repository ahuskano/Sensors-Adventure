package hr.ahuskano.wufy.app.views;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.widget.ImageView;

import java.util.Random;

import hr.ahuskano.wufy.app.R;


/**
 * Created by ahuskano on 9/4/2014.
 */
public class CanvasView extends ImageView {

    private int width;
    private int height;
    private int widthHamburger;
    private int heightHamburger;
    private Bitmap image;
    private Bitmap hamburger;
    private Random randomX;
    private Random randomY;
    private final long delay = 100000;

    private CanvasView canvasView;
    private ShapeDrawable hole;


    public static int x;
    public static int y;

    private int maxX;
    private int maxY;

    private int imageX;
    private int imageY;


    public CanvasView(Context context) {
        super(context);
        this.setBackgroundColor(getResources().getColor(R.color.black_light));
        image = BitmapFactory.decodeResource(getContext().getResources(), R.drawable.sonic_small);
        imageX = image.getWidth();
        imageY = image.getHeight();
        hamburger = BitmapFactory.decodeResource(getContext().getResources(), R.drawable.hamburger_small);
        hole = new ShapeDrawable(new OvalShape());
        hole.getPaint().setColor(Color.WHITE);
        randomX = new Random();
        randomX.setSeed(maxX);
        randomY = new Random();
        randomY.setSeed(maxY);
        widthHamburger = 800;
        heightHamburger = 800;

    }

    @Override
    protected void onDraw(Canvas canvas) {
/*
        width = (x > 0 ? x : 0);
        width = width > maxX ? maxX : width;
        height = y > 0 ? y : 0;
        height = height > maxY ? maxY : height;

        canvas.drawBitmap(hamburger, widthHamburger, heightHamburger, null);

        canvas.drawBitmap(image, width, height, null);
        invalidate();
        if (detectCollision()) {
            widthHamburger = Math.abs(randomX.nextInt() % maxX);
            heightHamburger = Math.abs(randomY.nextInt() % maxY);

        }

  */  }

    private boolean detectCollision() {
        Rect rect1 = new Rect();
        rect1.set(width, height, width + image.getWidth(), height + image.getHeight());
        Rect rect2 = new Rect();
        rect2.set(widthHamburger, heightHamburger, widthHamburger + hamburger.getWidth(), heightHamburger + hamburger.getHeight());
        return rect1.intersect(rect2);
    }

}

