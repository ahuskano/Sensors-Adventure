package hr.ahuskano.sensorsadventure.app.views;

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
        color = color > 10000 ? 10000 : color;
        int transparent = color / 40;
        shape.getPaint().setARGB(transparent, 255, 252, 198);

    }

    public void setPosition(int left, int top, int right, int bottom) {
        shape.setBounds(left, top, right, bottom);
        Log.d("test", "setPosition " + left + " " + top + " " + right + " " + bottom);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        shape.draw(canvas);
    }
}
