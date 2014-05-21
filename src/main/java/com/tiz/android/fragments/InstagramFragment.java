package com.tiz.android.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import com.tiz.android.R;
import com.tiz.android.utils.Constants;

/**
 * Created by david.hodge on 12/22/13.
 */
public class InstagramFragment extends Fragment {

    private WebView webview;
    private ProgressBar Pbar;

    View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        view = inflater.inflate(R.layout.web_stub, container, false);


        webview = (WebView) view.findViewById(R.id.webView1);
        webview.getSettings().setJavaScriptEnabled(true);
        //webview.getSettings().setLayoutAlgorithm(LayoutAlgorithm.SINGLE_COLUMN);
        //webview.getSettings().setLoadWithOverviewMode(true);
        webview.getSettings().setBuiltInZoomControls(true);
        webview.getSettings().setDisplayZoomControls(false);
        //webview.getSettings().setUseWideViewPort(true);
        webview.getSettings().setSupportZoom(true);
        //webview.getSettings().setDefaultZoom(ZoomDensity.FAR);
        webview.loadUrl(Constants.INSTAGRAM_URL);
        webview.setWebViewClient(new WebViewClient());
        webview.setBackgroundColor(0x00000000);
        Pbar = (ProgressBar) view.findViewById(R.id.pb1);
        setHasOptionsMenu(true);


        webview.setWebChromeClient(new WebChromeClient() {
            public void onProgressChanged(WebView view, int progress) {
                if (progress < 100 && Pbar.getVisibility() == ProgressBar.GONE) {
                    Pbar.setVisibility(ProgressBar.VISIBLE);
                }
                Pbar.setProgress(progress);
                if (progress == 100) {
                    Pbar.setVisibility(ProgressBar.GONE);
                }

            }
        });

        return view;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.webmenu, menu);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();
        if (itemId == R.id.web_back) {
            webview.goBack();
        } else if (itemId == R.id.web_forward) {
            webview.goForward();
        } else if (itemId == R.id.web_refresh) {
            webview.reload();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onStart() {
        super.onStart();
        //TODO ANALYTICS
    }

    @Override
    public void onStop() {
        super.onStop();
        //TODO ANALYTICS
    }
}
