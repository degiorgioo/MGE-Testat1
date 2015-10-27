package com.example.dada.gadgetothek;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.dada.gadgetothek.domain.Reservation;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.List;

public class ReservationAdapter extends RecyclerView.Adapter<ReservationViewHolder>{

    private List<Reservation> allReservation;

    public ReservationAdapter(List<Reservation> objects) {
        allReservation = new ArrayList<>();
        this.allReservation = objects;
    }

    @Override
    public ReservationViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater rinflater = LayoutInflater.from(parent.getContext());
        View v = rinflater.inflate(R.layout.rowlayoutreservation, parent, false);

        TextView reservationId = (TextView)v.findViewById(R.id.txt_reservationId);
        TextView reservationGadgetName = (TextView)v.findViewById(R.id.txt_reservationGadgetName);
        TextView reservationIsFinished = (TextView)v.findViewById(R.id.txt_reservationIsFinished);
        TextView reservationDate = (TextView) v.findViewById(R.id.txt_reservationDate);

        ReservationViewHolder reservationViewHolder = new ReservationViewHolder(v, reservationId, reservationGadgetName, reservationIsFinished, reservationDate);

        return reservationViewHolder;
    }

    @Override
    public void onBindViewHolder(ReservationViewHolder holder, int position) {
        final Reservation r = allReservation.get(position);

        DateFormat dateFormat = DateFormat.getDateInstance();

        holder.textViewGadgetName.setText("Gadgetname: " + r.getGadget().getName());
        holder.textViewReservationFinished.setText("Reservation finished: " + Boolean.valueOf(r.getFinished()).toString());
        holder.textViewReservationID.setText("Reservation ID: " + r.getReservationId());
        holder.textViewReservationDate.setText("Reservation date: " + dateFormat.format(r.getReservationDate()));
    }

    @Override
    public int getItemCount() {
        return allReservation.size();
    }

}
