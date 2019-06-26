package com.nitp.manish.hmsi;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class AccountSettingActivity extends AppCompatActivity {

    FirebaseAuth mAuth;
    FirebaseUser user;

    //logout
//    public void logout(View view){
//        Toast.makeText(this, "logout", Toast.LENGTH_SHORT).show();
//
//        mAuth = FirebaseAuth.getInstance();
//
//        AlertDialog.Builder builder = new AlertDialog.Builder(this);
//
//        builder.setTitle("Confirm");
//        builder.setMessage("Are you sure?");
//
//        builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//                mAuth.signOut();
//                Toast.makeText(AccountSettingActivity.this, "Success", Toast.LENGTH_SHORT).show();
//            }
//        });
//
//        builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//                Toast.makeText(AccountSettingActivity.this, "clicked on no", Toast.LENGTH_SHORT).show();
//            }
//        });
//
//        builder.create().show();
//
//    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_setting);


        //enabling back button
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }




    //actions on pressing back button
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(item.getItemId()==android.R.id.home){
            this.finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
