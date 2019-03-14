package com.example.newsreader;

import android.os.Bundle;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.newsreader.Base.BaseActivity;

public class ArticleDetails extends BaseActivity {

    protected WebView articleWebView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_article_details);
        initView();
        articleWebView.getSettings().setJavaScriptEnabled(true);
        articleWebView.setWebChromeClient(new WebChromeClient());
        articleWebView.setWebViewClient(new WebViewClient());
        articleWebView.loadUrl(getIntent().getStringExtra("url"));

    }

    private void initView() {
        articleWebView =  findViewById(R.id.article_webView);
    }
}
