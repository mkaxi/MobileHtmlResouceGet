package com.huawei.lifeshare;


import android.app.AlertDialog;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

/**Ø
 * Created by chenaxi on 16/6/18.
 */
public class MainActivityTestWebView extends FragmentActivity  {

    EditText editText;
    WebView webView;
    WebView vie;
    Button  button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.webview_layout);

        button=(Button)findViewById(R.id.button);
        editText=(EditText) findViewById(R.id.edtext);
        webView=(WebView) findViewById(R.id.container);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vie.loadUrl("javascript:window.handler.show(document.body.innerHTML);");
            }
        });

        webView.loadUrl("http://jia.360.cn/pc/view.html?sn=36050750439");
        webView.getSettings().setJavaScriptEnabled(true);

        webView.addJavascriptInterface(new HandlerMK(), "handler");
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url) {
                Toast.makeText(MainActivityTestWebView.this, "网页加载完成", Toast.LENGTH_SHORT).show();
                vie=view;
                super.onPageFinished(view, url);
            }
        });

    }

    class HandlerMK {
        public void show(final String data) {
            Toast.makeText(MainActivityTestWebView.this, "执行了handler.show方法",  Toast.LENGTH_SHORT).show();

            new Handler(getMainLooper()).post(new Runnable() {
                @Override
                public void run() {
                    editText.setText(data+"");
                }
            });
            new AlertDialog.Builder(MainActivityTestWebView.this).setMessage(data).create().show();

        }
    }
}
