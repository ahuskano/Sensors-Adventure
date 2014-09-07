package hr.ahuskano.wufy.app.fragments;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;
import android.widget.TableRow;
import android.widget.TextView;

import hr.ahuskano.wufy.app.R;
import hr.ahuskano.wufy.app.utils.Singleton;

/**
 * Created by ahuskano on 8/23/2014.
 */
public class FragmentSensorDetails extends BaseFragment implements SensorEventListener {

    private final String TAG = FragmentSensorDetails.class.getSimpleName();

    private Sensor sensor;
    private SensorManager sensorManager;
    private TextView value1, value2, value3, value4, value5;
    private TableRow row1, row2, row3, row4, row5;
    private boolean cleanValues = false;

    @Override
    protected int getResourceLayout() {
        return R.layout.fragment_sensor_details;
    }

    @Override
    protected void initView(View view, Bundle bundle) {
        fillSensorData(view);
        value1 = (TextView) view.findViewById(R.id.tvSensorValue1);
        value2 = (TextView) view.findViewById(R.id.tvSensorValue2);
        value3 = (TextView) view.findViewById(R.id.tvSensorValue3);
        value4 = (TextView) view.findViewById(R.id.tvSensorValue4);
        value5 = (TextView) view.findViewById(R.id.tvSensorValue5);


        row1 = (TableRow) view.findViewById(R.id.trValue1);
        row2 = (TableRow) view.findViewById(R.id.trValue2);
        row3 = (TableRow) view.findViewById(R.id.trValue3);
        row4 = (TableRow) view.findViewById(R.id.trValue4);
        row5 = (TableRow) view.findViewById(R.id.trValue5);

    }

    private void fillSensorData(View view) {
        sensor = Singleton.getInstance().getSensor();
        ((TextView) view.findViewById(R.id.tvSensorName)).setText(sensor.getName());
        ((TextView) view.findViewById(R.id.tvSensorMaxRange)).setText(String.valueOf(sensor.getMaximumRange()));
        ((TextView) view.findViewById(R.id.tvSensorPower)).setText(String.valueOf(sensor.getPower()));
        ((TextView) view.findViewById(R.id.tvSensorResolution)).setText(String.valueOf(sensor.getResolution()));
        ((TextView) view.findViewById(R.id.tvSensorType)).setText(String.valueOf(sensor.getType()));
        ((TextView) view.findViewById(R.id.tvSensorVendor)).setText(String.valueOf(sensor.getVendor()));
        ((TextView) view.findViewById(R.id.tvSensorVersion)).setText(String.valueOf(sensor.getVersion()));
        ((TextView) view.findViewById(R.id.tvSensorMinDelay)).setText(String.valueOf(sensor.getMinDelay()));
        sensorManager = (SensorManager) getActivity().getSystemService(Context.SENSOR_SERVICE);
    }

    @Override
    public void onResume() {
        super.onResume();
        sensorManager.registerListener(this, sensor, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    public void onPause() {
        super.onPause();
        sensorManager.unregisterListener(this);
    }

    @Override
    protected String getLogTag() {
        return TAG;
    }


    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        if (!cleanValues) {
            setupView(sensorEvent);
        }
        switch (sensorEvent.values.length) {
            case 5:
                value5.setText(String.valueOf(sensorEvent.values[4]));
            case 4:
                value4.setText(String.valueOf(sensorEvent.values[3]));
            case 3:
                value3.setText(String.valueOf(sensorEvent.values[2]));
            case 2:
                value2.setText(String.valueOf(sensorEvent.values[1]));
            case 1:
                value1.setText(String.valueOf(sensorEvent.values[0]));

        }
    }

    private void setupView(SensorEvent sensorEvent) {

        switch (sensorEvent.values.length) {
            case 1:
                row2.setVisibility(TextView.GONE);
            case 2:
                row3.setVisibility(TextView.GONE);
            case 3:
                row4.setVisibility(TextView.GONE);
            case 4:
                row5.setVisibility(TextView.GONE);
        }
        switch (sensorEvent.values.length) {
            case 5:
                ((TextView) getView().findViewById(R.id.tvSensorLabel5)).setText(getString(R.string.fragment_details_value_label));
            case 4:
                ((TextView) getView().findViewById(R.id.tvSensorLabel4)).setText(getString(R.string.fragment_details_value_label));

            case 3:
                ((TextView) getView().findViewById(R.id.tvSensorLabel3)).setText(getString(R.string.fragment_details_value_label));

            case 2:
                ((TextView) getView().findViewById(R.id.tvSensorLabel2)).setText(getString(R.string.fragment_details_value_label));

            case 1:
                ((TextView) getView().findViewById(R.id.tvSensorLabel1)).setText(getString(R.string.fragment_details_value_label));

        }
        cleanValues = true;
    }


    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
}
