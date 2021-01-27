package com.example.booking;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
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
    ValueCallback<Uri[]> uploadMessage;
    Context context;
    MainActivity myActivity;

    public BrowserClient() {
    }

    public BrowserClient(SwipeRefreshLayout refreshLayout, ValueCallback<Uri[]> uploadMessage) {
        this.refreshLayout = refreshLayout;
        this.uploadMessage = uploadMessage;
    }
    public boolean onShowFileChooser(WebView webView, ValueCallback<Uri[]> filePathCallback, WebChromeClient.FileChooserParams fileChooserParams) {
        // make sure there is no existing message
        if (myActivity.uploadMessage != null) {
            myActivity.uploadMessage.onReceiveValue(null);
            myActivity.uploadMessage = null;
        }

        myActivity.uploadMessage = filePathCallback;

        Intent intent = fileChooserParams.createIntent();
        try {
            myActivity.startActivityForResult(intent, MainActivity.REQUEST_SELECT_FILE);
        } catch (ActivityNotFoundException e) {
            myActivity.uploadMessage = null;
            Toast.makeText(myActivity, "Cannot open file chooser", Toast.LENGTH_LONG).show();
            return false;
        }

        return true;
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
