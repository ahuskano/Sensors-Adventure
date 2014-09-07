package hr.ahuskano.wufy.app.fragments;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;

import hr.ahuskano.wufy.app.R;

/**
 * Created by ahuskano on 8/25/2014.
 */
public class FragmentCompas extends SensorFragment {

    private SensorManager sensorManager;
    private ImageView compas_image;
    private Sensor sensor;
    private float currentDegree = 0f;

    private final String TAG = FragmentCompas.class.getSimpleName();

    @Override
    protected int getResourceLayout() {
        return R.layout.fragment_compas;
    }

    @Override
    protected void initView(View view, Bundle bundle) {
        compas_image = (ImageView) view.findViewById(R.id.ivCompas);
    }

    @Override
    protected String getLogTag() {
        return TAG;
    }


    @Override
    public void sensorEvent(SensorEvent sensorEvent) {
        float degree = Math.round(sensorEvent.values[0]);
        RotateAnimation animation = new RotateAnimation(currentDegree,
                -degree,
                Animation.RELATIVE_TO_SELF,
                0.5f,
                Animation.RELATIVE_TO_SELF,
                0.5f);
        animation.setDuration(220);
        animation.setFillAfter(true);
        compas_image.startAnimation(animation);
        currentDegree = -degree;
    }

    @Override
    protected int getSensor() {
        return Sensor.TYPE_ORIENTATION;
    }

    @Override
    protected int getSensorMode() {
        return SensorManager.SENSOR_DELAY_GAME;
    }

}
