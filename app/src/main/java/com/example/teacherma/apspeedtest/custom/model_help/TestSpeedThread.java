package com.example.teacherma.apspeedtest.custom.model_help;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

/**
 * @author teacherMa on 2017/11/4.
 */

public class TestSpeedThread extends Thread {
    private static final int UPWARD_TIME = 5000;
    private static final int BUFF_SIZE = 8*1024;
    private CallbackHandler mCallbackHandler;
    private String mIp;
    private String mPort;

    public TestSpeedThread(CallbackHandler callbackHandler, String ip, String port) {
        mCallbackHandler = callbackHandler;
        mIp = ip;
        mPort = port;
    }

    @Override
    public void run() {
        try {
            Socket socket = new Socket(mIp, Integer.parseInt(mPort));
            OutputStream outputStream = socket.getOutputStream();
            InputStream inputStream = socket.getInputStream();
            long startTime = System.currentTimeMillis();
            while (System.currentTimeMillis() - startTime <= UPWARD_TIME) {
                outputStream.write(new byte[BUFF_SIZE]);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
