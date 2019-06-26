package com.nitp.manish.hmsi;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    //HOME ACTIVITY

    FirebaseAuth mAuth;
    FirebaseUser user;
    String userCurrentLat=null,userCurrentLng=null;

    ImageButton imgBtnPayToll,imgBtnHospital,imgBtnNearby,imgBtnReport,imgBtnHelpline,imgBtnFeedback;

    LocationManager locationManager;
    LocationListener locationListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //initializing mAuth
        mAuth = FirebaseAuth.getInstance();
        user = mAuth.getCurrentUser();

        //initializing all image buttons
        imgBtnPayToll = (ImageButton)findViewById(R.id.imgBtnPayToll);
        imgBtnHospital = (ImageButton)findViewById(R.id.imgBtnHospital);
        imgBtnNearby = (ImageButton)findViewById(R.id.imgBtnNearby);
        imgBtnReport = (ImageButton)findViewById(R.id.imgBtnReport);
        imgBtnHelpline = (ImageButton)findViewById(R.id.imgBtnHelpline);
        imgBtnFeedback = (ImageButton)findViewById(R.id.imgBtnFeedback);


        getLatLng();

        //Setting clickonlistener to all button
        imgBtnPayToll.setOnClickListener(this);
        imgBtnHospital.setOnClickListener(this);
        imgBtnNearby.setOnClickListener(this);
        imgBtnReport.setOnClickListener(this);
        imgBtnHelpline.setOnClickListener(this);
        imgBtnFeedback.setOnClickListener(this);

    }

    //to get usercurrent location
    public void getLatLng(){

        locationManager = (LocationManager)this.getSystemService(Context.LOCATION_SERVICE);



        locationListener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {

                userCurrentLat = Double.toString(location.getLatitude());
                userCurrentLng = Double.toString(location.getLongitude());
            }

            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {

            }

            @Override
            public void onProviderEnabled(String provider) {

            }

            @Override
            public void onProviderDisabled(String provider) {

            }
        };


        if(ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
        }
        else{
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationListener);
        }



    }



    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if(requestCode == 1){
            if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                if(ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED){
                    locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,0,0,locationListener);
                }
            }
        }
    }






    //CHECKING WHETHER USER IS LOGGED IN OR NOT
    private boolean isLoggedIn(FirebaseUser user){
        if(user == null){
            return false;
        }
        else{
            return true;
        }
    }


    //MAKING MENU OPTION

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_activity,menu);
        return true;
    }

    //HANDLING OPTIONS DISPLAYING IN MENU ITEM
    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        user=mAuth.getCurrentUser();
        if(isLoggedIn(user)){
            MenuItem login= menu.findItem(R.id.action_login);
            login.setVisible(false);
            MenuItem signup= menu.findItem(R.id.action_signup);
            signup.setVisible(false);
            MenuItem setting= menu.findItem(R.id.action_acc_setting);
            setting.setVisible(true);
            MenuItem logout= menu.findItem(R.id.action_logout);
            logout.setVisible(true);

        }
        else{
            MenuItem login= menu.findItem(R.id.action_login);
            login.setVisible(true);
            MenuItem signup= menu.findItem(R.id.action_signup);
            signup.setVisible(true);
            MenuItem setting= menu.findItem(R.id.action_acc_setting);
            setting.setVisible(false);
            MenuItem logout= menu.findItem(R.id.action_logout);
            logout.setVisible(false);
        }
        return true;
    }


    //actions performed on choosing menu option
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==R.id.action_login){
            //perform login
            startActivity(new Intent(this, LoginActivity.class));
        }
        if(item.getItemId()==R.id.action_signup){
            //perform signup
            startActivity(new Intent(this, SignupActivity.class));
        }
        if(item.getItemId()==R.id.action_acc_setting){
            //open new activity having persons account detail
            startActivity(new Intent(this, AccountSettingActivity.class));
        }
        if(item.getItemId()==R.id.action_logout){
            //perform logout
            mAuth.signOut();

        }
        if(item.getItemId()==R.id.action_about){
            //launch about activity
            startActivity(new Intent(this, AboutActivity.class));
        }

        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onClick(View view) {

        switch (view.getId()){
            case R.id.imgBtnPayToll:

                user = mAuth.getCurrentUser();
                //check if user is signed in
                if (user != null) {
                    //open payment activity
                    startActivity(new Intent(this, FillDetailActivity.class));
                }else{
                    startActivity(new Intent(this, LoginActivity.class));
                }
//                //redirect to fastag
//                Intent openFastag = getPackageManager().getLaunchIntentForPackage("com.fastaguser");
//                if(openFastag!=null) {
//                    startActivity(openFastag);
//                }
//                else{
//                    Intent i = new Intent(android.content.Intent.ACTION_VIEW);
//                    i.setData(Uri.parse("https://play.google.com/store/apps/details?id=com.fastaguser"));
//                    startActivity(i);
//                }
                break;
            case R.id.imgBtnHospital:

                if (userCurrentLat == null || userCurrentLng ==null){
                    Toast.makeText(MainActivity.this, "Wait for location", Toast.LENGTH_SHORT).show();
                }else{

                    String url = "https://www.google.com/maps/search/hospital/@"+ userCurrentLat+","+userCurrentLng+",15z/data=!3m1!4b1";
                    Intent mapView = new Intent(this, OpenNearbyPlacesActivity.class);
                    mapView.putExtra("urlNearby",url);
                    startActivity(mapView);
                }



                break;
            case R.id.imgBtnNearby:
                Intent Nearby = new Intent(this, NearbyActivity.class);
                Nearby.putExtra("Latitude", userCurrentLat);
                Nearby.putExtra("Longitude", userCurrentLng);
                startActivity(Nearby);
                break;
            case R.id.imgBtnReport:

                startActivity(new Intent(getApplicationContext(), ReportActivity.class
                ));

                break;
            case R.id.imgBtnHelpline:
                //Goto Helpline activity
                startActivity(new Intent(this, HelplineActivity.class));
                break;
            case R.id.imgBtnFeedback:

                break;
        }

    }
}


