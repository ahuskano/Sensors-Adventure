package hr.ahuskano.wufy.app.types;

import hr.ahuskano.wufy.app.R;

/**
 * Created by ahuskano on 8/23/2014.
 */
public class Item {

    private String title;
    private int icon;
    private int id;

    public Item(int id,String title, int icon) {
        this.id=id;
        this.title = title;
        this.icon=icon;
    }

    public Item(int id, String title){
        this.id=id;
        this.title=title;
        this.icon= R.drawable.ic_launcher;
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

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }
}
