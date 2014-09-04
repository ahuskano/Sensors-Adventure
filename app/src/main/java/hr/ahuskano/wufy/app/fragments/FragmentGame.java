package hr.ahuskano.wufy.app.fragments;

import android.app.DialogFragment;
import android.graphics.BitmapFactory;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorManager;
import android.view.Display;
import android.view.View;
import android.widget.TextView;

import org.w3c.dom.Text;

import hr.ahuskano.wufy.app.R;
import hr.ahuskano.wufy.app.views.GameView;

/**
 * Created by ahuskano on 9/4/2014.
 */
public class FragmentGame extends SensorFragment {

    private GameView view;

    @Override
    protected void sensorEvent(SensorEvent sensorEvent) {
        view.setWidthBoss((int) sensorEvent.values[0]);
        view.setHeightBoss((int) sensorEvent.values[1]);
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
        return R.layout.fragment_game;
    }

    @Override
    protected void initView(View view) {
        this.view = (GameView) view.findViewById(R.id.view);
        setupGame();
    }

    private void setupGame() {
        view.setFm(getChildFragmentManager());
        view.setBoss(BitmapFactory.decodeResource(getContext().getResources(), R.drawable.sonic_small));
        view.setFood(BitmapFactory.decodeResource(getContext().getResources(), R.drawable.hamburger_small));
        Display display = getActivity().getWindowManager().getDefaultDisplay();
        view.setMaxX(display.getWidth());
        view.setMaxY(display.getHeight());
        view.setSeeds();
        view.setWidthFood(500);
        view.setHeightFood(800);
        view.setPoints((TextView) getView().findViewById(R.id.tvCoins));
        view.setTime((TextView) getView().findViewById(R.id.tvTime));
        view.start();
        ;
    }

    @Override
    protected String getLogTag() {
        return FragmentGame.class.getSimpleName();
    }
}
