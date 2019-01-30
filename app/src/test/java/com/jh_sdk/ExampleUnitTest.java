package com.jh_sdk;

import com.alibaba.fastjson.JSON;
import com.ccbsdk.log.LogFactory;
import com.ccbsdk.utils.DeviceHelper;
import com.ccbsdk.utils.StringUtil;

import org.json.JSONObject;
import org.junit.Test;

import cn.com.infosec.Base64;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void main(){
        String error="msg";
        int code =300;
        // errorJson = "{\"returnMsg\":" + "\"error\"" + ",\"returnCode\":" + "200" + "}";
        String errorJson = "{\"returnMsg\":" + "\""+error+"\"" + ",\"returnCode\":" + code+  "}";
        System.out.println(DeviceHelper.getDateOrTime("HHmmssSSS"));
        System.out.println(DeviceHelper.getDateOrTime("yyyyMMdd"));
        com.alibaba.fastjson.JSONObject jsonObject = JSON.parseObject(errorJson);
        System.out.println(errorJson);
        String str1 = StringUtil.generateKey(16);
        String str = Base64.encode(cn.com.infosec.Random.nextBytes(12));
        System.out.println("------>"+str1);
        System.out.println("------>"+str);
           System.out.println(jsonObject.getString("returnMsg")+"-----"+jsonObject.getString("returnCode"));


    }
}