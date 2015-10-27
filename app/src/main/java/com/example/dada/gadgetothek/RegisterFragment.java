package com.example.dada.gadgetothek;


import android.app.Fragment;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;


/**
 * A simple {@link Fragment} subclass.
 */
public class RegisterFragment extends Fragment {


    public RegisterFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_register, container, false);
        final FloatingActionButton fab = (FloatingActionButton) root.findViewById(R.id.fab4);

        final EditText mailTextBoxRegister = (EditText)root.findViewById(R.id.textBox_emailRegister);
        final EditText usernameTextBoxRegister = (EditText)root.findViewById(R.id.textBox_usernameRegister);
        final EditText passwordTextBoxRegister = (EditText)root.findViewById(R.id.textBox_userPasswordRegister);
        final EditText studNummerTextBoxRegister = (EditText)root.findViewById(R.id.textBox_studNummerRegister);

        getActivity().setTitle("Registration");

        root.findViewById(R.id.btn_sendRegistration).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((OnFragmentActivityInteraction) getActivity()).registerUser(mailTextBoxRegister.getText().toString(),
                        passwordTextBoxRegister.getText().toString(), usernameTextBoxRegister.getText().toString(),
                        studNummerTextBoxRegister.getText().toString());
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
