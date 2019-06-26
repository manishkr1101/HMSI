package com.nitp.manish.hmsi;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.Random;

public class PaymentStatusActivity extends AppCompatActivity {

    TextView textViewRefNo;

//    int maximum = 999999999;
//    int minimum = 100000000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_status);

        textViewRefNo = (TextView)findViewById(R.id.textViewRef);

//        Random rn = new Random();
//        int range = maximum - minimum + 1;
//        int randomNum =  rn.nextInt(range) + minimum;
        Intent intent = getIntent();
        String refId = intent.getStringExtra("refId");

        textViewRefNo.setText(refId);
    }



}
