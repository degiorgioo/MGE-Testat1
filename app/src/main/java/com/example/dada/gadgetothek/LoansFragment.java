package com.example.dada.gadgetothek;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class LoansFragment extends Fragment {

    public LoansFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_loans, container, false);

        getActivity().setTitle("Loans");

        if(((OnFragmentActivityInteraction) getActivity()).getLibServiceSession().isLoggedIn()){
            ((OnFragmentActivityInteraction) getActivity()).getAllLoans(root);

        }else{
        }
        return root;
    }

}
