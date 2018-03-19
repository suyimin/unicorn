package com.xdroid.util;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Environment;
import android.os.Looper;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.Thread.UncaughtExceptionHandler;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 异常日志存储在/sdcard/data/package_name/cache/或者/data/data/package_name/cache/目录下
 * 使用方法：在Application的onCreate方法中添加：
 * new CrashHandler.Builder(this).debug(false).tip("程序崩溃了").file("crash", "crash_log", ".crash").build().catching();
 */
public class CrashHandler implements UncaughtExceptionHandler {
    public static final String TAG = CrashHandler.class.getSimpleName();
    private UncaughtExceptionHandler mDefaultHandler; //系统异常处理器

    private boolean debug; //是否调试模式
    private Context context; //需要用到的上下文
    private String tip; //错误时的提示信息
    private String fileDir;//文件路径
    private String fileName; //文件名不包括后缀
    private String suffix;//后缀名

    private CrashHandler(Builder builder) {
        this.debug = builder.debug;
        this.context = builder.contex;
        this.tip = builder.tip;
        this.fileName = builder.fileName;
        this.fileDir = builder.fileDir;
        this.suffix = builder.suffix;
    }

    /**
     * 开始注册处理崩溃异常
     */
    public void catching() {
        this.mDefaultHandler = Thread.getDefaultUncaughtExceptionHandler();
        Thread.setDefaultUncaughtExceptionHandler(this);
        Log.d(TAG, "Start catching");
    }

    /**
     * 销毁异常捕捉
     */
    public void stop() {

    }

    @Override
    public void uncaughtException(Thread t, Throwable e) {
        // 若为调试则输出错误信息
        if (debug) {
            e.printStackTrace();
            return;
        }
        if (!handleException(e) && mDefaultHandler != null) {
            mDefaultHandler.uncaughtException(t, e);
        } else {
            restartApplication();
        }
        System.exit(0);//        android.os.Process.killProcess(android.os.Process.myPid());
    }

    /**
     * 处理异常
     *
     * @param ex
     * @return
     */
    private boolean handleException(Throwable ex) {
        if (ex == null) {
            return false;
        }

        new Thread(new Runnable() {
            @Override
            public void run() {
                Looper.prepare();
                Toast.makeText(context, tip, Toast.LENGTH_LONG).show();
                Looper.loop();
            }
        }).start();

        write2File(ex); //写入文件
        upload2Server(ex); //上传到服务器

        return true;
    }

    /**
     * 把错误信息写入文件
     *
     * @param ex
     */
    private void write2File(Throwable ex) {
        // 如果没有存储卡则返回
        String state = Environment.getExternalStorageState();
        if (!TextUtils.equals(Environment.MEDIA_MOUNTED, state)) {
            return;
        }
        // 取得存储目录
        File directory = getStoreFile();
        if (!directory.exists()) {
            directory.mkdir();
        }
        // 当前时间
        long now = System.currentTimeMillis();
        Date date = new Date(now);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String time = format.format(date);

        File file = new File(directory + File.separator + fileName + "_" + time + suffix);
        FileWriter fw = null;
        try {
            fw = new FileWriter(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (fw == null) return;
        PrintWriter pw = new PrintWriter(new BufferedWriter(fw));
        // 写时间
        pw.println(time);
        writePhoneInfo(pw);
        pw.println();
        ex.printStackTrace(pw);
        pw.close();
    }

    /**
     * 存储在应用缓存目录下
     *
     * @return
     */
    private File getStoreFile() {
        String path = null;
        String state = Environment.getExternalStorageState();
        boolean isRemovable = Environment.isExternalStorageRemovable();
        if (TextUtils.equals(state, Environment.MEDIA_MOUNTED) || !isRemovable) {
            path = this.context.getExternalCacheDir().toString();
        } else {
            path = this.context.getCacheDir().toString();
        }
        return new File(path + File.separator + fileDir);
    }

    /**
     * 手机型号等信息
     *
     * @param pw
     */
    private void writePhoneInfo(PrintWriter pw) {
        String packageName = this.context.getPackageName();
        PackageManager packageManager = this.context.getPackageManager();
        PackageInfo packageInfo = null;
        try {
            packageInfo = packageManager.getPackageInfo(packageName, PackageManager.GET_ACTIVITIES);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        pw.println("Application Version: " + packageInfo.versionName + "_" + packageInfo.versionCode);
        pw.println("OS Version: " + Build.VERSION.RELEASE + "_" + Build.VERSION.SDK_INT);
        pw.println("Vendor: " + Build.MANUFACTURER);
        pw.println("Model: " + Build.MODEL);
        pw.println("CPU ABI: " + Build.CPU_ABI);
    }

    /**
     * 把错误信息上传到服务器
     *
     * @param ex
     */
    private void upload2Server(Throwable ex) {
        // insert code here
    }

    /**
     * 重启Application
     */
    private void restartApplication() {
        String packageName = this.context.getPackageName();
        PackageManager packageManager = this.context.getPackageManager();
        Intent intent = packageManager.getLaunchIntentForPackage(packageName);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        this.context.startActivity(intent);
    }

    /**
     * 构造器类
     */
    public static class Builder {
        private boolean debug;//是否调试
        private Context contex; // 上下文
        private String fileDir; // 文件路径
        private String fileName; // 文件名
        private String suffix;//后缀名
        private String tip; // 提示信息

        public Builder(Context context) {
            this.contex = context;
            this.debug = false;
            this.tip = "I am sorry.";
            this.fileDir = "crash";
            this.fileName = "crash_log";
            this.suffix = ".trace";
        }

        /**
         * 是否调试模式
         *
         * @param isDebug
         * @return
         */
        public Builder debug(boolean isDebug) {
            this.debug = isDebug;
            return this;
        }

        /**
         * 错误Toast消息
         *
         * @param tip
         * @return
         */
        public Builder tip(String tip) {
            this.tip = tip;
            return this;
        }

        /**
         * 存储路径文件名以及后缀名
         *
         * @param fileDir
         * @param fileName
         * @param suffix
         * @return
         */
        public Builder file(String fileDir, String fileName, String suffix) {
            this.fileDir = fileDir;
            this.fileName = fileName;
            this.suffix = suffix;
            return this;
        }

        public CrashHandler build() {
            return new CrashHandler(this);
        }


    }
}
