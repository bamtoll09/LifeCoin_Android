package caa.kr.lifecoin;

import android.app.Fragment;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.PermissionRequest;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import static android.content.ContentValues.TAG;

public class CameraFragment extends Fragment {

    private static CameraFragment CF;

    public static CameraFragment getInstance() {
        if (CF == null) CF = new CameraFragment();

        return CF;
    }

    private WebView mWebView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.layout_camera, container, false);

        mWebView = (WebView) v.findViewById(R.id.web_qr_reader);

        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.getSettings().setAllowFileAccessFromFileURLs(true);
        mWebView.getSettings().setAllowUniversalAccessFromFileURLs(true);
        mWebView.getSettings().setDomStorageEnabled(true);
        mWebView.getSettings().setPluginState(WebSettings.PluginState.ON);
        mWebView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);

                if (getActivity() != null)
                    ((MainActivity) getActivity()).LoadingScreenSwitch(1);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);

                if (getActivity() != null)
                    ((MainActivity) getActivity()).LoadingScreenSwitch(0);
            }
        });
        mWebView.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onPermissionRequest(final PermissionRequest request) {
                Log.d(TAG, "onPermissionRequest: onPermissionRequest");
                    request.grant(request.getResources());
            }
        });

        mWebView.loadUrl("https://bc687d1f.ngrok.io");


        return v;
    }

    protected void refresh() {
        mWebView.reload();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ((MainActivity) getActivity()).LoadingScreenSwitch(0);
    }
}
