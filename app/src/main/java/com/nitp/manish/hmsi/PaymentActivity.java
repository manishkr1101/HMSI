package com.nitp.manish.hmsi;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.craftman.cardform.Card;
import com.craftman.cardform.CardForm;
import com.craftman.cardform.OnPayBtnClickListner;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class PaymentActivity extends AppCompatActivity {

    String amount;
    String refId,tollName,vehicleNumber,vehicleType,paidAmount,paymentStatus;
    long currentTime;

    FirebaseAuth mAuth;
    FirebaseUser user;

    DatabaseReference databasePayment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

        CardForm cardForm = (CardForm) findViewById(R.id.cardform);
        TextView displaytext = (TextView) findViewById(R.id.payment_amount);
        Button btnpay = (Button) findViewById(R.id.btn_pay);

        mAuth = FirebaseAuth.getInstance();
        currentTime = System.currentTimeMillis();


        FirebaseApp.initializeApp(this);

        user = mAuth.getCurrentUser();
        String email = user.getEmail();
        String uid = user.getUid();
        Toast.makeText(this, email, Toast.LENGTH_SHORT).show();
        databasePayment = FirebaseDatabase.getInstance().getReference(uid);

        Intent i = getIntent();
        amount = i.getStringExtra("FINALAMOUNT");
        tollName = i.getStringExtra("tollName");
        vehicleNumber = i.getStringExtra("vehicleNumber");
        vehicleType = i.getStringExtra("vehicleType");
        paidAmount = i.getStringExtra("paidAmount");
        //Toast.makeText(this, amount., Toast.LENGTH_SHORT).show();
        Toast.makeText(this, tollName, Toast.LENGTH_SHORT).show();
        Toast.makeText(this, vehicleNumber, Toast.LENGTH_SHORT).show();
        Toast.makeText(this, vehicleType, Toast.LENGTH_SHORT).show();
        Toast.makeText(this, paidAmount, Toast.LENGTH_SHORT).show();

        displaytext.setText("INR " + amount);
        displaytext.setEnabled(false);
        btnpay.setText(String.format("Pay %s", displaytext.getText()));

        cardForm.setPayBtnClickListner(new OnPayBtnClickListner() {
            @Override
            public void onClick(Card card) {
                Toast.makeText(getApplicationContext(), "Name : " + card.getName() + " | Last 4 digits : " + card.getLast4(), Toast.LENGTH_SHORT).show();


                Toast.makeText(PaymentActivity.this, "Success", Toast.LENGTH_SHORT).show();

                //if payment success update to firebase
                paymentStatus="true";
                refId = databasePayment.push().getKey();
                updatePaymentDetail();

                //startActivity(new Intent(getApplicationContext(), PaymentStatusActivity.class));

                Intent intent = new Intent(getApplicationContext(),PaymentStatusActivity.class);
                intent.putExtra("refId",refId);
                startActivity(intent);
                finish();
            }
        });
    }

    private void updatePaymentDetail(){



        PaymentDetails details = new PaymentDetails(refId,tollName,vehicleNumber,vehicleType,paidAmount,paymentStatus,currentTime);
        databasePayment.child(refId).setValue(details);

    }
}
