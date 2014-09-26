package hr.ahuskano.sensorsadventure.app.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.Tracker;

import hr.ahuskano.sensorsadventure.app.R;
import hr.ahuskano.sensorsadventure.app.SensorsAdventureApp;
import hr.ahuskano.sensorsadventure.app.utils.Utils;

/**
 * Created by ahuskano on 8/23/2014.
 */
public abstract class BaseFragment extends Fragment {

    protected abstract int getResourceLayout();

    protected abstract void initView(View view, Bundle bundle);

    protected abstract String getLogTag();

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        initView(view, savedInstanceState);
        Utils.sendScreenName(getLogTag(),getActivity());
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(getResourceLayout(), container, false);
    }

    protected Context getContext() {
        return getActivity().getBaseContext();
    }

    protected void logIt(String message) {
        if (message == null)
            message = getString(R.string.message_null);
        Log.d(getLogTag(), message);
    }

    protected FragmentManager getFraManager() {

        return getActivity().getSupportFragmentManager();
    }
}
