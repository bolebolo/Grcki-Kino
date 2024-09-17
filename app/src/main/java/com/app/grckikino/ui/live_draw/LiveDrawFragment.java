package com.app.grckikino.ui.live_draw;

import static com.app.grckikino.utils.KeysAndConstants.LIVE_DRAW_URL;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.app.grckikino.R;
import com.app.grckikino.databinding.FragmentLiveDrowBinding;


public class LiveDrawFragment extends Fragment {

    private FragmentLiveDrowBinding binding;
    private boolean isUrlLoaded = false;


    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentLiveDrowBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
//        setupWebView();
    }



    private void setupWebView() {
        WebView webView =  binding.drawWebView;
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setDomStorageEnabled(true);
        webView.getSettings().setAllowFileAccess(false);
        webView.getSettings().setAllowContentAccess(false);
        webView.getSettings().setLoadWithOverviewMode(true);
        webView.getSettings().setUseWideViewPort(true);
        webView.getSettings().setJavaScriptCanOpenWindowsAutomatically(false);
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
                Log.e("WebView", "Error: " + error.toString());
                Toast.makeText(getContext(), getString(R.string.error_loading_page), Toast.LENGTH_SHORT).show();
            }
        });
        if(!isUrlLoaded){
            webView.loadUrl(LIVE_DRAW_URL);
            isUrlLoaded=true;
        }
    }

}