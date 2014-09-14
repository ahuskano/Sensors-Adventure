package hr.ahuskano.wufy.app.utils;

import android.content.Context;
import android.graphics.Typeface;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.support.v4.app.Fragment;
import android.widget.TextView;

import java.util.List;

import hr.ahuskano.wufy.app.DrawerMenuActivity;
import hr.ahuskano.wufy.app.fragments.FragmentAvailableSensors;
import hr.ahuskano.wufy.app.fragments.FragmentCompas;
import hr.ahuskano.wufy.app.fragments.FragmentGame;
import hr.ahuskano.wufy.app.fragments.FragmentLight;
import hr.ahuskano.wufy.app.fragments.FragmentShuffedDetect;

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
}
