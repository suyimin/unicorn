package com.gy.amobile.hslibrary.widget;

import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.os.Handler;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.gy.amobile.hslibrary.R;


/**
 * 提示信息
 */
public class PromptUtils {

    private static Handler mHandler = new Handler();

    /**
     * 自定义toast提示信息
     *
     * @param context
     * @param title   头部提示信息
     * @param content 提示内容
     */
    public static void toastPrompt(Context context, String title, String content) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View layout = inflater.inflate(R.layout.toast_prompt_message, null); // 通过布局注入器，注入布局给View对象
        TextView tv_title = (TextView) layout.findViewById(R.id.tv_prompt_title);       // 提示头部信息
        TextView tv_context = (TextView) layout.findViewById(R.id.tv_prompt_context);   // 提示内容

        if (TextUtils.isEmpty(title)) tv_title.setVisibility(View.GONE);// 如果提示头部信息内容为空则隐藏
        if (TextUtils.isEmpty(content)) tv_context.setVisibility(View.GONE);// 如果内容信息为空则隐藏

        tv_title.setText(title);
        tv_context.setText(content);

        Toast toast = new Toast(context);               // 创建一个Toast
        toast.setGravity(Gravity.CENTER, 12, 40);   // 设置Toast的位置
        toast.setDuration(Toast.LENGTH_SHORT);      // 设置Toast生存时间
        toast.setView(layout);                      // 设置Toast布局
        toast.show();                               // 显示Toast
    }

    /**
     * 弹出提示
     *
     * @param context
     * @param view
     * @param content 提示内容
     */
    public static void popupWindow(Context context, View view, String content) {
        View v = LayoutInflater.from(context).inflate(R.layout.pop_prompt_message, null); // 通过布局注入器，注入布局给View对象
        TextView tv_context = (TextView) v.findViewById(R.id.tv_pop_context);
        tv_context.setText(content);
        final PopupWindow pw = new PopupWindow(v, 260, ViewGroup.LayoutParams.WRAP_CONTENT, true); // 通过view 和宽·高，构造PopopWindow
        pw.setBackgroundDrawable(new BitmapDrawable()); // 设置背景，为了点击外部区域，让popwindow消失
        pw.showAsDropDown(view); // 将window视图显示在myButton下面

        /**
         * 设置popupWindow在2秒后关闭
         */
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (null != pw) pw.dismiss();
            }
        }, 2000);
    }

}
