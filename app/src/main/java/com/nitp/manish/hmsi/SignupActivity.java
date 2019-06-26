package com.nitp.manish.hmsi;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SignupActivity extends AppCompatActivity implements View.OnClickListener{


    private EditText etEmail,etPassword,etCnfrmPassword;
    private Button btnSignup;
    private TextView tvLogin;
    FirebaseAuth mAuth;
    ProgressDialog progressDialog;
    FirebaseUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        etEmail = (EditText)findViewById(R.id.editEmail);
        etPassword = (EditText)findViewById(R.id.editPassword);
        etCnfrmPassword = (EditText)findViewById(R.id.editRePassword);
        tvLogin = (TextView)findViewById(R.id.textViewSignin);
        btnSignup = (Button)findViewById(R.id.btnSignup);
        progressDialog = new ProgressDialog(this);

        mAuth = FirebaseAuth.getInstance();

        //initializing user
        user = mAuth.getCurrentUser();

        if(mAuth.getCurrentUser() != null){
            Toast.makeText(this, "First logout from this account", Toast.LENGTH_SHORT).show();
            finish();
        }

        btnSignup.setOnClickListener(this);
        tvLogin.setOnClickListener(this);
    }

    private void SignUp() {

        String email = etEmail.getText().toString().trim();
        String password = etPassword.getText().toString().trim();
        String re_password = etCnfrmPassword.getText().toString().trim();

        //CHECK IF MAIL OR PASSWORD IS LEFT EMPTY
        if(TextUtils.isEmpty(email)){
            Toast.makeText(this, "enter your email", Toast.LENGTH_SHORT).show();
            return;
        }
        if(TextUtils.isEmpty(password) || TextUtils.isEmpty(re_password)){
            Toast.makeText(this, "enter your password", Toast.LENGTH_SHORT).show();
            return;
        }
        if(!password.equals(re_password)){
            Toast.makeText(this, "password not matched", Toast.LENGTH_SHORT).show();
            return;
        }

        progressDialog.setMessage("Signing Up...");
        progressDialog.show();

        mAuth.createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){

                            progressDialog.dismiss();
                            sendEmailVerification();
                            //signing out the user from the divice
                            mAuth.signOut();
                            //finishing the signup activity
                            finish();
                        }
                        else
                        {
                            progressDialog.dismiss();
                            Toast.makeText(SignupActivity.this, "please try again", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }


    //SENDING MAIL VERIFICATION LINK
    private void sendEmailVerification() {
        // Disable button
        btnSignup.setEnabled(false);

        // Send verification email
        // [START send_email_verification]
        user = mAuth.getCurrentUser();
        user.sendEmailVerification()
                .addOnCompleteListener(this, new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        // [START_EXCLUDE]
                        // Re-enable button
                        btnSignup.setEnabled(true);

                        if (task.isSuccessful()) {
                            Toast.makeText(SignupActivity.this,
                                    "Verification email sent to " + user.getEmail(),
                                    Toast.LENGTH_SHORT).show();
                        } else {

                            Toast.makeText(SignupActivity.this,
                                    "Failed to send verification email.",
                                    Toast.LENGTH_SHORT).show();
                        }
                        // [END_EXCLUDE]
                    }
                });
        // [END send_email_verification]
    }



    @Override
    public void onClick(View view) {
        if(view == btnSignup){
            SignUp();
        }
        if(view==tvLogin){
            finish();
            startActivity(new Intent(this, LoginActivity.class));
        }
    }



}
