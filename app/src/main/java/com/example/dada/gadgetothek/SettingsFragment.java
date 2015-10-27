package com.example.dada.gadgetothek;


import android.app.Fragment;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class SettingsFragment extends Fragment {

    public SettingsFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_settings, container, false);

        final EditText settingsServerName = (EditText) root.findViewById(R.id.textBox_ServerNameSettings);
        final FloatingActionButton fab = (FloatingActionButton) root.findViewById(R.id.fab3);

        Button btnSetSettings = (Button)root.findViewById(R.id.btn_setSettings);
        settingsServerName.setText(((OnFragmentActivityInteraction) getActivity()).getSharedPreferencesServername());

        getActivity().setTitle("Settings");

        btnSetSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((OnFragmentActivityInteraction) getActivity()).setServerName(settingsServerName.getText().toString());
                ((OnFragmentActivityInteraction) getActivity()).setSharedPreferences(settingsServerName.getText().toString());
                Toast.makeText(getActivity(), "Update settings erfolgreich", Toast.LENGTH_SHORT).show();
            }
        });

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((OnFragmentActivityInteraction) getActivity()).switchFragment(new HomeFragment());
            }
        });


        return root;
    }


}
