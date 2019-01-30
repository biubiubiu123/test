package com.open.ccb.ccbdemo;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.os.Build;
import android.os.Bundle;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import com.open.ccb.ccbdemo.utils.ScreenMetrics;


public class BaseActivity extends AppCompatActivity implements View.OnClickListener {

    private ProgressDialog mProgressDialog;
    private int color_title = Color.parseColor("#3F51B5");

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setImmersiveColor(color_title);
    }

    /**
     * 设置沉浸式效果
     *
     * @param color
     */
    protected void setImmersiveColor(int color) {
        //安卓4.4-5.0沉浸式(全屏模式，添加假 View ,设置 ContentView 和 ChildView 的 fitsSystemWindows)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT && Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            ViewGroup mContentView = findViewById(Window.ID_ANDROID_CONTENT);
            ViewGroup mContentParent = (ViewGroup) mContentView.getParent();
            View statusBarView = mContentParent.getChildAt(0);
            if (statusBarView != null && statusBarView.getLayoutParams() != null &&
                    statusBarView.getLayoutParams().height == ScreenMetrics.instance(this).statusHeight) {
                //移除假的 View
                mContentParent.removeView(statusBarView);
            }
            //ContentView 不预留空间
            if (mContentParent.getChildAt(0) != null) {
                ViewCompat.setFitsSystemWindows(mContentParent.getChildAt(0), false);
            }
            //ChildView 不预留空间
            View mChildView = mContentView.getChildAt(0);
            if (mChildView != null) {
                ViewCompat.setFitsSystemWindows(mChildView, false);
            }
        }
        //安卓5.0以上设置沉浸式(着色模式，直接将状态栏颜色设置为title背景色)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            //取消设置透明状态栏,使 ContentView 内容不再覆盖状态栏
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

            //需要设置这个 flag 才能调用 setStatusBarColor 来设置状态栏颜色
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            //设置状态栏颜色
            window.setStatusBarColor(color);

            ViewGroup mContentView = findViewById(Window.ID_ANDROID_CONTENT);
            View mChildView = mContentView.getChildAt(0);
            if (mChildView != null) {
                //注意不是设置 ContentView 的 FitsSystemWindows, 而是设置 ContentView 的第一个子 View . 预留出系统 View 的空间.
                ViewCompat.setFitsSystemWindows(mChildView, true);
            }
        }
    }

    protected void showLoading(Context context, String prompt) {
        mProgressDialog = new ProgressDialog(context);
        mProgressDialog.setMessage(prompt);
        mProgressDialog.setCancelable(false);
        mProgressDialog.show();
    }

    protected void setLoadingText(String prompt) {
        if (mProgressDialog != null) {
            mProgressDialog.setMessage(prompt);
        }
    }

    protected void loadingCancel() {
        if (mProgressDialog != null) {
            mProgressDialog.dismiss();
        }
    }

    @Override
    public void onClick(View v) {
    }

    public void onMessage(Message msg) {
    }

    /**
     * bitmap转base64字符串
     *
     * @param bitmap
     * @return
     */
   /* public static String bitmapToBase64(Bitmap bitmap) {
        // 要返回的字符串
        String reslut = null;
        ByteArrayOutputStream baos = null;
        try {
            if (bitmap != null) {
                baos = new ByteArrayOutputStream();
                *//**
                 * 压缩只对保存有效果bitmap还是原来的大小
                 *//*
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
                baos.flush();
                baos.close();
                // 转换为字节数组
                byte[] byteArray = baos.toByteArray();
                // 转换为字符串
                reslut = Base64Utils.encode(byteArray);
            } else {
                return null;
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (baos != null) {
                    baos.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return reslut;
    }*/

    /**
     * 图片压缩
     *
     * @return
     */
   /* protected Bitmap imgCompress(Bitmap image) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        image.compress(Bitmap.CompressFormat.JPEG, 90, baos);// 质量压缩方法，这里100表示不压缩，把压缩后的数据存放到baos中
        int options = 90;
        while (baos.toByteArray().length / 1024 > 100 && options > 0) { // 循环判断如果压缩后图片是否大于100kb,大于继续压缩
            baos.reset(); // 重置baos即清空baos
            image.compress(Bitmap.CompressFormat.JPEG, options, baos);// 这里压缩options%，把压缩后的数据存放到baos中
            options -= 10;// 每次都减少10
        }
        ByteArrayInputStream isBm = new ByteArrayInputStream(baos.toByteArray());// 把压缩后的数据baos存放到ByteArrayInputStream中
        Bitmap bitmap = BitmapFactory.decodeStream(isBm, null, null);// 把ByteArrayInputStream数据生成图片
        return bitmap;
    }*/

    /**
     * 读取图片属性：旋转的角度
     *
     * @param path 图片绝对路径
     * @return degree旋转的角度
     */
  /*  public int readPictureDegree(String path) {
        int degree = 0;
        try {
            ExifInterface exifInterface = new ExifInterface(path);
            int orientation = exifInterface.getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_NORMAL);
            switch (orientation) {
                case ExifInterface.ORIENTATION_ROTATE_90:
                    degree = 90;
                    break;
                case ExifInterface.ORIENTATION_ROTATE_180:
                    degree = 180;
                    break;
                case ExifInterface.ORIENTATION_ROTATE_270:
                    degree = 270;
                    break;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return degree;
    }*/

    /*
     * 旋转图片
     * @param angle
     * @param bitmap
     * @return Bitmap
     */
   /* public Bitmap rotaingImageView(int angle, Bitmap bitmap) {
        //旋转图片 动作
        Matrix matrix = new Matrix();
        ;
        matrix.postRotate(angle);
        System.out.println("angle2=" + angle);
        // 创建新的图片
        Bitmap resizedBitmap = Bitmap.createBitmap(bitmap, 0, 0,
                bitmap.getWidth(), bitmap.getHeight(), matrix, true);
        return resizedBitmap;
    }*/

}
