package hr.ahuskano.wufy.app.fragments;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.Display;
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
    private ShapeDrawable hole;

    public static int x;
    public static int y;

    private int maxX;
    private int maxY;

    private int imageX;
    private int imageY;

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
        Display display = getActivity().getWindowManager().getDefaultDisplay();
        maxX = display.getWidth() - imageX;
        maxY = display.getHeight() - imageY;


    }

    @Override
    protected String getLogTag() {
        return TAG;
    }

    private class CanvasView extends ImageView {

        private int width;
        private int height;
        private Bitmap image;

        public CanvasView(Context context) {
            super(context);
            this.setBackgroundColor(getResources().getColor(R.color.black_light));
            image = BitmapFactory.decodeResource(getContext().getResources(), R.drawable.sonic_small);
            imageX = image.getWidth();
            imageY = image.getHeight();
            hole = new ShapeDrawable(new OvalShape());
            hole.getPaint().setColor(Color.WHITE);
        }

        @Override
        protected void onDraw(Canvas canvas) {
            width = (x > 0 ? x : 0);
            width = width > maxX ? maxX : width;
            height = y > 0 ? y : 0;
            height = height > maxY ? maxY : height;
            hole.setBounds(maxX / 2 - 200, maxY / 2 - 200, maxX / 2 + 200, maxY / 2 + 200);

            hole.draw(canvas);

            canvas.drawBitmap(image, width, height, null);

            invalidate();
        }
    }
}
