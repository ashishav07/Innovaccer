package com.example.innovacer;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class CurrentFragment extends Fragment {
    private RecyclerView recyclerView;
    private ArrayList<Visitor> visitorArrayList;
    private LinearLayout linearLayout;
    private ProgressBar pb;
    private TextView textView;
    NavigationView navigationView;
    View root;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
         root = inflater.inflate(R.layout.fragment_current, container, false);
        recyclerView = new RecyclerView(root.getContext());
        recyclerView = root.findViewById(R.id.visitorRecyclerView);
        linearLayout = root.findViewById(R.id.linearLayout);
        textView = root.findViewById(R.id.noVisit);
        pb = root.findViewById(R.id.pb);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        visitorArrayList = new ArrayList<>();
        loadData();
        return root;
    }
    private void loadData(){
        if(visitorArrayList.size()>0)
            visitorArrayList.clear();
        final String[] checkOut = new String[1];
        db.collection("Visitor Details").orderBy("Timestamps", Query.Direction.DESCENDING).get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        for(DocumentSnapshot documentSnapshot : task.getResult()){
                            Visitor visitor = new Visitor(
                                documentSnapshot.getString("vName"),
                                documentSnapshot.getString("vNumber"),
                                documentSnapshot.getString("vEmail"),
                                documentSnapshot.getString("hName"),
                                documentSnapshot.getString("hEmail"),
                                documentSnapshot.getString("hNumber"),
                                documentSnapshot.getString("hAddress"),
                                documentSnapshot.getString("id"),
                                documentSnapshot.getString("Check In Time"),
                                checkOut[0] = documentSnapshot.getString("Check Out Time"),
                                documentSnapshot.getString("Check In Date")
                            );
                            if(checkOut[0].equals("null")) {
                                visitorArrayList.add(visitor);
                            }
                            if(visitorArrayList.size() == 0){
                                textView.setVisibility(View.VISIBLE);
                            }
                        }
                        pb.setVisibility(View.GONE);
                        VisitorAdapter visitorAdapter = new VisitorAdapter(getActivity(),visitorArrayList);
                        recyclerView.setAdapter(visitorAdapter);
                    }
                });
    }
}
