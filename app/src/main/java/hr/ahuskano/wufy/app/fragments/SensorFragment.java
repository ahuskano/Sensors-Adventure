package hr.ahuskano.wufy.app.fragments;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;

/**
 * Created by ahuskano on 8/25/2014.
 */
public abstract class SensorFragment extends BaseFragment implements SensorEventListener {

    protected abstract void sensorEvent(SensorEvent sensorEvent);

    protected abstract int getSensor();

    protected abstract int getSensorMode();

    protected Sensor sensor;
    protected SensorManager sensorManager;


    public void instanceSensor() {
        sensorManager = (SensorManager) getContext().getSystemService(Context.SENSOR_SERVICE);
        sensor = sensorManager.getDefaultSensor(getSensor());
    }

    @Override
    public void onPause() {
        super.onPause();
        sensorManager.unregisterListener(this, sensor);

    }

    @Override
    public void onResume() {
        super.onResume();
        sensorManager.registerListener(this, sensor, getSensorMode());
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);
        instanceSensor();
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        sensorEvent(sensorEvent);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
}
