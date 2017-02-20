package com.gy.amobile.hslibrary.http;

import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.os.Message;

public class HSHud {
    private static HSHudDialog dialog;

    private static Context context;

    private static HSHudDismissCallback callback;

    public static void showLoadingMessage(Context context, String msg, boolean cancelable) {
        dismiss();
        setDialog(context, msg, cancelable);
        if (dialog != null) {
            dialog.show();
            if (cancelable) autoDismiss(30000);
        }
    }

    /**
     * 设置dialog关闭时间
     * @param context
     * @param msg
     * @param autoClose
     */
    public static void showLoadingMessage(Context context, String msg, int autoClose) {
        dismiss();
        setDialog(context, msg, true);
        if (dialog != null) {
            dialog.show();
            autoDismiss(autoClose);
        }
    }

    private static void setDialog(Context ctx, String msg, boolean cancelable) {
        context = ctx;
        if (!isContextValid()) return;
        dialog = HSHudDialog.createDialog(ctx);
        dialog.setMessage(msg);
        dialog.setCanceledOnTouchOutside(false);
        dialog.setCancelable(cancelable); // back键是否可dimiss对话框

    }

    public static void dismiss() {
        if (isContextValid() && dialog != null && dialog.isShowing()) dialog.dismiss();
        dialog = null;
    }

    /**
     * 计时关闭对话框
     * 
     */
    private static void autoDismiss(final long time) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(time);
                    handler.sendEmptyMessage(0);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private static Handler handler = new Handler() {
        public void handleMessage(Message msg) {
            if (msg.what == 0) {
                dismiss();
                if (callback != null) {
                    callback.onDismiss();
                }
            }

        };
    };

    /**
     * 判断parent view是否还存在
     * 若不存在不能调用dismis，或setDialog等方法
     * @return
     */
    private static boolean isContextValid() {
        if (context == null) return false;
        if (context instanceof Activity) {
            Activity act = (Activity) context;
            if (act.isFinishing()) return false;
        }
        return true;
    }

    public static void setCancelable(boolean canceled) {
        dialog.setCancelable(canceled);
    }
}
