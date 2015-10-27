package com.example.dada.gadgetothek;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;


public class LoanViewHolder extends RecyclerView.ViewHolder {

    View parent;
    TextView textViewLoanTitle;
    TextView textViewLoanPickupDate;
    TextView textViewLoanGadgetId;
    TextView textViewLoandReturnDate;

    public LoanViewHolder(View parent, TextView textViewLoanTitle, TextView textViewLoanPickupDate, TextView textViewLoanGadgetId, TextView textViewreturnDate){
        super(parent);
        this.parent = parent;
        this.textViewLoanTitle = textViewLoanTitle;
        this.textViewLoanPickupDate = textViewLoanPickupDate;
        this.textViewLoanGadgetId = textViewLoanGadgetId;
        this.textViewLoandReturnDate = textViewreturnDate;
    }
}
