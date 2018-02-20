package com.example.anushka.cookit;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Quantity extends AppCompatActivity {
    public static final String EXTRA_MESSAGE = "com.example.myfirstapp.MESSAGE";
    private DatabaseReference mDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quantity);
    }
    /** Called when the user taps the Send button */
    public void sendMessage(View view) {
        // Do something in response to button
        Intent intent = new Intent(this, request.class);
        EditText editText = (EditText) findViewById(R.id.editText);
        String message = editText.getText().toString();
        int num = Integer.parseInt(message);

        if ((num > 0) || (num <= 10)){
            intent.putExtra(EXTRA_MESSAGE, message);
            Long tsLong = System.currentTimeMillis()/1000;
            String ts = tsLong.toString();
            mDatabase = FirebaseDatabase.getInstance().getReference();
            mDatabase.child("rice").child(ts).child("Quantity").setValue(num);

            startActivity(intent);
        }
        else{
            TextView text2 = (TextView) findViewById(R.id.textView2);
            text2.setText("Qunatity should be from 1 to 10");
        }


    }

    public void incVal(View view) {
        EditText editText = (EditText) findViewById(R.id.editText);
        String value = editText.getText().toString();
        int qty = Integer.parseInt(value);
        if (qty < 10) {
            qty = qty + 1;
        }
        editText.setText(""+qty+"");
    }
    public void decVal(View view) {
        EditText editText = (EditText) findViewById(R.id.editText);
        String value = editText.getText().toString();
        int qty = Integer.parseInt(value);
        if (qty > 1) {
            qty = qty - 1;
        }
        editText.setText(""+qty+"");
    }
}
