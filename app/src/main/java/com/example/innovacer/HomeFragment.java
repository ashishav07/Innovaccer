package com.example.innovacer;
import android.Manifest;
import android.app.ProgressDialog;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.firestore.FirebaseFirestore;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class HomeFragment extends Fragment implements View.OnClickListener {
    private View root;
    private EditText vName,vEmail,vNumber,hName,hAddress,hEmail,hNumber;
    private TextInputLayout vEmailLayout,vMobileLayout,hEmailLayout,hMobileLayout;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private final static int SEND_SMS_PERMISSION_REQ=1;
    private boolean isValidEmail(String email) {
        String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

        Pattern pattern = Pattern.compile(EMAIL_PATTERN);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    private Boolean isValidMobile(String number){
        String num_Pattern = "^[1-9]{1}[0-9]{9}$";
        Pattern pattern = Pattern.compile(num_Pattern);
        Matcher matcher = pattern.matcher(number);
        return matcher.matches();
    }
    private void sendEmail(String to,String message) {

        //Creating SendMail object
        SendMail sm = new SendMail(root.getContext(),to,"New Visitor",message);

        //Executing sendmail to send email
        sm.execute();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        root =  inflater.inflate(R.layout.fragment_home, container, false);

        if(!checkPermission(Manifest.permission.SEND_SMS))
        {
            ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.SEND_SMS}, SEND_SMS_PERMISSION_REQ);
        }
        vName = root.findViewById(R.id.vName);
        vEmail = root.findViewById(R.id.vEmail);
        vNumber = root.findViewById(R.id.vMobile);
        hName = root.findViewById(R.id.hName);
        hEmail = root.findViewById(R.id.hEmail);
        hAddress = root.findViewById(R.id.hAddress);
        hNumber = root.findViewById(R.id.hMobile);
        vEmailLayout = root.findViewById(R.id.vEmail_layout);
        hEmailLayout = root.findViewById(R.id.hEmail_layout);
        vMobileLayout = root.findViewById(R.id.vMobile_layout);
        hMobileLayout = root.findViewById(R.id.hMobile_layout);
        root.findViewById(R.id.submit).setOnClickListener(this);

        return root;
    }
    private boolean checkPermission(String sendSms) {

        int checkpermission= ContextCompat.checkSelfPermission(root.getContext(),sendSms);
        return checkpermission== PackageManager.PERMISSION_GRANTED;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onClick(View view) {
        vEmailLayout.setError(null);
        vMobileLayout.setError(null);
        hMobileLayout.setError(null);
        hEmailLayout.setError(null);
        if(!isValidEmail(vEmail.getText().toString().trim())){
            vEmailLayout.setError("Invalid Email");
            return;
        }
        if(!isValidEmail(hEmail.getText().toString().trim())){
            hEmailLayout.setError("Invalid Email");
            return;
        }
        if(!isValidMobile(vNumber.getText().toString().trim())){
            vMobileLayout.setError("Invalid Number");
            return;
        }
        if(!isValidMobile(hNumber.getText().toString().trim())){
            hMobileLayout.setError("Invalid Number");
            return;
        }
        final String msg;
        msg = "Details of the Visitor are mentioned below \n" + "Name: " + vName.getText().toString().trim() + "\n" + "Email: "  + vEmail.getText().toString().trim() + "\n" + "Contact Number: " + vNumber.getText().toString().trim();
        final String number = hNumber.getText().toString().trim();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss");
        DateTimeFormatter dt = DateTimeFormatter.ofPattern("dd/mm/yyyy");
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime current = LocalDateTime.now();
        final ProgressDialog progressDialog = new ProgressDialog(root.getContext());
        progressDialog.setTitle("Checking in");
        progressDialog.setMessage("Please Wait...");
        progressDialog.show();
        Map<String,Object> details = new HashMap<>();
        details.put("vName",vName.getText().toString().trim());
        details.put("vNumber",vNumber.getText().toString().trim());
        details.put("vEmail",vEmail.getText().toString().trim());
        details.put("hName",hName.getText().toString().trim());
        details.put("hAddress",hAddress.getText().toString().trim());
        details.put("hEmail",hEmail.getText().toString().trim());
        details.put("hNumber",hNumber.getText().toString());
        details.put("Check In Time",dtf.format(now));
        details.put("Check Out Time","null");
        details.put("Check In Date",dt.format(current));
        String id = db.collection("Visitor Details").document().getId();
        details.put("id",id);
        db.collection("Visitor Details").document(id).set(details).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){
                    Toast.makeText(root.getContext(),"Checked In",Toast.LENGTH_SHORT).show();
                    if(checkPermission(Manifest.permission.SEND_SMS))
                    {
                        SmsManager.getDefault().sendTextMessage(number, null, msg, null,null);
                    }
                    else{
                        ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.SEND_SMS}, SEND_SMS_PERMISSION_REQ);
                        if(checkPermission(Manifest.permission.SEND_SMS))
                        {
                            SmsManager.getDefault().sendTextMessage(number, null, msg, null,null);
                        }
                    }
                    sendEmail(hEmail.getText().toString().trim(),msg);
                }
                else{
                    Toast.makeText(root.getContext(),"Some Error Occured",Toast.LENGTH_SHORT).show();
                }
                vName.setText(null);
                vEmail.setText(null);
                vNumber.setText(null);
                hAddress.setText(null);
                hName.setText(null);
                hEmail.setText(null);
                hNumber.setText(null);
                progressDialog.dismiss();
            }
        });
    }
}