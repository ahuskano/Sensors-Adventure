package hr.ahuskano.wufy.app.views;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.os.CountDownTimer;
import android.support.v4.app.FragmentManager;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

import hr.ahuskano.wufy.app.R;

/**
 * Created by ahuskano on 9/4/2014.
 */
public class GameView extends ImageView {

    private Context context;
    private TextView time;
    private TextView points;
    private long timestamp;
    private FragmentManager fm;
    private AlertDialog dialog;
    private int coins = 0;

    private Bitmap boss;
    private Bitmap food;

    private int heightBoss;
    private int widthBoss;

    private int heightFood;
    private int widthFood;

    private Random randomX;
    private Random randomY;

    private int maxX;
    private int maxY;

    private boolean stoped = false;

    public GameView(Context context) {
        super(context);
        init(context);
    }

    public GameView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public GameView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context);
    }

    private void init(Context context) {
        this.context = context;
        this.setBackgroundColor(getResources().getColor(R.color.black_light));
        setDialog();

    }


    public void setSeeds() {
        if (maxX != 0 && maxY != 0) {
            randomX = new Random();
            randomX.setSeed(maxX);
            randomY = new Random();
            randomY.setSeed(maxY);
            return;
        }
        Toast.makeText(context, "Max X and Max Y not seted", Toast.LENGTH_SHORT).show();
    }

    public void start() {
        new CountDownTimer(30000, 1000) {
            @Override
            public void onTick(long milisec) {
                time.setText("Time: " + milisec / 1000);
            }

            @Override
            public void onFinish() {
                time.setText("Finished");
                stoped = true;
                if (dialog != null && !dialog.isShowing())
                    dialog.setTitle("You got " + coins + " points!!! Do you want to play again?");
                coins = 0;
                dialog.show();

            }
        }.start();
        coins = 0;
        points.setText("Points: "+coins);

    }

    private void setDialog() {
        dialog = new AlertDialog.Builder(context)
                .setPositiveButton("Restart",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialo, int whichButton) {
                                dialog.dismiss();
                                stoped = false;
                                start();
                            }
                        }
                )
                .setNegativeButton("Finish",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialo, int whichButton) {
                                dialog.dismiss();
                            }
                        }
                )
                .create();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (timestamp == 0) {
            timestamp = System.currentTimeMillis() / 1000;
        }
        if (!stoped) {

            canvas.drawBitmap(boss, widthBoss, heightBoss, null);
            canvas.drawBitmap(food, widthFood, heightFood, null);

        }
        refresh();
        invalidate();
    }

    private void refresh() {
        widthBoss = (widthBoss > 0 ? widthBoss : 0);
        widthBoss = widthBoss > maxX ? maxX : widthBoss;
        heightBoss = heightBoss > 0 ? heightBoss : 0;
        heightBoss = heightBoss > maxY ? maxY : heightBoss;
        if (detectCollision()) {
            widthFood = Math.abs(randomX.nextInt() % maxX);
            heightFood = Math.abs(randomY.nextInt() % maxY);
            if (!stoped)
                updateCoins();
        }

    }

    private void updateCoins() {
        coins += 10;
        points.setText("Points: " + coins);
    }

    private boolean detectCollision() {
        Rect rect1 = new Rect();
        rect1.set(widthBoss, heightBoss, widthBoss + boss.getWidth(), heightBoss + boss.getHeight());
        Rect rect2 = new Rect();
        rect2.set(widthFood, heightFood, widthFood + food.getWidth(), heightFood + food.getHeight());
        return rect1.intersect(rect2);
    }

    public void setBoss(Bitmap boss) {
        this.boss = boss;
    }

    public void setFood(Bitmap food) {
        this.food = food;
    }

    public void setHeightBoss(int heightBoss) {
        this.heightBoss += heightBoss;
    }

    public void setWidthBoss(int widthBoss) {
        this.widthBoss -= widthBoss;
    }

    public void setHeightFood(int heightFood) {
        this.heightFood = heightFood;
    }

    public void setWidthFood(int widthFood) {
        this.widthFood = widthFood;
    }

    public void setRandomX(Random randomX) {
        this.randomX = randomX;
    }

    public void setRandomY(Random randomY) {
        this.randomY = randomY;
    }

    public void setMaxX(int maxX) {
        this.maxX = maxX - boss.getWidth();
    }

    public void setMaxY(int maxY) {
        this.maxY = maxY - boss.getHeight();
    }

    public void setPoints(TextView points) {
        this.points = points;
    }

    public void setTime(TextView time) {
        this.time = time;
    }

    public void setFm(FragmentManager fm) {
        this.fm = fm;
    }
}
