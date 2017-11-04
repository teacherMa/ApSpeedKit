package com.example.teacherma.apspeedtest.model.remote;

import android.os.Handler;

import com.example.teacherma.apspeedtest.api.OnResultCallback;
import com.example.teacherma.apspeedtest.model.MainDataSource;
import com.example.teacherma.apspeedtest.model.bean.TestResult;

import java.util.List;

public class MainRemoteDS implements MainDataSource {
    private static final String TAG = "MainRemoteDS";

    private MainRemoteDS() {

    }

    public static MainRemoteDS getInstance() {
        return SingletonHolder.INSTANCE;
    }

    @Override
    public void getTestHistory(OnResultCallback<List<TestResult>> callback) {

    }

    @Override
    public void requestBuildNewTest(OnResultCallback<Integer> callback) {

    }

    @Override
    public void testSpeed(OnResultCallback<TestResult> callback, final String ip, final String port) {
    }

    private static class SingletonHolder {
        private static final MainRemoteDS INSTANCE = new MainRemoteDS();
    }
}
