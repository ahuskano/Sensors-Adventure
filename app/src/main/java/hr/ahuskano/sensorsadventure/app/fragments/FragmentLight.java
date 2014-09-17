package hr.ahuskano.sensorsadventure.app.fragments;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import hr.ahuskano.sensorsadventure.app.views.LightView;
import hr.ahuskano.sensorsadventure.app.R;

/**
 * Created by ahuskano on 8/26/2014.
 */
public class FragmentLight extends SensorFragment {

    private static final String TAG = FragmentLight.class.getSimpleName();
    private LightView view;
    private ImageView image;
    private TextView label;
    private float THRESHOLD_TOP=0.2f;
    private float THRESHOLD_DOWN=0.4f;
    private float THRESHOLD_LEFT=0.2f;
    private float THRESHOLD_RIGHT=0.2f;


    @Override
    public void sensorEvent(SensorEvent sensorEvent) {
        if ((int) sensorEvent.values[0] > 0)
            if (view != null) {
                this.view.setPosition((int) (image.getLeft()+(image.getWidth()*THRESHOLD_LEFT)), (int) (image.getTop()+(image.getHeight()*THRESHOLD_TOP)), (int) (image.getRight()-(image.getWidth()*THRESHOLD_RIGHT)), (int) (image.getBottom()-(image.getHeight()*THRESHOLD_DOWN)));
                this.view.setColor((int) sensorEvent.values[0]);
                this.view.invalidate();
                this.label.setText(getString(R.string.light_label) + sensorEvent.values[0]);
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
    protected void initView(View view, Bundle bundle) {
        this.image = (ImageView) view.findViewById(R.id.ivLight);
        this.view = (LightView) view.findViewById(R.id.vLight);
        this.label = (TextView) view.findViewById(R.id.tvLight);

    }

    @Override
    protected String getLogTag() {
        return TAG;
    }

}
