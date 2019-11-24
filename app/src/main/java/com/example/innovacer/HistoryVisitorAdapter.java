package com.example.innovacer;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class HistoryVisitorAdapter extends RecyclerView.Adapter<HistoryVisitorViewHolder> {
    private Context context;
    private ArrayList<Visitor> visitorArrayList;
    public HistoryVisitorAdapter(Context context,ArrayList<Visitor> visitorArrayList) {
        this.context = context;
        this.visitorArrayList = visitorArrayList;
    }

    @NonNull
    @Override
    public HistoryVisitorViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.history_visitor_layout,parent,false);
        return new HistoryVisitorViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HistoryVisitorViewHolder holder, int position) {
        holder.hName.setText(visitorArrayList.get(position).gethName());
        holder.hMobile.setText(visitorArrayList.get(position).gethMobile());
        holder.hEmail.setText(visitorArrayList.get(position).gethEmail());
        holder.hAddress.setText(visitorArrayList.get(position).gethAddress());
        holder.vName.setText(visitorArrayList.get(position).getvName());
        holder.vMobile.setText(visitorArrayList.get(position).getvMobile());
        holder.vEmail.setText(visitorArrayList.get(position).getvEmail());
    }

    @Override
    public int getItemCount() {
        return visitorArrayList.size();
    }
}
