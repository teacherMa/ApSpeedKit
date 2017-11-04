package com.example.teacherma.apspeedtest.model.local;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.example.teacherma.apspeedtest.App;
import com.example.teacherma.apspeedtest.api.OnResultCallback;
import com.example.teacherma.apspeedtest.model.MainDataSource;
import com.example.teacherma.apspeedtest.model.bean.TestResult;
import com.example.teacherma.apspeedtest.utils.Constants;

import java.util.ArrayList;
import java.util.List;

public class MainLocalDS implements MainDataSource {
    private MainLocalDS() {
    }

    public static MainLocalDS getInstance() {
        return MainLocalDS.SingletonHolder.INSTANCE;
    }

    @Override
    public void getTestHistory(OnResultCallback<List<TestResult>> callback) {
        ArrayList<TestResult> results = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            results.add(new TestResult());
        }
        callback.onSuccess(results, Constants.ResultCode.LOCAL);
    }

    @Override
    public void requestBuildNewTest(OnResultCallback<Integer> callback) {
        try {
            ConnectivityManager connectivityManager = (ConnectivityManager) App.getAppContext()
                    .getSystemService(Context.CONNECTIVITY_SERVICE);
            if (connectivityManager == null) {
                callback.onFail(Constants.NetWorkError.ACCESS_NETWORK_FAILED);
                return;
            }
            NetworkInfo networkInfo = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
            if (NetworkInfo.State.CONNECTED != networkInfo.getState()) {
                callback.onFail(Constants.NetWorkError.WIFI_CONNECTION_ERROR);
                return;
            }
            callback.onSuccess(Constants.BuildNewTestResult.SUCCESS, Constants.ResultCode.LOCAL);
        } catch (Exception e) {
            callback.onFail(Constants.NetWorkError.UNKNOWN_ERROR);
        }

    }

    @Override
    public void testSpeed(OnResultCallback<TestResult> callback, String ip, String port) {

    }

    private static class SingletonHolder {
        private static final MainLocalDS INSTANCE = new MainLocalDS();
    }
}
