package com.gy.amobile.hslibrary.http;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.view.Gravity;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.gy.amobile.hslibrary.R;


/**
 * 自定义的progressDialog<br />
 * <p>
 * 1.支持百分比进度显示
 * <p>
 * 2.支持取消
 * <p>
 * 3.支持自动关闭,可自定义时间
 * <p>
 * com.gy.mobile.gyaf.ui.widget.HSHudDialog
 *
 * @author xuxiaoming create at 2014年10月9日 上午11:38:02
 */
@SuppressLint("WrongViewCast")
public class HSHudDialog extends Dialog {

    /**
     * 提示内容视图
     */
    private static TextView msgView;

    private static ProgressBar pbView;

    public HSHudDialog(Context context, int theme) {
        super(context, theme);
    }

    /**
     * 创建对话框
     *
     * @param context
     * @return
     */
    public static HSHudDialog createDialog(Context context) {
        HSHudDialog dialog = new HSHudDialog(context, R.style.HSHud);
        dialog.setContentView(R.layout.hshud);
        pbView = (ProgressBar) dialog.findViewById(R.id.img_hshud);
        msgView = (TextView) dialog.findViewById(R.id.tv_hshud_msg);
        dialog.getWindow().getAttributes().gravity = Gravity.CENTER;
        //dialog.getWindow().setType(WindowManager.LayoutParams.TYPE_SYSTEM_ALERT);
        return dialog;
    }

    /**
     * @return 返回值：void
     * @method 方法名：setMessageByGif
     * @features 功    能：设置对话框提示内容
     * @params 参    数：@param msg
     * @modify 修改者: pansq
     */
    public void setMessage(String msg) {
        msgView.setText(msg);
    }

    @Override
    public void dismiss() {
        super.dismiss();
    }
}
