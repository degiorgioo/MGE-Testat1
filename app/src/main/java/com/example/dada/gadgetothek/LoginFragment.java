package com.example.dada.gadgetothek;


import android.app.Fragment;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;


public class LoginFragment extends Fragment{


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_login, container, false);
        final EditText userTextBox = (EditText)root.findViewById(R.id.textBox_username);
        final EditText passwordTextBox = (EditText)root.findViewById(R.id.textBox_password);
        final EditText servernameTextBox = (EditText)root.findViewById(R.id.textBox_servername);
        final FloatingActionButton fab = (FloatingActionButton) root.findViewById(R.id.fab);

        getActivity().setTitle("Login");

        try{
            ((OnFragmentActivityInteraction) getActivity()).getLibServiceSession().getCurrenServername().toString();

            servernameTextBox.setText(((OnFragmentActivityInteraction) getActivity()).getLibServiceSession().getCurrenServername().toString());

            root.findViewById(R.id.btn_login).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ((OnFragmentActivityInteraction) getActivity()).loginUser(userTextBox.getText().toString(), passwordTextBox.getText().toString(), servernameTextBox.getText().toString());
                }
            });
        }catch (Exception e){
            Toast.makeText(getActivity(), "Please configure server [settings]", Toast.LENGTH_SHORT).show();
        }

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((OnFragmentActivityInteraction) getActivity()).switchFragment(new HomeFragment());
            }
        });

        return root;
    }
}
