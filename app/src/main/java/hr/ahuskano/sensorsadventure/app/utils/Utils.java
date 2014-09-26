package hr.ahuskano.sensorsadventure.app.utils;

import android.app.Activity;
import android.content.Context;
import android.graphics.Typeface;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.support.v4.app.Fragment;
import android.widget.TextView;

import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.Tracker;

import java.util.List;

import hr.ahuskano.sensorsadventure.app.DrawerMenuActivity;
import hr.ahuskano.sensorsadventure.app.SensorsAdventureApp;
import hr.ahuskano.sensorsadventure.app.fragments.FragmentAvailableSensors;
import hr.ahuskano.sensorsadventure.app.fragments.FragmentCompas;
import hr.ahuskano.sensorsadventure.app.fragments.FragmentGame;
import hr.ahuskano.sensorsadventure.app.fragments.FragmentLight;
import hr.ahuskano.sensorsadventure.app.fragments.FragmentShuffedDetect;

/**
 * Created by ahuskano on 8/23/2014.
 */
public class Utils {

    public static List<Sensor> getAvailableSensors(Context context) {
        return ((SensorManager) context.getSystemService(Context.SENSOR_SERVICE)).getSensorList(Sensor.TYPE_ALL);
    }

    public static boolean sensorExist(Context context, int type) {

        return ((SensorManager) context.getSystemService(Context.SENSOR_SERVICE)).getSensorList(type).size() > 0;
    }

    public static void setFont(TextView textView, String fontName) {
        Typeface font = Typeface.createFromAsset(textView.getContext().getAssets(), "fonts/" + fontName);
        textView.setTypeface(font);

    }

    public static Fragment provideFragment(int tag) {
        switch (tag) {
            case DrawerMenuActivity.FRAGMENT_AVAILABLE_SENSORS:
                return new FragmentAvailableSensors();
            case DrawerMenuActivity.FRAGMENT_LIGHT:
                return new FragmentLight();
            case DrawerMenuActivity.FRAGMENT_GAME:
                return new FragmentGame();
            case DrawerMenuActivity.FRAGMENT_COMPAS:
                return new FragmentCompas();
            case DrawerMenuActivity.FRAGMENT_SHUFFED_DETECT:
                return new FragmentShuffedDetect();
            default:
                return null;
        }

    }

    public static void sendScreenName(String name, Activity activity) {
        Tracker t = ((SensorsAdventureApp) activity.getApplication()).getTracker(
                SensorsAdventureApp.TrackerName.APP_TRACKER);
        t.setScreenName(name);
        t.send(new HitBuilders.ScreenViewBuilder().build());
    }

    public static void sendScoreEvent(long highScore, Activity activity) {
        Tracker t = ((SensorsAdventureApp) activity.getApplication()).getTracker(
                SensorsAdventureApp.TrackerName.APP_TRACKER);
        t.send(new HitBuilders.EventBuilder().setCategory("HighScore").setAction(String.valueOf(highScore)).build());
    }

    public static void sendSensorEvent(String name, Activity activity) {
        Tracker t = ((SensorsAdventureApp) activity.getApplication()).getTracker(SensorsAdventureApp.TrackerName.APP_TRACKER);
        t.send(new HitBuilders.EventBuilder().setCategory("Sensor").setAction(name).build());
    }
}
