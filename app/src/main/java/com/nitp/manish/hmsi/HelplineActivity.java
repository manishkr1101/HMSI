package com.nitp.manish.hmsi;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class HelplineActivity extends AppCompatActivity implements View.OnClickListener {

    ImageView imageViewCallPolice,imageViewCallAmbulance,imageViewCallAccident;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_helpline);

        //enabling back button
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //initializing all objects
        imageViewCallPolice = (ImageView)findViewById(R.id.imageViewCallPolice);
        imageViewCallAmbulance = (ImageView)findViewById(R.id.imageViewCallAmbulance);
        imageViewCallAccident = (ImageView)findViewById(R.id.imageViewCallAccident);


        imageViewCallAmbulance.setOnClickListener(this);
        imageViewCallPolice.setOnClickListener(this);
        imageViewCallAccident.setOnClickListener(this);
    }





    //on clicklistner
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.imageViewCallPolice:
                //make a call to police
                Toast.makeText(this, "Calling", Toast.LENGTH_SHORT).show();
                Intent dialPolice = new Intent(Intent.ACTION_DIAL,Uri.parse("tel:100"));
                startActivity(dialPolice);
                break;
            case R.id.imageViewCallAmbulance:
                Intent dialAmb = new Intent(Intent.ACTION_DIAL,Uri.parse("tel:102"));
                startActivity(dialAmb);
                break;
            case R.id.imageViewCallAccident:
                Intent dialAccident = new Intent(Intent.ACTION_DIAL,Uri.parse("tel:1073"));
                startActivity(dialAccident);
                break;
        }
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
