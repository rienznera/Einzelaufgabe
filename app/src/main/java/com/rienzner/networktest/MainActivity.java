package com.rienzner.networktest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btnSendReq = findViewById(R.id.btnSendRequest);
        btnSendReq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String message;
                toggleButtons(false);
                EditText matnrTextField = findViewById(R.id.inputMatnr);
                TCPThread t = new TCPThread(matnrTextField.getText().toString());
                t.start();
                try{
                    t.join();
                    message = t.getMessage();
                }
                catch(InterruptedException ie){
                       message = ie.getMessage();
                    Thread.currentThread().interrupt();
                }
                TextView responseView = findViewById(R.id.responseText);
                responseView.setText(message);

                toggleButtons(true);
            }
        });

        Button btnEditNumber = findViewById(R.id.btnNumberEdit);
        btnEditNumber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String number = "";
                toggleButtons(false);
                EditText matnrTextField = findViewById(R.id.inputMatnr);
                NumberFormatThread t = new NumberFormatThread(matnrTextField.getText().toString());
                t.start();
                try{
                    t.join();
                    number = t.getMatnr();
                }
                catch(InterruptedException ie){
                    Log.e("LOG", ie.toString());
                    Thread.currentThread().interrupt();
                }
                EditText inputMatnr = findViewById(R.id.inputMatnr);
                inputMatnr.setText(number);

                toggleButtons(true);
            }
        });

    }

    private void toggleButtons(Boolean value){
        Button btnSendReq = findViewById(R.id.btnSendRequest);
        btnSendReq.setActivated(value);
    }


}
