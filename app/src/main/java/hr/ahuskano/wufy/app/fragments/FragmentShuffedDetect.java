package hr.ahuskano.wufy.app.fragments;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import hr.ahuskano.wufy.app.R;

/**
 * Created by ahuskano on 8/25/2014.
 */
public class FragmentShuffedDetect extends SensorFragment {

    private final String TAG = FragmentShuffedDetect.class.getSimpleName();

    private Sensor sensor;
    private SensorManager sensorManager;
    private TextView tvMessage;
    private long timestamp;
    private static final int THRESHOLD = 6;

    @Override
    public void sensorEvent(SensorEvent sensorEvent) {
        float[] values = sensorEvent.values.clone();
        float accelerationRoot = (values[0] * values[0] + values[1] * values[1] + values[2] * values[2]) /
                (SensorManager.GRAVITY_EARTH * SensorManager.GRAVITY_EARTH);
        logIt("root: " + accelerationRoot);
        if (accelerationRoot >= THRESHOLD) {
            timestamp = sensorEvent.timestamp;
            tvMessage.setText(getString(R.string.shake_it_true));
        }
    }

    @Override
    protected int getSensor() {
        return Sensor.TYPE_ACCELEROMETER;
    }

    @Override
    protected int getSensorMode() {
        return SensorManager.SENSOR_DELAY_NORMAL;
    }

    @Override
    protected int getResourceLayout() {
        return R.layout.fragment_shuffed_detect;
    }

    @Override
    protected void initView(View view, Bundle bundle) {
        tvMessage = (TextView) view.findViewById(R.id.tvShakeIt);
        timestamp = System.currentTimeMillis();
    }

    @Override
    protected String getLogTag() {
        return TAG;
    }
}
