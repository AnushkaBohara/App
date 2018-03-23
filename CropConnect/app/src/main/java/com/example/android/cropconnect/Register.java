package com.example.android.cropconnect;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class Register extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
    }

    public void regOnClickzz(View view)
    {
        Intent i=new Intent(this,Dashboard.class);
        startActivity(i);
    }
}
