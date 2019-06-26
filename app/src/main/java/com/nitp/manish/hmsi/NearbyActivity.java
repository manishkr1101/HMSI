package com.nitp.manish.hmsi;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class NearbyActivity extends AppCompatActivity implements View.OnClickListener {

    ImageView btnNearbyToll;
    ImageView btnNearbyPetrol;
    ImageView btnNearbyMechanic;
    ImageView btnNearbyRestroom;
    ImageView btnNearbyRestaurant;

    String userCurrentLat=null,userCurrentLng=null;
    String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nearby);

        //enabling back button
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //initialising all button
        btnNearbyToll = (ImageView)findViewById(R.id.ivNearbyToll);
        btnNearbyPetrol = (ImageView)findViewById(R.id.ivNearbyPetrolPump);
        btnNearbyMechanic = (ImageView)findViewById(R.id.ivNearbyMechanic);
        btnNearbyRestroom = (ImageView)findViewById(R.id.ivNearbyRestroom);
        btnNearbyRestaurant = (ImageView)findViewById(R.id.ivNearbyRestaurants);

        //taking currentlat and currentlong
        Intent intent = getIntent();
        userCurrentLat = intent.getExtras().getString("Latitude");
        userCurrentLng = intent.getExtras().getString("Longitude");

        if (userCurrentLng==null || userCurrentLat==null){
            Toast.makeText(NearbyActivity.this, "wait for location", Toast.LENGTH_SHORT).show();
        }






        btnNearbyToll.setOnClickListener(this);
        btnNearbyPetrol.setOnClickListener(this);
        btnNearbyMechanic.setOnClickListener(this);
        btnNearbyRestroom.setOnClickListener(this);
        btnNearbyRestaurant.setOnClickListener(this);


    }


    //actions on clicking back button in title bar
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(item.getItemId()==android.R.id.home){
            this.finish();
        }
        return super.onOptionsItemSelected(item);
    }

    //action on button click
    @Override
    public void onClick(View view) {
        if (userCurrentLng != null && userCurrentLat != null) {

            switch (view.getId()) {
                case R.id.ivNearbyToll:
                    url = "https://www.google.com/maps/search/toll+plaza/@"+userCurrentLat+","+userCurrentLng+",15z";
                    Intent Nearbytoll =new Intent(NearbyActivity.this,OpenNearbyPlacesActivity.class);
                    Nearbytoll.putExtra("urlNearby",url);
                    //Toast.makeText(NearbyActivity.this, userCurrentLat + " " + userCurrentLng, Toast.LENGTH_SHORT).show();
                    startActivity(Nearbytoll);
                    break;
                case R.id.ivNearbyPetrolPump:
                    //Toast.makeText(NearbyActivity.this, " getting", Toast.LENGTH_SHORT).show();
                    url = "https://www.google.com/maps/search/Petrol+pump/@"+userCurrentLat+","+userCurrentLng+",15z";
                    Intent NearbyPump=new Intent(NearbyActivity.this,OpenNearbyPlacesActivity.class);
                    NearbyPump.putExtra("urlNearby",url);
                    startActivity(NearbyPump);
                    break;
                case R.id.ivNearbyMechanic:
                    url = "https://www.google.com/maps/search/mechanic/@"+userCurrentLat+","+userCurrentLng+",15z";
                    Intent NearbyMechanic=new Intent(NearbyActivity.this,OpenNearbyPlacesActivity.class);
                    NearbyMechanic.putExtra("urlNearby",url);
                    startActivity(NearbyMechanic);
                    break;
                case R.id.ivNearbyRestroom:
                    url = "https://www.google.com/maps/search/Rest+house/@"+userCurrentLat+","+userCurrentLng+",15z";
                    Intent NearbyRestroom=new Intent(NearbyActivity.this,OpenNearbyPlacesActivity.class);
                    NearbyRestroom.putExtra("urlNearby",url);
                    startActivity(NearbyRestroom);
                    break;
                case R.id.ivNearbyRestaurants:
                    url = "https://www.google.com/maps/search/restaurants/@"+userCurrentLat+","+userCurrentLng+",15z";
                    Intent NearbyRestaurants=new Intent(NearbyActivity.this,OpenNearbyPlacesActivity.class);
                    NearbyRestaurants.putExtra("urlNearby",url);
                    startActivity(NearbyRestaurants);
                    break;
            }

        }
    }
}
