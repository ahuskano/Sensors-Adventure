package hr.ahuskano.wufy.app.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * Created by ahuskano on 9/6/2014.
 */
public class LightView extends View {

    private ShapeDrawable shape;

    public LightView(Context context) {
        super(context);
        init();
    }

    public LightView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public LightView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        shape = new ShapeDrawable(new OvalShape());
        Log.d("test", "init");
    }

    public void setColor(int color) {
        shape.getPaint().setARGB(color, 250,250,0);
        Log.d("test", "setCOlor "+ color);

    }

    public void setPosition(int left, int top, int right, int bottom) {
        shape.setBounds(left, top, right, bottom);
        Log.d("test", "setPosition "+ left+ " "+ top+" "+ right+" "+bottom);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        shape.draw(canvas);
    }
}