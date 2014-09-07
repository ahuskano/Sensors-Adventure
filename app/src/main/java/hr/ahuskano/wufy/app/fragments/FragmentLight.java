package hr.ahuskano.wufy.app.fragments;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorManager;
import android.view.View;
import android.widget.ImageView;

import hr.ahuskano.wufy.app.R;
import hr.ahuskano.wufy.app.views.LightView;

/**
 * Created by ahuskano on 8/26/2014.
 */
public class FragmentLight extends SensorFragment {

    private static final String TAG = FragmentLight.class.getSimpleName();
    private LightView view;
    private ImageView image;


    @Override
    public void sensorEvent(SensorEvent sensorEvent) {
        logIt("VALUE OF LIGHT SENSOR: " + sensorEvent.values[0]);
        if ((int) sensorEvent.values[0] > 0)
            if (view != null) {
                //                this.view.setPosition((int)(image.getLeft()*2.8), (int)(image.getTop()*1.10), (int)(image.getRight()*0.80), (int)(image.getBottom()*0.70));
                this.view.setPosition((int)(image.getLeft()*2.2), (int)(image.getTop()*2.3), (int)(image.getRight()*0.85), (int)(image.getBottom()*0.65));

                this.view.setColor((int) sensorEvent.values[0]);
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
        this.view = (LightView) view.findViewById(R.id.vLight);
        if (view == null) logIt("view je null");
        this.image = (ImageView) view.findViewById(R.id.ivLight);

    }

    @Override
    protected String getLogTag() {
        return TAG;
    }

}
