package hr.ahuskano.wufy.app;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.SeekBar;

import net.simonvt.menudrawer.MenuDrawer;
import net.simonvt.menudrawer.Position;

import hr.ahuskano.wufy.app.types.Item;


public class MainActivity extends BaseFragment {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initDrawer();
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
}
