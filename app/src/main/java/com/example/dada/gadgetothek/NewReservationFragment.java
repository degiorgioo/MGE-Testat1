package com.example.dada.gadgetothek;


import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.dada.gadgetothek.domain.Gadget;


public class NewReservationFragment extends Fragment {


    public NewReservationFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_new_reservation, container, false);
        RecyclerView rV = (RecyclerView) v.findViewById(R.id.listView_gadgets);

        ItemTouchHelper.SimpleCallback simpleItemTouchCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int swipeDir) {
                Gadget g = ((OnFragmentActivityInteraction) getActivity()).getAllGadgetAsList().get(viewHolder.getAdapterPosition());
                ((OnFragmentActivityInteraction) getActivity()).reserveGadget(g);
            }
        };

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleItemTouchCallback);
        itemTouchHelper.attachToRecyclerView(rV);

        getActivity().setTitle("Available gadgets");

        if(((OnFragmentActivityInteraction) getActivity()).getLibServiceSession().isLoggedIn()){
            ((OnFragmentActivityInteraction) getActivity()).getAllGadgets(v);
        }else{
            Toast.makeText(getActivity(), "Please login", Toast.LENGTH_LONG).show();
        }

        return v;
    }


}
