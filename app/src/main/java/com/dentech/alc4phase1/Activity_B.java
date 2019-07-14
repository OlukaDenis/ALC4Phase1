package com.dentech.alc4phase1;

        import android.annotation.SuppressLint;
        import android.app.Activity;
        import android.graphics.Bitmap;
        import android.net.http.SslError;
        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.view.View;
        import android.webkit.*;
        import android.widget.ProgressBar;
        import android.widget.TextView;
        import android.widget.Toast;

public class Activity_B extends AppCompatActivity {
    private WebView webView;
    private String url = "https://andela.com/alc";
    private ProgressBar progressBar;
    private TextView tvLoad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__b);
        setTitle("About ALC");

        getSupportActionBar().setElevation(0);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        webView = (WebView) findViewById(R.id.webview);
        progressBar = (ProgressBar) findViewById(R.id.progressbar);
        tvLoad = (TextView) findViewById(R.id.tv_loading);

        setWebView();
    }

    @SuppressLint("SetJavaScriptEnabled")
    private void setWebView(){
        setProgressBarVisibility(View.VISIBLE);
        tvLoad.setVisibility(View.VISIBLE);
        webView.setWebViewClient(new WebViewClient() {

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                setProgressBarVisibility(View.VISIBLE);
                tvLoad.setVisibility(View.VISIBLE);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                setProgressBarVisibility(View.GONE);
                tvLoad.setVisibility(View.GONE);
            }

            @Override
            public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
                super.onReceivedError(view, request, error);
                setProgressBarVisibility(View.GONE);
                tvLoad.setVisibility(View.GONE);
            }

            @Override
            public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
                handler.proceed();
            }
        });
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
       //webSettings.setAllowContentAccess(true);
       // webSettings.setLoadsImagesAutomatically(true);
        webView.setWebChromeClient(new WebChromeClient());

        webView.loadUrl(url);
    }

    private void loadAlcUrl(String url){

        try {
            webView.loadUrl(url);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            webView.getCertificate();
            webView.reload();
        }

    }


    private void setProgressBarVisibility(int visibility) {
        // If a user returns back, a NPE may occur if WebView is still loading a page and then tries to hide a ProgressBar.
        if (progressBar != null) {
            progressBar.setVisibility(visibility);
        }
    }
}
