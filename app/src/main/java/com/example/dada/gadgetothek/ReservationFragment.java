package com.example.dada.gadgetothek;


import android.app.Fragment;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.dada.gadgetothek.domain.Reservation;


public class ReservationFragment extends Fragment {


    public ReservationFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        final View v = inflater.inflate(R.layout.fragment_reservation, container, false);
        RecyclerView rV = (RecyclerView) v.findViewById(R.id.recyclerViewReservations);
        FloatingActionButton fab = (FloatingActionButton) v.findViewById(R.id.fab2);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((OnFragmentActivityInteraction) getActivity()).switchFragment(new NewReservationFragment());
            }
        });

        ItemTouchHelper.SimpleCallback simpleItemTouchCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int swipeDir) {
                Reservation r = ((OnFragmentActivityInteraction) getActivity()).getAllReservationAsList().get(viewHolder.getAdapterPosition());
                ((OnFragmentActivityInteraction) getActivity()).deleteReservation(r);
            }
        };

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleItemTouchCallback);

        itemTouchHelper.attachToRecyclerView(rV);

        getActivity().setTitle("My Reservations");

        if(((OnFragmentActivityInteraction) getActivity()).getLibServiceSession().isLoggedIn()){
            ((OnFragmentActivityInteraction) getActivity()).getAllReservation(v);
        }else{
            Toast.makeText(getActivity(), "Please Login", Toast.LENGTH_LONG).show();
        }
        return v;
    }

}
