package com.example.booking;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import java.net.HttpCookie;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.List;

public class BrowserClient extends WebViewClient {

    SwipeRefreshLayout refreshLayout;
    WebView webView;
    Context context;

    public BrowserClient() {
    }

    public BrowserClient(SwipeRefreshLayout refreshLayout) {
        this.refreshLayout = refreshLayout;
    }


    @Override
    public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
        super.onReceivedError(view, request, error);
    }

    @Override
    public void onPageStarted(WebView view, String url, Bitmap favicon) {
        super.onPageStarted(view, url, favicon);
        //refreshLayout.setRefreshing(true);

    }

    @Override
    public void onPageFinished(WebView view, String url) {
        super.onPageFinished(view, url);
        refreshLayout.setRefreshing(false);
        CookieManager.getInstance().acceptCookie();


    }
}
