package hr.ahuskano.sensorsadventure.app.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by ahuskano on 9/7/2014.
 */
public class SharedPreferenceManagment {
    public static final String TAG = SharedPreferenceManagment.class.getSimpleName();
    public static final String KEY_PREFERENCES = TAG + "preferences";
    public static final String KEY_HIGH_SCORE = KEY_PREFERENCES + "HIGH_SCORE";

    public static SharedPreferences getPreferences(Context context) {
        return context.getSharedPreferences(KEY_PREFERENCES, Context.MODE_PRIVATE);
    }

    public static void saveScore(Context context, int score) {
        getPreferences(context).edit().putInt(KEY_HIGH_SCORE, score).commit();
    }

    public static int getScore(Context context) {
        return getPreferences(context).getInt(KEY_HIGH_SCORE, 0);
    }

}
