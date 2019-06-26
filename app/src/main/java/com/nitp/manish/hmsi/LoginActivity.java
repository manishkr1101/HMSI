package com.nitp.manish.hmsi;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
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

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{

    private FirebaseAuth mAuth;
    private EditText editTextEmail,editTextPassword;
    private Button btnLogin;
    private TextView textViewSignup,textViewForgotPassword;
    private ProgressDialog progressDialog;
    FirebaseUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //initializing progressdialog
        progressDialog = new ProgressDialog(this);

        mAuth = FirebaseAuth.getInstance();

        user = mAuth.getCurrentUser();



        //checking whether user is already logged in or not
        //if yes redirect to homepage
        if(mAuth.getCurrentUser()!=null){
            Toast.makeText(this, "Already Logged in as "+user.getEmail(), Toast.LENGTH_SHORT).show();
            finish();
           // startActivity(new Intent(this, MainActivity.class));
        }

        //if not proceed for login
        editTextEmail=(EditText)findViewById(R.id.editEmail);
        editTextPassword=(EditText)findViewById(R.id.editPassword);
        btnLogin=(Button)findViewById(R.id.btnLogin);
        textViewSignup=(TextView)findViewById(R.id.textViewSignup);
        textViewForgotPassword=(TextView)findViewById(R.id.textViewForgotPassword);


        btnLogin.setOnClickListener(this);
        textViewSignup.setOnClickListener(this);
        textViewForgotPassword.setOnClickListener(this);
    }

    private void userLogin(){
        String email = editTextEmail.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();

        //CHECK IF MAIL OR PASSWORD IS LEFT EMPTY
        if(TextUtils.isEmpty(email)){
            //Toast.makeText(this, "enter your email", Toast.LENGTH_SHORT).show();
            editTextEmail.setError("email required");
            return;
        }
        if(TextUtils.isEmpty(password)){
            editTextPassword.setError("password required");
            return;
        }

        progressDialog.setMessage("Logging in...");
        progressDialog.show();
        //write here codes to login
        mAuth.signInWithEmailAndPassword(email,password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if(task.isSuccessful()){
                            progressDialog.dismiss();
                            //check whether user has verified his email
                            user = mAuth.getCurrentUser();
                            if(!user.isEmailVerified()){

                                Toast.makeText(LoginActivity.this, "Please verify your email", Toast.LENGTH_SHORT).show();
                                //send verification mail
                                sendEmailVerification();
                                mAuth.signOut();
                            }
                            else {
                                Toast.makeText(LoginActivity.this, "Logged in", Toast.LENGTH_SHORT).show();

                                //hide login and signup option

                                finish();
                            }
                        }
                        else{
                            Toast.makeText(LoginActivity.this, "Try again", Toast.LENGTH_SHORT).show();
                            progressDialog.dismiss();
                        }
                    }
                });

    }





    private void sendEmailVerification() {
        // Disable button


        // Send verification email
        // [START send_email_verification]
        user = mAuth.getCurrentUser();
        user.sendEmailVerification()
                .addOnCompleteListener(this, new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        // [START_EXCLUDE]
                        // Re-enable button


                        if (task.isSuccessful()) {
                            Toast.makeText(LoginActivity.this,
                                    "Verification email sent to " + user.getEmail(),
                                    Toast.LENGTH_SHORT).show();
                        } else {

                            Toast.makeText(LoginActivity.this,
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
        if(view == btnLogin){
            //goto Login
            userLogin();
        }
        if(view==textViewSignup){
            //redirect to SignupActivity
            finish();
            startActivity(new Intent(this, SignupActivity.class));


        }
        if(view==textViewForgotPassword){
            //redirect to homepage

            startActivity(new Intent(this, ForgotPasswordActivity.class));
        }

    }
}
