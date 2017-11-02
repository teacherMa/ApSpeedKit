package com.example.teacherma.apspeedtest.custom;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.example.teacherma.apspeedtest.BuildConfig;
import com.example.teacherma.apspeedtest.R;
import com.example.teacherma.apspeedtest.api.NewTestCreatedCallback;
import com.example.teacherma.apspeedtest.utils.BaseUtil;
import com.example.teacherma.apspeedtest.utils.Constants;

/**
 * @author teacherMa on 2017/11/2.
 */

public class NewTestPopupWindow extends PopupWindow {
    private Context mContext;

    private EditText mIp1;
    private EditText mIp2;
    private EditText mIp3;
    private EditText mIp4;
    private EditText mPort;
    private TextView mConfirm;
    private TextView mCancel;

    private NewTestCreatedCallback mTestCreatedCallback;

    public NewTestPopupWindow(Context context) {
        super(context);

        mContext = context;

        setWidth(ViewGroup.LayoutParams.WRAP_CONTENT);
        setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);

        View view = LayoutInflater.from(context).inflate(R.layout.new_test_window, null);

        mIp1 = view.findViewById(R.id.ip_edit_1);
        mIp2 = view.findViewById(R.id.ip_edit_2);
        mIp3 = view.findViewById(R.id.ip_edit_3);
        mIp4 = view.findViewById(R.id.ip_edit_4);
        mPort = view.findViewById(R.id.port_edit);
        mConfirm = view.findViewById(R.id.create_confirm);
        mCancel = view.findViewById(R.id.create_cancel);

        setContentView(view);

        setListener();

        setFocusable(true);
        setTouchable(true);

        setOnDismissListener(new OnDismissListener() {
            @Override
            public void onDismiss() {
                WindowManager.LayoutParams layoutParams = ((Activity) mContext).getWindow().getAttributes();
                layoutParams.alpha = 1.0f;
                ((Activity) mContext).getWindow().setAttributes(layoutParams);
            }
        });

        mIp1.requestFocus();

    }

    public NewTestPopupWindow setTestCreatedCallback(NewTestCreatedCallback testCreatedCallback) {
        mTestCreatedCallback = testCreatedCallback;
        return this;
    }

    @Override
    public void showAtLocation(View parent, int gravity, int x, int y) {
        super.showAtLocation(parent, gravity, x, y);
        WindowManager.LayoutParams layoutParams = ((Activity) mContext).getWindow().getAttributes();
        layoutParams.alpha = 0.5f;
        ((Activity) mContext).getWindow().setAttributes(layoutParams);
    }

    private void setListener() {
        mCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NewTestPopupWindow.this.dismiss();
            }
        });

        mConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isLegalIpAddress()) {
                    BaseUtil.showToast(BaseUtil.getString(R.string.illegal_ip));
                    return;
                }
                if (mTestCreatedCallback != null) {
                    mTestCreatedCallback.onNewTestCreated(getIp(),getPort());
                    NewTestPopupWindow.this.dismiss();
                }
            }
        });
    }

    private boolean isLegalIpAddress() {
        try {
            return Integer.parseInt(mIp1.getText().toString()) <= 255
                    && Integer.parseInt(mIp1.getText().toString()) >= 0
                    && Integer.parseInt(mIp2.getText().toString()) <= 255
                    && Integer.parseInt(mIp2.getText().toString()) >= 0
                    && Integer.parseInt(mIp3.getText().toString()) <= 255
                    && Integer.parseInt(mIp3.getText().toString()) >= 0
                    && Integer.parseInt(mIp4.getText().toString()) <= 255
                    && Integer.parseInt(mIp4.getText().toString()) >= 0
                    && Integer.parseInt(mPort.getText().toString()) <= 65535
                    && Integer.parseInt(mPort.getText().toString()) >= 0;
        }catch (Exception e){
            return false;
        }
    }

    private String getIp() {
        return new StringBuilder(mIp1.getText().toString())
                .append(Constants.DOT)
                .append(mIp2.getText().toString())
                .append(Constants.DOT)
                .append(mIp3.getText().toString())
                .append(Constants.DOT)
                .append(mIp4.getText().toString()).toString();

    }

    private String getPort(){
        return mPort.getText().toString();
    }

}
