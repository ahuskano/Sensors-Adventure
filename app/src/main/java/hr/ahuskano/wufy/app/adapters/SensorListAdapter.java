package hr.ahuskano.wufy.app.adapters;

import android.content.Context;
import android.hardware.Sensor;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import hr.ahuskano.wufy.app.R;

/**
 * Created by ahuskano on 8/23/2014.
 */
public class SensorListAdapter extends BaseAdapter {
    private final String TAG = SensorListAdapter.class.getSimpleName();

    private List<Sensor> sensorList;
    private Context context;


    public SensorListAdapter(Context context, List<Sensor> sensorList) {
        this.sensorList = sensorList;
        this.context = context;
    }

    @Override
    public int getCount() {
        return sensorList.size();
    }

    @Override
    public Object getItem(int position) {
        return sensorList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.sensor_list_item, viewGroup, false);
        }
        ((TextView) view.findViewById(R.id.tvSensorTitle)).setText(((Sensor) getItem(position)).getName());
        return view;
    }
}
