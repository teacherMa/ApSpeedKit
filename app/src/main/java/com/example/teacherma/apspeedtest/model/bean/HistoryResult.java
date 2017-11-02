package com.example.teacherma.apspeedtest.model.bean;

import android.text.format.DateFormat;

import com.example.teacherma.apspeedtest.utils.Constants;

import java.util.Date;

/**
 * @author teacherMa on 2017/11/2.
 */

public class HistoryResult {
    private String mTime;
    private String mIp;
    private String mPort;
    private String mUpwardSpeed;
    private String mDownSpeed;

    public HistoryResult() {
        mTime = (String) DateFormat.format(Constants.DATE_TO_STRING_FORMAT,new Date());
        mIp = "192.168.1.1";
        mPort = "8080";
        mUpwardSpeed = "1MB/s";
        mDownSpeed = "2.5MB/s";
    }

    public String getTime() {
        return mTime;
    }

    public void setTime(String time) {
        mTime = time;
    }

    public String getIp() {
        return mIp;
    }

    public void setIp(String ip) {
        mIp = ip;
    }

    public String getPort() {
        return mPort;
    }

    public void setPort(String port) {
        mPort = port;
    }

    public String getUpwardSpeed() {
        return mUpwardSpeed;
    }

    public void setUpwardSpeed(String upwardSpeed) {
        mUpwardSpeed = upwardSpeed;
    }

    public String getDownSpeed() {
        return mDownSpeed;
    }

    public void setDownSpeed(String downSpeed) {
        mDownSpeed = downSpeed;
    }
}
