package hr.ahuskano.sensorsadventure.app;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.FrameLayout;


import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.Tracker;

import net.simonvt.menudrawer.MenuDrawer;
import net.simonvt.menudrawer.Position;

import hotchemi.android.rate.AppRate;
import hotchemi.android.rate.OnClickButtonListener;
import hr.ahuskano.sensorsadventure.app.types.Item;
import hr.ahuskano.sensorsadventure.app.utils.Singleton;
import hr.ahuskano.sensorsadventure.app.utils.Utils;
import hr.ahuskano.sensorsadventure.app.R;


public class MainActivity extends DrawerMenuActivity {
    private FrameLayout container;
    private final String TAG = MainActivity.class.getSimpleName();
    private int available_fragment = 1;
    private final String KEY_BUNDLE = TAG + ".bundle";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState != null)
            available_fragment = savedInstanceState.getInt(KEY_BUNDLE);
        initDrawer();
        initView();
        initRateDialog();
    }

    private void initRateDialog() {
        AppRate.with(this)
                .setInstallDays(1) // default 10, 0 means install day.
                .setLaunchTimes(3) // default 10
                .setRemindInterval(2) // default 1
                .setShowNeutralButton(true) // default true
                .monitor();
        AppRate.showRateDialogIfMeetsConditions(this);
    }



    private void initView() {
        container = (FrameLayout) findViewById(R.id.flFragments);
        Singleton.getInstance().setContainerId(container.getId());
        initFirstFragment();

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(KEY_BUNDLE, available_fragment);
    }

    private void initFirstFragment() {
        getSupportFragmentManager().beginTransaction().add(container.getId(), Utils.provideFragment(available_fragment)).commit();

    }

    private void initDrawer() {
        menuDrawer.setContentView(R.layout.activity_main);
        menuDrawer.setTouchMode(MenuDrawer.TOUCH_MODE_FULLSCREEN);
        menuDrawer.setSlideDrawable(R.drawable.ic_drawer);
        menuDrawer.setDrawerIndicatorEnabled(true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_settings:
                return true;
            case android.R.id.home:
                menuDrawer.toggleMenu();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected Position getDrawerPosition() {
        return Position.START;
    }

    @Override
    protected int getDragMode() {
        return MenuDrawer.MENU_DRAG_CONTENT;
    }

    @Override
    protected void onMenuItemClicked(int position, Item item) {
        getSupportFragmentManager().beginTransaction().replace(container.getId(), Utils.provideFragment(item.getId())).commit();
        available_fragment = item.getId();
        /*
        switch (item.getId()) {
            case FRAGMENT_AVAILABLE_SENSORS:
                getSupportFragmentManager().beginTransaction().replace(container.getId(), new FragmentAvailableSensors()).commit();
                break;
            case FRAGMENT_COMPAS:
                getSupportFragmentManager().beginTransaction().replace(container.getId(), new FragmentCompas()).commit();
                break;
            case FRAGMENT_SHUFFED_DETECT:
                getSupportFragmentManager().beginTransaction().replace(container.getId(), new FragmentShuffedDetect()).commit();
                break;
            case FRAGMENT_LIGHT:
                getSupportFragmentManager().beginTransaction().replace(container.getId(), new FragmentLight()).commit();
                break;
            case FRAGMENT_GAME:
                getSupportFragmentManager().beginTransaction().replace(container.getId(), new FragmentGame()).commit();
                break;
        }
        */
        menuDrawer.closeMenu();
    }

    @Override
    public void onBackPressed() {
        if (menuDrawer.getDrawerState() == MenuDrawer.STATE_OPEN || menuDrawer.getDrawerState() == MenuDrawer.STATE_OPENING) {
            menuDrawer.closeMenu();
            return;
        }
        super.onBackPressed();
    }

    @Override
    protected void onStart() {
        super.onStart();

    }

}
