package hr.ahuskano.wufy.app.fragments;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
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

    private CanvasView canvasView;

    public static int x;
    public static int y;

    @Override
    protected void sensorEvent(SensorEvent sensorEvent) {
        x += (int) sensorEvent.values[0];
        y -= (int) sensorEvent.values[1];
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        canvasView = new CanvasView(getContext());
        return canvasView;
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

    private class CanvasView extends ImageView {

        static final int width = 150;
        static final int height = 150;
        private Bitmap image;

        public CanvasView(Context context) {
            super(context);
            this.setBackgroundColor(getResources().getColor(R.color.black));
            image=BitmapFactory.decodeResource(getContext().getResources(),R.drawable.sonic_small);

        }

        @Override
        protected void onDraw(Canvas canvas) {
            canvas.drawBitmap(image,x,y,null);
            invalidate();
        }
    }
}
