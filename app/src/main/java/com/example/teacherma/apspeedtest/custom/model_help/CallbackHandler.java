package com.example.teacherma.apspeedtest.custom.model_help;

import android.os.Handler;
import android.os.Message;

import com.example.teacherma.apspeedtest.api.OnResultCallback;
import com.example.teacherma.apspeedtest.model.bean.TestResult;
import com.example.teacherma.apspeedtest.utils.Constants;

/**
 * @author teacherMa on 2017/11/4.
 */

public class CallbackHandler extends Handler {
    private OnResultCallback<TestResult> mOnResultCallback;
    private static final int TEST_RESULT = 451;

    public CallbackHandler(OnResultCallback<TestResult> onResultCallback) {
        mOnResultCallback = onResultCallback;
    }

    @Override
    public void handleMessage(Message msg) {
        if (TEST_RESULT != msg.what){
            return;
        }
        if (null == mOnResultCallback) {
            return;
        }
        mOnResultCallback.onSuccess((TestResult)msg.obj, Constants.ResultCode.REMOTE);
    }

    public Message obtainTestResultMessage(TestResult result){
        return this.obtainMessage(TEST_RESULT,result);
    }
}
