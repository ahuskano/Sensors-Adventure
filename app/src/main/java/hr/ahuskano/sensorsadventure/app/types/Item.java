package hr.ahuskano.sensorsadventure.app.types;

import android.content.Context;

import hr.ahuskano.sensorsadventure.app.R;

/**
 * Created by ahuskano on 8/23/2014.
 */
public class Item {

    private String title;
    private String icon;
    private int id;

    public Item(Context context, int id,String title, String icon) {
        this.id=id;
        this.title = title;
        this.icon=icon;
    }

    public Item(Context context, int id, String title){
        this.id=id;
        this.title=title;
        this.icon=context.getResources().getString(R.string.icone_default);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }
}
