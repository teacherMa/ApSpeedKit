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
    private static final String INFO_PORT = "12346";
//    private static final String CLIENT_UDP_PORT = "23457";

    private static final int TEST_TIME = 5000;
    private static final int BUFF_SIZE = 1024;
//    private static final int UDP_TIME_OUT = 1000;
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
//        int serverUdpPort = (int) (10000 + Math.random() * 55535);
        try {
            Socket socket = new Socket(mIp, Integer.parseInt(mPort));
            Socket infoSocket = new Socket(mIp, Integer.parseInt(INFO_PORT));

            //Stream used to test speed between client and server
            OutputStream outputStream = socket.getOutputStream();
            InputStream inputStream = socket.getInputStream();

            //Stream used to transport information between client and server
            OutputStream infoOutputStream = infoSocket.getOutputStream();
            InputStream infoInputStream = infoSocket.getInputStream();

            PrintStream infoPrinter = new PrintStream(infoOutputStream);
            infoPrinter.println(TEST_TIME);
            infoPrinter.println(BUFF_SIZE);
//            infoPrinter.println(UDP_TIME_OUT);
//            infoPrinter.println(serverUdpPort);
//            infoPrinter.println(CLIENT_UDP_PORT);

            BufferedReader infoReader = new BufferedReader(new InputStreamReader(infoInputStream));

            Log.e(TAG, "run: tcp test start");

            Log.e(TAG, "run: tcp send start");

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

            Log.e(TAG, "run: tcp rec start");

            startTime = System.currentTimeMillis();
            while (System.currentTimeMillis() - startTime <= TEST_TIME) {
                if ((onceRecSize = inputStream.read(buffer)) != -1) {
                    clientTcpTotalRecSize += onceRecSize;
                } else {
                    break;
                }
            }
            Log.e(TAG, "run: tcp rec complete");

            Log.e(TAG, "run: tcp client rec " + clientTcpTotalRecSize);

            infoPrinter.println(clientTcpTotalRecSize);

//            InetAddress inetAddress = socket.getInetAddress();

            socket.close();

            Log.e(TAG, "run: tcp test finished\n");

//            DatagramSocket udpSocket = new DatagramSocket(Integer.parseInt(CLIENT_UDP_PORT));
//
//            DatagramPacket udpPacket = new DatagramPacket(buffer, 0, BUFF_SIZE, inetAddress,
//                    serverUdpPort);
//
//            Log.e(TAG, "run: udp test start");
//
//            int udpServerTotalRecSize;
//            int udpClientTotalRecSize = 0;
//
//            Log.e(TAG, "run: udp send start");
//
//            startTime = System.currentTimeMillis();
//            while (System.currentTimeMillis() - startTime <= TEST_TIME) {
//                udpSocket.send(udpPacket);
//            }
//            Log.e(TAG, "run: udp send complete");
//
//            udpServerTotalRecSize = Integer.parseInt(infoReader.readLine());
//
//            Log.e(TAG, "run: udp server rec " + udpServerTotalRecSize);
//
//            Log.e(TAG, "run: udp rec start");
//
//            startTime = System.currentTimeMillis();
//            udpSocket.setSoTimeout(UDP_TIME_OUT + TEST_TIME);
//            try {
//                while (System.currentTimeMillis() - startTime <= TEST_TIME) {
//                    udpSocket.receive(udpPacket);
//                    udpClientTotalRecSize += udpPacket.getData().length;
//                }
//            } catch (SocketTimeoutException ignored) {
//
//            }
//
//            Log.e(TAG, "run: udp client rec " + udpClientTotalRecSize);
//
//            infoPrinter.println(udpClientTotalRecSize);
//
//            infoSocket.close();
//            udpSocket.close();

            float upSpeed = serverTotalRevSize/1024.0f/1024/TEST_TIME*1000;
            float downSpeed = clientTcpTotalRecSize/1024.0f/1024/TEST_TIME*1000;
            TestResult result = new TestResult(upSpeed, downSpeed);
            result.setIp(mIp);
            result.setPort(mPort);

            mCallbackHandler.obtainTestResultMessage(result).sendToTarget();
        } catch (Exception e) {
            e.printStackTrace();
            mCallbackHandler.obtainFailMessage().sendToTarget();
        }

    }
}
