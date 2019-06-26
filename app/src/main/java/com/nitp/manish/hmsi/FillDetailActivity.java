package com.nitp.manish.hmsi;

import android.content.Intent;
import android.os.PatternMatcher;
import android.service.autofill.RegexValidator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FillDetailActivity extends AppCompatActivity {

    EditText  amount, number;
    Spinner type;
    Button buttongo;
    ArrayAdapter<CharSequence> adapter;
    String tollName,vehicleNumber,vehicleType,paidAmount;

    Spinner spinnerToll;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fill_detail);

        //tollname = (EditText) findViewById(R.id.tollname);
        spinnerToll = (Spinner)findViewById(R.id.spinnerToll);
        amount = (EditText) findViewById(R.id.amounttopay);
        buttongo = (Button) findViewById(R.id.btngo);
        number = (EditText) findViewById(R.id.vehiclenumber);
        type = (Spinner) findViewById(R.id.vehicletype);

        adapter = ArrayAdapter.createFromResource(this, R.array.vehicle_type, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        type.setAdapter(adapter);

        type.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                Toast.makeText(getBaseContext(), parent.getItemAtPosition(position) + " selected", Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        buttongo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                if (tollname.getText().toString().trim().isEmpty()){
//                    tollname.setError("Can't be empty");
//                    tollname.requestFocus();
//                    return;
//                }

                if (amount.getText().toString().trim().isEmpty()){
                    amount.setError("Can't be empty");
                    amount.requestFocus();
                    return;
                }

                if (number.getText().toString().trim().isEmpty()){
                    number.setError("Can't be empty");
                    number.requestFocus();
                    return;
                }

                Pattern pattern = Pattern.compile("^[A-Z]{2}[0-9]{2}[A-Z]{2}[0-9]{4}$");
                Matcher matcher = pattern.matcher(number.getText().toString());
                boolean match_found = (matcher.find() && matcher.group().equals(number.getText().toString()));

                if (!match_found || number.length() != 10){
                    number.setError("Enter a valid vehicle number");
                    number.requestFocus();
                    Toast.makeText(getApplicationContext(), "Invalid vehicle number", Toast.LENGTH_SHORT).show();
                    return;
                }


                if (!amount.getText().toString().trim().isEmpty() &&
                        !number.getText().toString().trim().isEmpty()) {

                    //String amountobepaid = amount.getText().toString();
//                    tollName = tollname.getText().toString().trim();
                    tollName = spinnerToll.getSelectedItem().toString();
                    vehicleNumber = number.getText().toString().trim();
                    vehicleType = type.getSelectedItem().toString().trim();
                    paidAmount = amount.getText().toString().trim();


                    Intent intent = new Intent(getApplicationContext(), PaymentActivity.class);
                    intent.putExtra("tollName",tollName);
                    intent.putExtra("vehicleNumber",vehicleNumber);
                    intent.putExtra("vehicleType",vehicleType);
                    intent.putExtra("paidAmount",paidAmount);
                    intent.putExtra("FINALAMOUNT", amount.getText().toString());
                    startActivity(intent);


                    Toast.makeText(getApplicationContext(), "Success", Toast.LENGTH_SHORT).show();
//                    finish();

                }

            }
        });


    }
}
