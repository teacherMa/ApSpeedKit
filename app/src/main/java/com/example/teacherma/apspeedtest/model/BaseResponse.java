package com.example.teacherma.apspeedtest.model;

import com.example.teacherma.apspeedtest.utils.BaseUtil;
import com.example.teacherma.apspeedtest.utils.Constants;

/**
 * @author teacherMa on 2017/11/1.
 */

public class BaseResponse<T> {

    private int mCode;
    private String mDesc;
    private T mBody;

    public int getCode() {
        return mCode;
    }

    public void setCode(int code) {
        mCode = code;
    }

    public String getDesc() {
        return BaseUtil.checkNotNull(mDesc);
    }

    public void setDesc(String desc) {
        mDesc = desc;
    }

    public T getBody() {
        return mBody;
    }

    public void setBody(T body) {
        mBody = body;
    }


    public boolean isSuccess() {
        return mCode == Constants.ResponseCode.SUCCESS;
    }


}
