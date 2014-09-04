package hr.ahuskano.wufy.app.dialogs;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import hr.ahuskano.wufy.app.R;

/**
 * Created by ahuskano on 9/4/2014.
 */
public class GameFinishedDialog extends DialogFragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        getDialog().setTitle("TItle");
        View view = inflater.inflate(R.layout.fragment_dialog_game, container, false);

        return view;
    }


}
