package com.example.teacherma.apspeedtest.custom.model_help;

import android.util.Log;

import com.example.teacherma.apspeedtest.model.bean.TestResult;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.Socket;

/**
 * @author teacherMa on 2017/11/4.
 */

public class TestSpeedThread extends Thread {
    private static final String TAG = "TestSpeedThread";
    public static final String INFO_PORT = "12346";

    private static final int TEST_TIME = 3000;
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
            Socket infoSocket = new Socket(mIp,Integer.parseInt(INFO_PORT));

            Log.e(TAG, "run: tcp test start" );

            //Stream used to test speed between client and server
            OutputStream outputStream = socket.getOutputStream();
            InputStream inputStream = socket.getInputStream();

            //Stream used to transport information between client and server
            OutputStream infoOutputStream = infoSocket.getOutputStream();
            InputStream infoInputStream = infoSocket.getInputStream();

            PrintStream infoPrinter = new PrintStream(infoOutputStream);
            infoPrinter.println(TEST_TIME);
            infoPrinter.println(BUFF_SIZE);

            BufferedReader infoReader = new BufferedReader(new InputStreamReader(infoInputStream));

            long startTime = System.currentTimeMillis();
            while (System.currentTimeMillis() - startTime <= TEST_TIME) {
                outputStream.write(buffer);
            }
            socket.shutdownOutput();
            Log.e(TAG, "run: tcp send complete");

            int serverTotalRevSize = Integer.parseInt(infoReader.readLine());
            Log.e(TAG, "run: tcp server rec " + serverTotalRevSize);

            int clientTcpTotalRecSize = 0;
            int onceRecSize;
            startTime = System.currentTimeMillis();
            while (System.currentTimeMillis() - startTime <= TEST_TIME) {
                if ((onceRecSize = inputStream.read(buffer)) != -1) {
                    clientTcpTotalRecSize += onceRecSize;
                }else {
                    break;
                }
            }
            Log.e(TAG, "run: tcp client rec " + clientTcpTotalRecSize);

            infoPrinter.println(clientTcpTotalRecSize);

            Log.e(TAG, "run: tcp test finished" );

            TestResult result = new TestResult(Integer.toString(serverTotalRevSize),
                    Integer.toString(clientTcpTotalRecSize));
            mCallbackHandler.obtainTestResultMessage(result).sendToTarget();
        } catch (Exception e) {
            e.printStackTrace();
            mCallbackHandler.obtainFailMessage().sendToTarget();
        }

    }
}
