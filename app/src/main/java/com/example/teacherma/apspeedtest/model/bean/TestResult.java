package com.example.teacherma.apspeedtest.model.bean;

import android.text.format.DateFormat;

import com.example.teacherma.apspeedtest.utils.Constants;

import java.util.Date;

import io.realm.RealmObject;

/**
 * @author teacherMa on 2017/11/2.
 */

public class TestResult extends RealmObject {
    private String mTime;
    private String mIp;
    private String mPort;
    private String mTcpUpwardSpeed;
    private String mUdpUpwardSpeed;
    private String mUdpDownSpeed;
    private String mTcpDownSpeed;

    public TestResult() {
        mTime = (String) DateFormat.format(Constants.DATE_TO_STRING_FORMAT, new Date());
        mIp = "192.168.1.1";
        mPort = "8080";
        mTcpUpwardSpeed = "1MB/s";
        mTcpDownSpeed = "2.5MB/s";
        mUdpDownSpeed = "2.5MB/s";
        mUdpUpwardSpeed = "1MB/s";
    }

    public TestResult(String tcpUpwardSpeed, String tcpDownSpeed) {
        mTime = (String) DateFormat.format(Constants.DATE_TO_STRING_FORMAT, new Date());
        mTcpUpwardSpeed = tcpUpwardSpeed;
        mTcpDownSpeed = tcpDownSpeed;
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

    public String getTcpUpwardSpeed() {
        return mTcpUpwardSpeed;
    }

    public void setTcpUpwardSpeed(String tcpUpwardSpeed) {
        mTcpUpwardSpeed = tcpUpwardSpeed;
    }

    public String getTcpDownSpeed() {
        return mTcpDownSpeed;
    }

    public void setTcpDownSpeed(String tcpDownSpeed) {
        mTcpDownSpeed = tcpDownSpeed;
    }

    public String getUdpUpwardSpeed() {
        return mUdpUpwardSpeed;
    }

    public void setUdpUpwardSpeed(String udpUpwardSpeed) {
        mUdpUpwardSpeed = udpUpwardSpeed;
    }

    public String getUdpDownSpeed() {
        return mUdpDownSpeed;
    }

    public void setUdpDownSpeed(String udpDownSpeed) {
        mUdpDownSpeed = udpDownSpeed;
    }
}
