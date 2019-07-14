package com.mcdenny.alc4phase1;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.net.http.SslError;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.SslErrorHandler;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

public class Activity_B extends AppCompatActivity {
    private WebView webView;
    private String url = "https://andela.com/alc/";
    private ProgressBar progressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__b);
        setTitle("About ALC");

        getSupportActionBar().setElevation(0);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        webView = (WebView) findViewById(R.id.webview);
        progressBar = (ProgressBar) findViewById(R.id.prgress_bar);

        setWebView();


    }

    @SuppressLint("SetJavaScriptEnabled")
    private void setWebView(){
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
                handler.proceed();
            }
        });
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setAllowContentAccess(true);
        webSettings.setLoadsImagesAutomatically(true);
        webView.setWebChromeClient(new WebChromeClient());

        loadAlcUrl(url);
    }

    private void loadAlcUrl(String url){

        try {
            webView.loadUrl(url);
            progressBar.setVisibility(View.INVISIBLE);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            webView.getCertificate();
            webView.reload();
        }

    }
}
