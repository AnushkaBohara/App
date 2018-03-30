package com.example.anushka.homeautomation;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.speech.RecognizerIntent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "Main activity";
    FirebaseAuth firebaseAuth;

    FirebaseDatabase firebaseDatabase;
    DatabaseReference l1Ref;
    DatabaseReference l2Ref;
    DatabaseReference f1Ref;
    DatabaseReference camRef;
    DatabaseReference camLogRef;

    ValueEventListener l1EventListener;
    ValueEventListener l2EventListener;
    ValueEventListener f1EventListener;
    ValueEventListener camEventListener;

    @BindView(R.id.coordinator)
    CoordinatorLayout coordinatorLayout;
    @BindView(R.id.modeSelectionGroup)
    RadioGroup modeSelectionGroup;
    @BindView(R.id.l1switch)
    Switch l1switch;
    @BindView(R.id.l2switch)
    Switch l2switch;
    @BindView(R.id.speakBtn)
    ImageButton speakBtn;
    @BindView(R.id.speakText)
    TextView speakText;
    @BindView(R.id.fanSeekBar)
    SeekBar fanSeekBar;
    @BindView(R.id.manualRadio)
    RadioButton manualRadio;
    @BindView(R.id.automaticRadio)
    RadioButton automaticRadio;
    @BindView(R.id.textView2)
    TextView textView2;






    private TextView voiceInput;
    private TextView speakButton;
    private TextView textView;
    private final int REQ_CODE_SPEECH_INPUT = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        firebaseDatabase = FirebaseDatabase.getInstance();
        l1Ref = firebaseDatabase.getReference("light1");
        l2Ref = firebaseDatabase.getReference("light2");
        f1Ref = firebaseDatabase.getReference("fan1");
        camRef = firebaseDatabase.getReference("cam");
        camLogRef = firebaseDatabase.getReference("camLogs");
        enableManual();
        fanSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                fan1Change(i);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });




        voiceInput = (TextView) findViewById(R.id.voiceInput);
        speakButton = (TextView) findViewById(R.id.btnSpeak);
        textView = (TextView) findViewById(R.id.textView);
        speakButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                askSpeechInput();
            }
        });

    }

    // Showing google speech input dialog

    private void askSpeechInput() {
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT,
                "Hi speak something");
        try {
            startActivityForResult(intent, REQ_CODE_SPEECH_INPUT);
        } catch (ActivityNotFoundException a) {

        }
    }

    // Receiving speech input

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case REQ_CODE_SPEECH_INPUT: {
                if (resultCode == RESULT_OK && null != data) {

                    ArrayList<String> result = data
                            .getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    voiceInput.setText(result.get(0));
                    if ((result.get(0)).indexOf("light one")!= -1) {
                        if ((result.get(0)).indexOf("on")!= -1){
                        textView.setText("Switching on light 1");
                            swtichl1On();}
                        if ((result.get(0)).indexOf("off")!= -1){
                            textView.setText("Switching off light 1");
                            swtichl1Off();}
                    }
                    if ((result.get(0)).indexOf("light to")!= -1) {
                        if ((result.get(0)).indexOf("on")!= -1){
                            textView.setText("Switching on light 2");
                            swtichl2On();}
                        if ((result.get(0)).indexOf("off")!= -1){
                            textView.setText("Switching off light 2");
                            swtichl2Off();}
                    }
                    if ((result.get(0)).indexOf("fan")!= -1){
                        if ((result.get(0)).indexOf("1")!= -1)
                            textView.setText("Setting fan speed 1");
                        if ((result.get(0)).indexOf("2")!= -1)
                            textView.setText("Setting fan speed 2");
                        if ((result.get(0)).indexOf("3")!= -1)
                            textView.setText("Setting fan speed 3");
                        if ((result.get(0)).indexOf("off")!= -1)
                            textView.setText("Switching off fan");
                    }
                    if ((result.get(0)).indexOf("manual mode")!= -1) {
                        textView.setText("Setting to manual mode");
                    }

                    }
                }
                break;
            }

        }
    private void swtichl2Off() {
        l2Ref.setValue(false);
    }

    private void swtichl2On() {
        l2Ref.setValue(true);
    }

    private void swtichl1Off() {
        l1Ref.setValue(false);
    }

    private void swtichl1On() {
        l1Ref.setValue(true);
    }

}
