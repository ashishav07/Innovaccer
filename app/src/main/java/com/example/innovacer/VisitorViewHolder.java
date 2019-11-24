package com.example.innovacer;

import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class VisitorViewHolder extends RecyclerView.ViewHolder {
    public TextView vName,vEmail,vMobile,hName,hEmail,hMobile,hAddress;
    public Button checkout;
    private Context context;
    public VisitorViewHolder(@NonNull View itemView) {
        super(itemView);
        context = itemView.getContext();
        vName = itemView.findViewById(R.id.vName);
        vEmail = itemView.findViewById(R.id.vEmail);
        vMobile = itemView.findViewById(R.id.vMobile);
        hAddress = itemView.findViewById(R.id.hAddress);
        hEmail = itemView.findViewById(R.id.hEmail);
        hMobile = itemView.findViewById(R.id.hMobile);
        hName = itemView.findViewById(R.id.hName);
        checkout = itemView.findViewById(R.id.checkout);
    }
}
