package com.open.ccb.ccbdemo;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.TextView;

import com.ccbsdk.contact.SDKContext;
import com.ccbsdk.ui.activity.CCBBaseWebViewActivity;

public class CCBH5CustomActivity extends CCBBaseWebViewActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TextView textView = new TextView(this);
        textView.setTextSize(100);
        textView.setText("qqqq");
        textView.setGravity(Gravity.CENTER);

         getWebViewLayout().addView(textView);
         getCloudWebView().loadUrl("file:///android_asset/demo.html");

        // getCloudWebView().loadUrl("http://www.baidu.com");




    }
}
