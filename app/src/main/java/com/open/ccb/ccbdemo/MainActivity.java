package com.open.ccb.ccbdemo;
import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Address;
import android.location.Criteria;
import android.location.Geocoder;
import android.location.GpsSatellite;
import android.location.GpsStatus;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


import com.ccbsdk.api.CCBSDK;
import com.ccbsdk.api.SDKInitListener;
import com.ccbsdk.log.LogFactory;
import com.ccbsdk.utils.LoadingDialogUtils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;


public class MainActivity extends com.open.ccb.ccbdemo.BaseActivity implements SDKInitListener  {


    public String appKey = "bd97a304_786c_4d86_893e_ab806052656d";
    public String bPublicUrl = "http://128.196.221.13:16688/getMerchantSign";
    public String bPublicKey =   "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAixGPNxb9EPpHG5v8S8WuHrA+NmTNRuX1k07eQ7Z9jAU2BjhFdwYQmH4wpbsx6z5v8tVwDyOb11BNzTJ8gZ79zFnC6LKShsx1WQYa9U24DmOlvIkxMMkjeUzReNvCA659kotmHrDTbLNM+3RFlCRz75efGMOqup07t9flt+7U8DAk38MZ1TxEdZnlXaPjUATvl18MgSlB12gakpsSMXDZ0bJNmRgmUirJ2Tqbi2IK3mcLc8ZT/GU+j9z4Zo5yFZiDAtbjvfZBUcVDwNgetlk/YX+NA+gcYHMBbCnb0uuK+oYGZ2gtt56jT9buscHBDSBcN+Pw4hojRfsAXxr4UAhMxQIDAQAB";
    public String sPublicKey = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAjVdGe8P2JumYU4sB1Zywioxw1+HaZB/KmzrzlClj2m2e4ZPqS5cYe2FCiCwBmyizZrVYGfECugszj7e+OjbrFeqOpCgOYnXrbX0me0YdqLAowELbI4cqRxGciFlbgkjJXLoTHX/ZPPKAaF8VCNfQJrfpAqYZBeGPHWiODu4m3P8lGG5U+kHsjemdFf+5iwBabtm8IKvvE3fIfboXjnsdLSW3xxWjFK2A7iubUA8U0jA9UCCJp61qDnjmMC4hfW5QEWQMchW2D6vRYyZZStKGYxKto60jj5qnqm0+xyX9X5pBz9hTWTIqCHpzbyeLTOeG3l2jQ4ut9ZgVXP9IRj1IzwIDAQAB";
//    public String sPublicUrl = "https://128.196.200.30:443/api/android";//获取token(开发)
    public String sPublicUrl = "https://128.196.200.30/api/andriod";//获取token(开发)
    private EditText main_url;
    private EditText main_appid;
    private EditText ebPublicKey;
    private EditText esPublicUrl;
    private EditText esPublicKey;
    private EditText main_token;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE)
                    != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(new String[]{Manifest.permission.READ_PHONE_STATE}, 1);

            }
        }
        if(savedInstanceState!=null){
//            main_appid. setText(savedInstanceState.getString("appKey"    ) );
//            main_url.   setText(savedInstanceState.getString("bPublicUrl") );
//            ebPublicKey.setText(savedInstanceState.getString("bPublicKey") );
//            esPublicUrl.setText(savedInstanceState.getString("sPublicUrl") );
//            esPublicKey.setText(savedInstanceState.getString("sPublicKey") );

           appKey     =savedInstanceState.getString("appKey"    );
           bPublicUrl = savedInstanceState.getString("bPublicUrl");
           bPublicKey = savedInstanceState.getString("bPublicKey");
           sPublicUrl = savedInstanceState.getString("sPublicUrl");
           sPublicKey = savedInstanceState.getString("sPublicKey");
        }
        initView();

    }

    public void initView() {
        main_appid = findViewById(R.id.main_appid);
        main_url = findViewById(R.id.main_url);
        ebPublicKey = findViewById(R.id.bPublicKey);
        esPublicUrl = findViewById(R.id.sPublicUrl);
        esPublicKey = findViewById(R.id.sPublicKey);

        main_token = findViewById(R.id.main_token);


        main_appid.setText(appKey);
        main_url.setText(bPublicUrl);
        ebPublicKey.setText(bPublicKey);
        esPublicUrl.setText(sPublicUrl);
        esPublicKey.setText(sPublicKey);
    }

    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.save){
            appKey=main_appid. getText().toString().trim();
            bPublicUrl=main_url.   getText().toString().trim();
            bPublicKey =ebPublicKey.getText().toString().trim();
            sPublicUrl =esPublicUrl.getText().toString().trim();
            sPublicKey =esPublicKey.getText().toString().trim();

            main_appid.setText(appKey);
            main_url.setText(bPublicUrl);
            ebPublicKey.setText(bPublicKey);
            esPublicUrl.setText(sPublicUrl);
            esPublicKey.setText(sPublicKey);
        }
        if (v.getId() == R.id.index_btn_01) {

            appKey=main_appid. getText().toString().trim();
            bPublicUrl=main_url.   getText().toString().trim();
            bPublicKey =ebPublicKey.getText().toString().trim();
            sPublicUrl =esPublicUrl.getText().toString().trim();
            sPublicKey =esPublicKey.getText().toString().trim();

            CCBSDK.instance().initSDK(getApplicationContext(), appKey, bPublicUrl, bPublicKey, sPublicUrl, sPublicKey, this);
        }
        if (v.getId() == R.id.index_btn_03) {
            Map parameterMap = new HashMap();
            parameterMap.put("idCard", "220121313342532432");
            parameterMap.put("amount", "50");
            parameterMap.put("name", "老王");
            String classPath = getClass().getName();
          // CCBSDK.instance().intoCustomizedH5Activity(this, "PaymentInstem", "FcnId",parameterMap,classPath.replace("MainActivity","CCBH5CustomActivity"));
           CCBSDK.instance().intoH5Activity(this, "PaymentInstem", "Main",parameterMap,"#34ff0000");
            //CCBSDK.instance().intoCustomizedH5Activity(this,"PaymentInstem", "", parameterMap,classPath.replace("MainActivity","CCBH5CustomActivity"));//CCBSDK.instance().intoCustomizedH5Activity(this,"PaymentInstem", "FcnId",null,"");
           // CCBSDK.instance().intoCustomizedH5Activity(this,"https://128.196.200.30/h5/bk/PaymentInstem/index.html", "", parameterMap,"");

        }

    }


    @Override
    public void onSuccess(String successInfo) {
        //Toast.makeText(this, "SDK初始化成功" + SDKContext.getUrlInf("PaymentInstem").toString(), Toast.LENGTH_SHORT).show();
        Toast.makeText(this, successInfo, Toast.LENGTH_LONG).show();
        Log.e("MainActivity","验证开发者成功：" +successInfo);
    }

    @Override
    public void onFailed(String failedInfo) {
        Toast.makeText(this, failedInfo, Toast.LENGTH_LONG).show();
        Log.e("MainActivity","失败：" +failedInfo);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("appKey"     , appKey);
        outState.putString("bPublicUrl" , bPublicUrl);
        outState.putString("bPublicKey" , bPublicKey);
        outState.putString("sPublicUrl" , sPublicUrl);
        outState.putString("sPublicKey" , sPublicKey);
    }
}
