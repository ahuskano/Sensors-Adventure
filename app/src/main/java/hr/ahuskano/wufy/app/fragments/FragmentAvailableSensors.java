package hr.ahuskano.wufy.app.fragments;

import android.content.Intent;
import android.hardware.Sensor;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import hr.ahuskano.wufy.app.R;
import hr.ahuskano.wufy.app.SensorDetailsActivity;
import hr.ahuskano.wufy.app.adapters.SensorListAdapter;
import hr.ahuskano.wufy.app.utils.Singleton;
import hr.ahuskano.wufy.app.utils.Utils;

/**
 * Created by ahuskano on 8/23/2014.
 */
public class FragmentAvailableSensors extends BaseFragment implements AdapterView.OnItemClickListener {

    private final String TAG = FragmentAvailableSensors.class.getSimpleName();
    private ListView listView;
    private SensorListAdapter sensorListAdapter;

    @Override
    protected int getResourceLayout() {
        return R.layout.fragment_available_sensors;
    }

    @Override
    protected void initView(View view) {
        listView = (ListView) view.findViewById(R.id.lvSensors);
        sensorListAdapter = new SensorListAdapter(getContext(), Utils.getAvailableSensors(getContext()));
        listView.setAdapter(sensorListAdapter);
        listView.setOnItemClickListener(this);

    }

    @Override
    protected String getLogTag() {
        return TAG;
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
        Singleton.getInstance().setSensor((Sensor) sensorListAdapter.getItem(position));
        startActivity(new Intent(getActivity(), SensorDetailsActivity.class));
    }
}