package hr.ahuskano.sensorsadventure.app.fragments;

import android.graphics.BitmapFactory;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.widget.TextView;

import hr.ahuskano.sensorsadventure.app.views.GameView;
import hr.ahuskano.sensorsadventure.app.R;

/**
 * Created by ahuskano on 9/4/2014.
 */
public class FragmentGame extends SensorFragment {

    private GameView view;
    private final String TAG = FragmentGame.class.getSimpleName();
    private final String KEY_COINS = TAG + ".coins";

    @Override
    protected void sensorEvent(SensorEvent sensorEvent) {
        view.setxBoss((int) sensorEvent.values[0]);
        view.setyBoss((int) sensorEvent.values[1]);
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
    protected void initView(View view, Bundle bundle) {
        this.view = (GameView) view.findViewById(R.id.view);
        if (bundle != null)
            this.view.setCoins(bundle.getInt(KEY_COINS));


    }

    @Override
    public void onResume() {
        super.onResume();
        setupGame();
        view.setActive(true);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(KEY_COINS, view.getCoins());

    }

    @Override
    public void onPause() {
        super.onPause();
        view.setActive(false);
    }

    private void setupGame() {
        view.setFm(getChildFragmentManager());
        view.setBoss(BitmapFactory.decodeResource(getContext().getResources(), R.drawable.cale_medo_small));
        view.setFood(BitmapFactory.decodeResource(getContext().getResources(), R.drawable.cale_kosnica_medium));
        Display display = getActivity().getWindowManager().getDefaultDisplay();
        view.setMaxX(display.getWidth());
        view.setMaxY((int)(display.getHeight()*0.9));
        view.setSeeds();
        int  x=10>display.getWidth()?(int)(display.getWidth()*0.5):10;
        int  y=10>display.getHeight()?(int)(display.getHeight()*0.5):10;
        view.setWidthFood(x);
        view.setHeightFood(y);
        view.setPoints((TextView) getView().findViewById(R.id.tvCoins));
        view.setHighScore((TextView) getView().findViewById(R.id.tvHighScore));
        view.setTime((TextView) getView().findViewById(R.id.tvTime));
        view.setActivity(getActivity());
        view.start();
    }

    @Override
    protected String getLogTag() {
        return FragmentGame.class.getSimpleName();
    }
}
