package com.example.dada.gadgetothek;


import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.dada.gadgetothek.domain.Loan;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.List;

public class LoansAdapter extends RecyclerView.Adapter<LoanViewHolder> {

    private List<Loan> allLoans;

    public LoansAdapter(List<Loan> objects) {
        allLoans = new ArrayList<>();
        this.allLoans = objects;
    }


    @Override
    public LoanViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater linflater = LayoutInflater.from(parent.getContext());
        View v = linflater.inflate(R.layout.rowlayoutloan, parent, false);

        TextView loanTitle = (TextView) v.findViewById(R.id.txt_loanTitle);
        TextView loanGadgetID = (TextView) v.findViewById(R.id.txt_gadgetId);
        TextView loanPickupDate = (TextView) v.findViewById(R.id.txt_pickupDate);
        TextView loanReturnDate = (TextView) v.findViewById(R.id.txt_returnDate);

        LoanViewHolder loanViewHolder = new LoanViewHolder(v, loanTitle, loanPickupDate, loanGadgetID, loanReturnDate);

        return loanViewHolder;
    }

    @Override
    public void onBindViewHolder(LoanViewHolder holder, int position) {
        final Loan l = allLoans.get(position);
        DateFormat dateFormat = DateFormat.getDateInstance();

        holder.textViewLoanTitle.setText("Name: " + l.getGadget().getName());
        holder.textViewLoanGadgetId.setText("ID: " + l.getGadget().getInventoryNumber());
        holder.textViewLoanPickupDate.setText("Picked up: " + dateFormat.format(l.getPickupDate()));
        //holder.textViewLoandReturnDate.setText("Return date: " + dateFormat.format(l.getReturnDate()));
    }

    @Override
    public int getItemCount() {
        return allLoans.size();
    }

}
