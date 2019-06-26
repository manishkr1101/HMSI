package com.nitp.manish.hmsi;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class ReportActivity extends AppCompatActivity {

    Spinner spinnerSubject;
    EditText editTextComment;
    Button btnSubmit;
    String subject,comment;

    FirebaseAuth mAuth;
    //FirebaseUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        spinnerSubject = (Spinner)findViewById(R.id.spinnerSubject);
        editTextComment = (EditText)findViewById(R.id.editTextComment);
        btnSubmit = (Button)findViewById(R.id.buttonSubmit);

        mAuth = FirebaseAuth.getInstance();

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                subject = spinnerSubject.getSelectedItem().toString();
                comment = editTextComment.getText().toString();

                if(!subject.isEmpty() && !comment.isEmpty()){
                    if(mAuth.getCurrentUser() == null){
                        Toast.makeText(ReportActivity.this, "Please login first", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(getApplicationContext(),LoginActivity.class));
                    }
                    else{
                        Toast.makeText(ReportActivity.this, "Submitted succesfully", Toast.LENGTH_SHORT).show();
                        finish();
                    }


                }
                else{

                    editTextComment.setError("Can't be empty");
                    editTextComment.requestFocus();
                }
            }
        });
    }

    //actions on clicking button
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(item.getItemId()==android.R.id.home){
            this.finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
