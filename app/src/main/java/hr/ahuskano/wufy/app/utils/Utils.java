package hr.ahuskano.wufy.app.utils;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorManager;

import java.util.List;

/**
 * Created by ahuskano on 8/23/2014.
 */
public class Utils {

    public static List<Sensor> getAvailableSensors(Context context) {
        return ((SensorManager) context.getSystemService(Context.SENSOR_SERVICE)).getSensorList(Sensor.TYPE_ALL);
    }
}
