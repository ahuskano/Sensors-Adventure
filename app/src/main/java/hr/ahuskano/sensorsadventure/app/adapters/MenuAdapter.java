package hr.ahuskano.sensorsadventure.app.adapters;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import hr.ahuskano.sensorsadventure.app.types.Category;
import hr.ahuskano.sensorsadventure.app.types.Item;
import hr.ahuskano.sensorsadventure.app.utils.Utils;
import hr.ahuskano.sensorsadventure.app.R;

/**
 * Created by ahuskano on 8/23/2014.
 */
public class MenuAdapter extends BaseAdapter {

    public interface MenuListener {
        void onActiveViewChanged(View v);
    }

    private Context context;
    private List<Object> menuItems;
    private MenuListener menuListener;
    private int menuActivePosition;


    public MenuAdapter(Context context, List<Object> menuItems) {
        this.context = context;
        this.menuItems = menuItems;

    }

    @Override
    public int getCount() {
        return menuItems.size();
    }

    @Override
    public Object getItem(int position) {
        return menuItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemViewType(int position) {
        return getItem(position) instanceof Item ? 0 : 1;
    }

    @Override
    public boolean isEnabled(int position) {
        return getItem(position) instanceof Item;
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    @Override
    public View getView(int position, View view, ViewGroup parent) {
        Object item = menuItems.get(position);
        if (item instanceof Category) {
            if (view == null)
                view = LayoutInflater.from(context).inflate(R.layout.menu_row_category, parent, false);
            ((TextView) view).setText(((Category) item).getTitle());
        } else if (item instanceof Item) {
            if (view == null)
                view = LayoutInflater.from(context).inflate(R.layout.menu_row_item, parent, false);
            TextView viewItem = ((TextView) view.findViewById(R.id.tvLabel));
            viewItem.setText(((Item) item).getTitle());
            TextView viewIcone = ((TextView) view.findViewById(R.id.tvIcon));
            viewIcone.setText(((Item) item).getIcon());
            Utils.setFont(viewIcone, getContext().getResources().getString(R.string.font_fontawesome));
        }
        view.setTag(R.id.mdActiveViewPosition, position);
        if (position == menuActivePosition) {
            menuListener.onActiveViewChanged(view);
        }
        return view;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public List<Object> getMenuItems() {
        return menuItems;
    }

    public void setMenuItems(List<Object> menuItems) {
        this.menuItems = menuItems;
    }

    public MenuListener getMenuListener() {
        return menuListener;
    }

    public void setMenuListener(MenuListener menuListener) {
        this.menuListener = menuListener;
    }

    public int getMenuActivePosition() {
        return menuActivePosition;
    }

    public void setMenuActivePosition(int menuActivePosition) {
        this.menuActivePosition = menuActivePosition;
    }
}
