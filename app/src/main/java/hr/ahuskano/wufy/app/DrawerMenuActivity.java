package hr.ahuskano.wufy.app;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import net.simonvt.menudrawer.MenuDrawer;
import net.simonvt.menudrawer.Position;

import java.util.ArrayList;
import java.util.List;

import hr.ahuskano.wufy.app.adapters.MenuAdapter;
import hr.ahuskano.wufy.app.types.Item;

/**
 * Created by ahuskano on 8/23/2014.
 */
public abstract class DrawerMenuActivity extends FragmentActivity implements MenuAdapter.MenuListener {

    public static final int FRAGMENT_AVAILABLE_SENSORS = 1;
    public static final int FRAGMENT_COMPAS = 2;
    public static final int FRAGMENT_SHUFFED_DETECT = 3;
    public static final int FRAGMENT_SECOND = 6;
    public static final int FRAGMENT_LIGHT = 4;
    public static final int FRAGMENT_BALL = 6;
    public static final int FRAGMENT_GAME = 5;

    private static final String KEY_ACTIVE_POSITION =
            "hr.ahuskano.wufy.app.drawer.active";

    protected MenuAdapter menuAdapter;
    protected MenuDrawer menuDrawer;
    protected ListView listView;
    private int menuActivePosition = 0;

    protected abstract Position getDrawerPosition();

    protected abstract int getDragMode();

    protected abstract void onMenuItemClicked(int position, Item item);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
    }

    private void initView() {
        menuDrawer = MenuDrawer.attach(this, MenuDrawer.Type.BEHIND, getDrawerPosition(), getDragMode());
        List<Object> menuItems = new ArrayList<Object>();
        menuItems = getItems(menuItems);
        listView = new ListView(this);
        menuAdapter = new MenuAdapter(getBaseContext(), menuItems);
        menuAdapter.setMenuListener(this);
        menuAdapter.setMenuActivePosition(menuActivePosition);

        listView.setAdapter(menuAdapter);
        listView.setOnItemClickListener(menuItemClickListener);
        listView.setBackgroundColor(getResources().getColor(R.color.winter_sun_blue_darker_ligh));
        menuDrawer.setMenuView(listView);

    }

    private List<Object> getItems(List<Object> menu) {
        menu.add(new Item(getBaseContext(),FRAGMENT_AVAILABLE_SENSORS, getString(R.string.available_sensors_title),getResources().getString(R.string.icone_sensor_list)));
        menu.add(new Item(getBaseContext(),FRAGMENT_COMPAS, getString(R.string.compas_title),getResources().getString(R.string.icone_compas)));
        menu.add(new Item(getBaseContext(),FRAGMENT_SHUFFED_DETECT, getString(R.string.shuffed_detect_title),getResources().getString(R.string.icone_shuffed_detect)));
        menu.add(new Item(getBaseContext(),FRAGMENT_LIGHT, getString(R.string.light_title),getResources().getString(R.string.icone_light)));
        menu.add(new Item(getBaseContext(),FRAGMENT_BALL, getString(R.string.ball_title),getResources().getString(R.string.icone_ball)));
        menu.add(new Item(getBaseContext(),FRAGMENT_GAME,getString(R.string.fragment_game_title)));
        return menu;
    }

    private AdapterView.OnItemClickListener menuItemClickListener = new AdapterView.OnItemClickListener() {

        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
            menuActivePosition = position;
            menuDrawer.setActiveView(view, position);
            menuAdapter.setMenuActivePosition(position);
            onMenuItemClicked(position, (Item) menuAdapter.getItem(position));
        }
    };

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(KEY_ACTIVE_POSITION, menuActivePosition);
    }


    @Override
    public void onActiveViewChanged(View view) {
        menuDrawer.setActiveView(view, menuActivePosition);
    }

}
