package com.example.dada.gadgetothek;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;


public class ReservationViewHolder extends RecyclerView.ViewHolder {

    TextView textViewReservationID;
    TextView textViewGadgetName;
    TextView textViewReservationFinished;
    TextView textViewReservationDate;
    View parent;

    public ReservationViewHolder(View parent, TextView id, TextView gadgetName, TextView reservationFinished, TextView textViewReservationDate){
        super(parent);
        this.parent = parent;
        this.textViewReservationID = id;
        this.textViewGadgetName = gadgetName;
        this.textViewReservationFinished = reservationFinished;
        this.textViewReservationDate = textViewReservationDate;
    }
}
