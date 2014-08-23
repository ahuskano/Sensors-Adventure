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
public abstract class BaseFragment extends FragmentActivity implements MenuAdapter.MenuListener {

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
        menuDrawer.setMenuView(listView);

    }

    private List<Object> getItems(List<Object> menu) {
        menu.add(new Item(getString(R.string.available_sensors)));
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
