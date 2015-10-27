package com.example.dada.gadgetothek;


import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {


    public HomeFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_blank, container, false);

        ImageButton login = (ImageButton) v.findViewById(R.id.imageButtonLogin);
        ImageButton settings = (ImageButton) v.findViewById(R.id.imageButtonSettings);
        ImageButton newReservation = (ImageButton) v.findViewById(R.id.imageButtonAddReservation);
        ImageButton allReservations = (ImageButton) v.findViewById(R.id.imageButtonAllReservations);


        getActivity().setTitle("Home");


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((OnFragmentActivityInteraction) v.getContext()).switchFragment(new LoginFragment());
            }
        });

        settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((OnFragmentActivityInteraction) v.getContext()).switchFragment(new SettingsFragment());
            }
        });

        newReservation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((OnFragmentActivityInteraction) v.getContext()).switchFragment(new NewReservationFragment());
            }
        });

        allReservations.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((OnFragmentActivityInteraction) v.getContext()).switchFragment(new ReservationFragment());
            }
        });

        return v;
    }


}
