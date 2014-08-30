package hr.ahuskano.wufy.app.fragments;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.ArcShape;
import android.graphics.drawable.shapes.OvalShape;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import hr.ahuskano.wufy.app.R;

/**
 * Created by ahuskano on 8/28/2014.
 */
public class FragmentBall extends SensorFragment {

    private final static String TAG = FragmentBall.class.getSimpleName();

    private Mario mario;
    private ShapeDrawable shape = new ShapeDrawable();

    private long lastUpdate;

    public static int x;
    public static int y;

    @Override
    protected void sensorEvent(SensorEvent sensorEvent) {
        x -= (int) sensorEvent.values[0];
        y += (int) sensorEvent.values[1];
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mario = new Mario(getContext());
        lastUpdate = System.currentTimeMillis();

        return mario;
    }

    @Override
    protected int getSensor() {
        return Sensor.TYPE_ACCELEROMETER;
    }

    @Override
    protected int getSensorMode() {
        return SensorManager.SENSOR_DELAY_FASTEST;
    }

    @Override
    protected int getResourceLayout() {

        return 0;
    }

    @Override
    protected void initView(View view) {

    }

    @Override
    protected String getLogTag() {
        return TAG;
    }

    private class Mario extends ImageView {

        static final int width = 150;
        static final int height = 150;

        public Mario(Context context) {
            super(context);
            this.setBackgroundColor(getResources().getColor(R.color.winter_sun_blue_darker));
            shape = new ShapeDrawable(new OvalShape());
            shape.getPaint().setColor(getResources().getColor(R.color.winter_sun_red));
            shape.setBounds(x, y, x + width, y + height);

        }

        @Override
        protected void onDraw(Canvas canvas) {
            shape.setBounds(x, y, x + width, y + height);
            shape.draw(canvas);
            invalidate();
        }
    }
}
