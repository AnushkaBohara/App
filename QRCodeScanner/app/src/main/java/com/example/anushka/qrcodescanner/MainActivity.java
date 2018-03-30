package com.example.anushka.qrcodescanner;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.StringTokenizer;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    //View Objects
    private Button buttonScan;
    private TextView textViewName, textViewAddress;
    private DatabaseReference mDatabase;
    private String medicines;

    //qr code scanner object
    private IntentIntegrator qrScan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        //View objects
        buttonScan = (Button) findViewById(R.id.buttonScan);
        textViewName = (TextView) findViewById(R.id.textViewName);
        textViewAddress = (TextView) findViewById(R.id.textViewAddress);

        //intializing scan object
        qrScan = new IntentIntegrator(this);

        //attaching onclick listener
        buttonScan.setOnClickListener(this);
    }

    //Getting the scan results
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (result != null) {
            //if qrcode has nothing in it
            if (result.getContents() == null) {
                Toast.makeText(this, "Result Not Found", Toast.LENGTH_LONG).show();
            } else {
                //if qr contains data
                try {
                    //converting the data to json
                    mDatabase = FirebaseDatabase.getInstance().getReference();
                    JSONObject obj = new JSONObject(result.getContents());
                    //setting values to textviews

                    mDatabase.child("devices").child("dev1").child("pending_request").setValue(true);
                    mDatabase.child("devices").child("dev1").child("cur_pres").child("patID").setValue(obj.getString("pat"));
                    mDatabase.child("devices").child("dev1").child("cur_pres").child("presID").setValue(obj.getString("pres"));
                    medicines = obj.getString("med");
                    StringTokenizer st = new StringTokenizer(medicines, ":,");
                    while (st.hasMoreTokens()) {
                        mDatabase.child("devices").child("dev1").child("cur_pres").child("Medicines").child(st.nextToken()).child("qty").setValue(st.nextToken());
                    }
                    textViewName.setText(obj.getString("pat"));
                    textViewAddress.setText(obj.getString("pres"));
                } catch (JSONException e) {
                    e.printStackTrace();
                    //if control comes here
                    //that means the encoded format not matches
                    //in this case you can display whatever data is available on the qrcode
                    //to a toast
                    Toast.makeText(this, result.getContents(), Toast.LENGTH_LONG).show();
                }
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }
    @Override
    public void onClick(View view) {
        //initiating the qr code scan
        qrScan.initiateScan();
    }
}
