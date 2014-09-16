package hr.ahuskano.sensorsadventure.app;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.widget.FrameLayout;

import hr.ahuskano.sensorsadventure.app.fragments.FragmentSensorDetails;
import hr.ahuskano.sensorsadventure.app.R;

/**
 * Created by ahuskano on 8/24/2014.
 */
public class SensorDetailsActivity extends FragmentActivity {

    private FrameLayout frameLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sensor_details);
        initView();
    }

    private void initView() {
        frameLayout = ((FrameLayout) findViewById(R.id.flSensorDetailsContainer));
        setFragment();
    }

    private void setFragment() {
        getSupportFragmentManager().beginTransaction().add(frameLayout.getId(), new FragmentSensorDetails()).commit();
    }
}
