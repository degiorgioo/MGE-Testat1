package com.example.dada.gadgetothek;


import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

public class GadgetViewHolder extends RecyclerView.ViewHolder{

    TextView textViewGadgetName;
    TextView textViewGadgetID;
    TextView textViewGadgetCondition;
    View parent;


    public GadgetViewHolder(View parent, TextView textViewGadgetID, TextView textViewGadgetName, TextView textViewGadgetCondition){
        super(parent);
        this.parent = parent;
        this.textViewGadgetID = textViewGadgetID;
        this.textViewGadgetName = textViewGadgetName;
        this.textViewGadgetCondition = textViewGadgetCondition;
    }
}
