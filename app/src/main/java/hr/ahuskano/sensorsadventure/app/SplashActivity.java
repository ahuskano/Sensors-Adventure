package hr.ahuskano.sensorsadventure.app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;


/**
 * Created by ahuskano on 9/7/2014.
 */
public class SplashActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        //getActionBar().hide();
        new Thread() {
            @Override
            public void run() {
                try {
                    sleep(800);
                    startActivity(new Intent(SplashActivity.this, MainActivity.class));
                } catch (Exception e) {
                } finally {
                    finish();
                }
            }
        }.start();
    }
}
