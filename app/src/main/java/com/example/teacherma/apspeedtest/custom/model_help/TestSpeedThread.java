package com.example.teacherma.apspeedtest.custom.model_help;

import android.util.Log;

import com.example.teacherma.apspeedtest.model.bean.TestResult;
import com.example.teacherma.apspeedtest.utils.BaseUtil;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

/**
 * @author teacherMa on 2017/11/4.
 */

public class TestSpeedThread extends Thread {
    private static final String TAG = "TestSpeedThread";

    private static final int UPWARD_TIME = 1000;
    private static final int BUFF_SIZE = 1024;
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
        byte[] buffer = new byte[BUFF_SIZE];
        try {
            Socket socket = new Socket(mIp, Integer.parseInt(mPort));
            OutputStream outputStream = socket.getOutputStream();
            InputStream inputStream = socket.getInputStream();
            long startTime = System.currentTimeMillis();
            while (System.currentTimeMillis() - startTime <= UPWARD_TIME) {
                outputStream.write(buffer);
            }
            sleep(UPWARD_TIME);
            socket.shutdownOutput();
            Log.e(TAG, "run: send complete");
            byte[] serverSize = new byte[4];
            if (inputStream.read(serverSize) == -1) {
                mCallbackHandler.obtainFailMessage().sendToTarget();
                return;
            }
            int serverTotalRevSize = BaseUtil.ByteArrayToInt(serverSize);
            Log.e(TAG, "run: send " + serverTotalRevSize);
            int clientTotalRevSize = 0;
            int onceRevSize;
            while ((onceRevSize = inputStream.read(buffer)) != -1) {
                clientTotalRevSize += onceRevSize;
            }
            Log.e(TAG, "run: rec " + clientTotalRevSize);
            TestResult result = new TestResult(Integer.toString(serverTotalRevSize),
                    Integer.toString(clientTotalRevSize));
            mCallbackHandler.obtainTestResultMessage(result).sendToTarget();
        } catch (Exception e) {
            e.printStackTrace();
            mCallbackHandler.obtainFailMessage().sendToTarget();
        }

    }
}
