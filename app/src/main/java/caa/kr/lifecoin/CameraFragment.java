package caa.kr.lifecoin;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class CameraFragment extends Fragment {

    private static CameraFragment CF;
    public static CameraFragment getInstance() {
        if (CF == null) CF = new CameraFragment();

        return CF;
    }

    private WebView mWebView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.layout_camera, container, false);

        mWebView = (WebView) v.findViewById(R.id.web_qr_reader);
        mWebView.setWebViewClient(new WebViewClient());
        mWebView.setWebChromeClient(new WebChromeClient());
        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.loadUrl("https://93ef86e3.ngrok.io");

        return v;
    }
}
