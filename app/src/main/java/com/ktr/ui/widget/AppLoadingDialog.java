package com.ktr.ui.widget;


import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ktr.ktr_libray.R;


/**
 * 
 * @author zhoubo
 *
 */
public class AppLoadingDialog {

    Context context;

    private Dialog dialog = null;

    private TextView tipTextView = null;

    public static String MSG_DEFAULT = "载入中...";

    private AppLoadingDialog(Context context) {

        this.context = context;
        LayoutInflater inflater = LayoutInflater.from(this.context);
        View v = inflater.inflate(R.layout.loading_dialog, null);// 得到加载view
        LinearLayout layout = (LinearLayout) v.findViewById(R.id.dialog_view);// 加载布局
        this.tipTextView = (TextView) v.findViewById(R.id.tipTextView);// 提示文字
        this.tipTextView.setText(MSG_DEFAULT);// 设置加载信息

        this.dialog = new Dialog(this.context, R.style.loading_dialog);// 创建自定义样式dialog
        this.dialog.setCancelable(true);// 不可以用“返回键”取消
        this.dialog.setContentView(layout, new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT));// 设置布局
    }

    /**
     * 得到自定义的progressDialog
     * 
     * @param context
     * 
     * @return
     */
    public static AppLoadingDialog getInstance(Context context) {

        return new AppLoadingDialog(context);
    }

    public Dialog getDialog() {

        return this.dialog;
    }

    public void show() {

        this.hide();

        if (this.dialog != null)
            this.dialog.show();
    }

    public void show(int resId) {

        if (this.context != null) {
            String msg = this.context.getResources().getString(resId);
            this.show(msg);
        }
    }

    public void show(String msg) {

        this.update(msg);
        this.show();
    }

    public void update(int resId) {

        if (this.context != null) {
            String msg = this.context.getResources().getString(resId);
            this.update(msg);
        }
    }

    public void update(String msg) {

        if (this.tipTextView != null) {
            this.tipTextView.setText(msg);
        }
    }

    public void hide() {

        if (this.dialog != null && this.dialog.isShowing())
            this.dialog.hide();
    }

    public void dismiss() {

        if (this.dialog != null)
            this.dialog.dismiss();
    }
}
