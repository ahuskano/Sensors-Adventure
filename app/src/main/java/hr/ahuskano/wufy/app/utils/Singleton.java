package hr.ahuskano.wufy.app.utils;

import android.hardware.Sensor;

/**
 * Created by ahuskano on 8/24/2014.
 */
public class Singleton {

    private static Singleton instance;

    private int containerId = 0;

    private Sensor sensor;

    private Singleton() {

    }

    public static Singleton getInstance() {
        if (instance == null) instance = new Singleton();
        return instance;
    }

    public int getContainerId() {
        return containerId;
    }

    public void setContainerId(int containerId) {
        this.containerId = containerId;
    }


    public Sensor getSensor() {
        return sensor;
    }

    public void setSensor(Sensor sensor) {
        this.sensor = sensor;
    }
}
