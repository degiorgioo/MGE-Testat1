package com.example.dada.gadgetothek;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.dada.gadgetothek.domain.Gadget;

import java.util.ArrayList;
import java.util.List;

public class GagdgetAdapter extends RecyclerView.Adapter<GadgetViewHolder>{

    List<Gadget> allGadgets;
    Context context;

    public GagdgetAdapter(List<Gadget> objects, Context c){
        allGadgets = new ArrayList<>();
        this.allGadgets = objects;
        this.context = c;
    }

    @Override
    public GadgetViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater linflater = LayoutInflater.from(parent.getContext());
        View v = linflater.inflate(R.layout.rowlayoutgadget, parent, false);

        TextView gadgetID = (TextView) v.findViewById(R.id.txt_gadgetId);
        TextView gadgetName = (TextView) v.findViewById(R.id.txt_gadgetName);
        TextView gadgetCondition = (TextView) v.findViewById(R.id.txt_gadgetCondition);

        GadgetViewHolder gadgetViewHolder = new GadgetViewHolder(v, gadgetID, gadgetName, gadgetCondition);

        return gadgetViewHolder;
    }

    @Override
    public void onBindViewHolder(GadgetViewHolder holder, final int position) {
        final Gadget g = allGadgets.get(position);

        holder.textViewGadgetID.setText("Inventory number: " + g.getInventoryNumber());
        holder.textViewGadgetName.setText("Gadget name: " + g.getName());
        holder.textViewGadgetCondition.setText("Gadget condition: " + g.getCondition().toString());

    }

    @Override
    public int getItemCount() {
        return allGadgets.size();
    }
}
