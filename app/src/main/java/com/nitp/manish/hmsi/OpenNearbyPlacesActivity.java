package com.nitp.manish.hmsi;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

public class OpenNearbyPlacesActivity extends AppCompatActivity {

    WebView webView;
    String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_open_nearby_places);

        webView = (WebView)findViewById(R.id.webView);

        webView.setWebViewClient(new MyBrowser());


        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().getLoadsImagesAutomatically();
        webView.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);

        Intent intent = getIntent();
        url = intent.getExtras().getString("urlNearby");
        //url = "http://www.google.com";
        Toast.makeText(OpenNearbyPlacesActivity.this, url, Toast.LENGTH_LONG).show();
        webView.loadUrl(url);
    }

    // override default webbrowser

    private class MyBrowser extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url){
            view.loadUrl(url);
            return true;
        }
    }
}
