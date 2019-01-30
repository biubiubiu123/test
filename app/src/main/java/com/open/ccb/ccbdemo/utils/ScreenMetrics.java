package com.open.ccb.ccbdemo.utils;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.graphics.Point;
import android.graphics.Rect;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;

/**
 * 说明：${获取屏幕相关信息}
 * 作者：yangcheng
 * 邮箱：ycheng.dream@qq.com
 * Date: 2018/1/24.
 */

public class ScreenMetrics {

    private static ScreenMetrics metrics;
    private Context context;

    /**
     * 屏幕宽度
     */
    public int width;

    /**
     * 屏幕高度
     */
    public int height;

    /**
     * 状态栏高度
     */
    public int statusHeight;

    public static ScreenMetrics instance(Context context) {
        if (metrics == null) {
            metrics = new ScreenMetrics(context);
        }
        return metrics;
    }

    private ScreenMetrics(Context context) {
        this.context = context;
        init();
    }

    public void init() {
        //获取屏幕原始尺寸（包含状态栏）
        getDisplayInfomation();
        //获取状态栏高度
        getStatusHeight2();
    }

    /**
     * 获取屏幕分辨率（宽*高）
     */
    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    public void getDisplayInfomation() {
        Point point = new Point();
        ((Activity) context).getWindowManager().getDefaultDisplay().getRealSize(point);
        width = point.x;
        height = point.y;
    }

    /**
     * 获取状态栏高度
     * 通过系统尺寸资源获取
     */
    public void getStatusHeight2() {
        //获取status_bar_height资源的ID
        int resourceId = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            //根据资源ID获取响应的尺寸值
            statusHeight = context.getResources().getDimensionPixelSize(resourceId);
            if (statusHeight == 0) {
                getStatusHeight3();
            }
        }
    }

    /**
     * 获取状态栏高度
     * 借助屏幕和应用区域高度
     */
    public void getStatusHeight3() {
        //屏幕
        DisplayMetrics dm = new DisplayMetrics();
        ((AppCompatActivity) context).getWindowManager().getDefaultDisplay().getMetrics(dm);
        //应用区域
        Rect outRect1 = new Rect();
        ((AppCompatActivity) context).getWindow().getDecorView().getWindowVisibleDisplayFrame(outRect1);
        statusHeight = dm.heightPixels - outRect1.height();  //状态栏高度=屏幕高度-应用区域高度
        if (statusHeight == 0) {
            getStatusHeight4();
        }
    }

    /**
     * 获取状态栏高度
     * 标题栏高度的测量
     */
    public void getStatusHeight4() {
        DisplayMetrics dm = new DisplayMetrics();
        ((AppCompatActivity) context).getWindowManager().getDefaultDisplay().getMetrics(dm);
        //应用区域
        Rect outRect1 = new Rect();
        ((AppCompatActivity) context).getWindow().getDecorView().getWindowVisibleDisplayFrame(outRect1);
        statusHeight = outRect1.top;
    }




}
