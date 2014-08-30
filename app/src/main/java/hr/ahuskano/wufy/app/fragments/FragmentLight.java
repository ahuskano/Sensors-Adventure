package hr.ahuskano.wufy.app.fragments;

import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorManager;
import android.view.View;

import hr.ahuskano.wufy.app.R;

/**
 * Created by ahuskano on 8/26/2014.
 */
public class FragmentLight extends SensorFragment {

    private static final String TAG = FragmentLight.class.getSimpleName();
    private View view;


    @Override
    public void sensorEvent(SensorEvent sensorEvent) {
        logIt("VALUE OF LIGHT SENSOR: " + sensorEvent.values[0] / 4);
        if ((int) sensorEvent.values[0] > 0)
            if (view != null) {
                this.view.setBackgroundColor(Color.argb((int) sensorEvent.values[0] / 4, 0, 0, 0));
                this.view.invalidate();
            }

    }

    @Override
    protected int getSensor() {
        return Sensor.TYPE_LIGHT;
    }

    @Override
    protected int getSensorMode() {
        return SensorManager.SENSOR_DELAY_NORMAL;
    }


    @Override
    protected int getResourceLayout() {
        return R.layout.fragment_light;
    }

    @Override
    protected void initView(View view) {
        logIt("initVIew");
        this.view = (View) view.findViewById(R.id.vLight);
        if (view == null) logIt("view je null");
    }

    @Override
    protected String getLogTag() {
        return TAG;
    }

}
