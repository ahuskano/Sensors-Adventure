package hr.ahuskano.wufy.app.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import hr.ahuskano.wufy.app.R;

/**
 * Created by ahuskano on 8/23/2014.
 */
public abstract class BaseFragment extends Fragment {

    protected abstract int getResourceLayout();

    protected abstract void initView(View view);

    protected abstract String getLogTag();

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        initView(view);
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
