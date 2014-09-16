package hr.ahuskano.sensorsadventure.app.views;

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

import hr.ahuskano.sensorsadventure.app.utils.SharedPreferenceManagment;
import hr.ahuskano.sensorsadventure.app.R;

/**
 * Created by ahuskano on 9/4/2014.
 */
public class GameView extends ImageView {

    private Context context;
    private TextView time;
    private TextView points;
    private TextView highScore;
    private long timestamp;
    private FragmentManager fm;
    private AlertDialog dialog;
    private int coins = 0;

    private Bitmap boss;
    private Bitmap food;

    private int yBoss;
    private int xBoss;

    private int heightFood=100;
    private int widthFood=100;

    private Random randomX;
    private Random randomY;

    private int maxX;
    private int maxY;

    private boolean stoped = false;
    private boolean active = false;

    private CountDownTimer timer;

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
        Toast.makeText(context, context.getString(R.string.max_min_error), Toast.LENGTH_SHORT).show();
    }

    public void start() {
        if (timer == null || stoped) {
            timer = new CountDownTimer(30000, 1000) {
                @Override
                public void onTick(long milisec) {
                    time.setText(context.getString(R.string.time_label) + milisec / 1000);
                }

                @Override
                public void onFinish() {
                    if (active) {
                        time.setText(context.getString(R.string.finished_label));
                        stoped = true;
                        if (SharedPreferenceManagment.getScore(getContext()) < coins) {
                            SharedPreferenceManagment.saveScore(getContext(), coins);
                            highScore.setText(context.getString(R.string.high_score_label) + coins);
                        }

                        if (dialog != null && !dialog.isShowing())
                            dialog.setTitle("You got " + coins + " points!!! Do you want to play again?");
                        coins = 0;
                        dialog.show();
                    }
                }
            }.start();
            coins = 0;
            points.setText(context.getString(R.string.points_label) + coins);
        }

    }

    private void setDialog() {
        dialog = new AlertDialog.Builder(context)
                .setPositiveButton(context.getString(R.string.restart_label),
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialo, int whichButton) {
                                dialog.dismiss();
                                start();
                                stoped = false;
                            }
                        }
                )
                .setNegativeButton(context.getString(R.string.finish_label),
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

            canvas.drawBitmap(boss, xBoss, yBoss, null);
            canvas.drawBitmap(food, widthFood, heightFood, null);

        }
        refresh();
        invalidate();
    }

    private void refresh() {
        xBoss = (xBoss > 0 ? xBoss : 0);
        xBoss = xBoss > maxX ? maxX : xBoss;
        yBoss = yBoss > 0 ? yBoss : 0;
        yBoss = yBoss > maxY ? maxY : yBoss;
        if (detectCollision()) {
            widthFood = Math.abs(randomX.nextInt() % maxX);
            heightFood = Math.abs(randomY.nextInt() % maxY);
            if (!stoped)
                updateCoins();
        }

    }

    private void updateCoins() {
        coins += 10;
        points.setText(context.getString(R.string.points_label) + coins);
    }

    private boolean detectCollision() {
        Rect rect1 = new Rect();
        rect1.set(xBoss, yBoss, xBoss + boss.getWidth(), yBoss + boss.getHeight());
        Rect rect2 = new Rect();
        rect2.set(widthFood, heightFood, widthFood + food.getWidth(), heightFood + food.getHeight());
        return rect1.intersect(rect2);
    }

    public int getCoins() {
        return coins;
    }

    public void setCoins(int coins) {
        this.coins = coins;
    }

    public void setHighScore(TextView highScore) {
        this.highScore = highScore;
        this.highScore.setText(context.getString(R.string.high_score_label) + SharedPreferenceManagment.getScore(getContext()));
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public void setBoss(Bitmap boss) {
        this.boss = boss;
    }

    public void setFood(Bitmap food) {
        this.food = food;
    }

    public void setyBoss(int yBoss) {
        this.yBoss += yBoss;
    }

    public void setxBoss(int xBoss) {
        this.xBoss -= xBoss;
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
