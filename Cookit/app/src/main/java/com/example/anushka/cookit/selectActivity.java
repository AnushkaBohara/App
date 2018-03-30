package com.example.anushka.cookit;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class selectActivity extends AppCompatActivity {
    Button riceButton, dalButton, bothButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select);

        riceButton = (Button) findViewById(R.id.rice_button);

        // Capture button clicks
        riceButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {

                // Start NewActivity.class
                Intent myIntent = new Intent(selectActivity.this,
                        Quantity.class);
                startActivity(myIntent);
            }
        });
        dalButton = (Button) findViewById(R.id.dal_button);

        // Capture button clicks
        dalButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {

                // Start NewActivity.class
                Intent myIntent = new Intent(selectActivity.this,
                        QuantityDal.class);
                startActivity(myIntent);
            }
        });
        bothButton = (Button) findViewById(R.id.both_button);

        // Capture button clicks
        bothButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {

                // Start NewActivity.class
                Intent myIntent = new Intent(selectActivity.this,
                        QuantityBoth.class);
                startActivity(myIntent);
            }
        });
    }
}
