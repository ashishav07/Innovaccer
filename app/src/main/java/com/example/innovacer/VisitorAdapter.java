package com.example.innovacer;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class VisitorAdapter extends RecyclerView.Adapter<VisitorViewHolder> {
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private Context context;
    private ArrayList<Visitor> visitorArrayList;
    public VisitorAdapter(Context context,ArrayList<Visitor> visitorArrayList){
        this.context = context;
        this.visitorArrayList = visitorArrayList;
    }
    @NonNull
    @Override
    public VisitorViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.visitor_card_view,parent,false);
        return new VisitorViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VisitorViewHolder holder, final int position) {
        holder.hName.setText(visitorArrayList.get(position).gethName());
        holder.hMobile.setText(visitorArrayList.get(position).gethMobile());
        holder.hEmail.setText(visitorArrayList.get(position).gethEmail());
        holder.hAddress.setText(visitorArrayList.get(position).gethAddress());
        holder.vName.setText(visitorArrayList.get(position).getvName());
        holder.vMobile.setText(visitorArrayList.get(position).getvMobile());
        holder.vEmail.setText(visitorArrayList.get(position).getvEmail());
        final String id = visitorArrayList.get(position).getId();
        holder.checkout.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View view) {
                DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss");
                LocalDateTime now = LocalDateTime.now();
                db.collection("Visitor Details").document(id).update("Check Out Time",dtf.format(now)).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        Toast.makeText(context,"Checked Out Successfully",Toast.LENGTH_SHORT).show();
                        context.startActivity(new Intent(context,MainActivity.class));
                    }
                });
            }
        });
    }
    @Override
    public int getItemCount() {
        return visitorArrayList.size();
    }
}
