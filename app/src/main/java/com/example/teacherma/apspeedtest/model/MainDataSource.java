package com.example.teacherma.apspeedtest.model;

import com.example.teacherma.apspeedtest.api.OnResultCallback;
import com.example.teacherma.apspeedtest.model.bean.TestResult;

import java.util.List;

public interface MainDataSource {

    void getTestHistory(OnResultCallback<List<TestResult>> callback);

    void requestBuildNewTest(OnResultCallback<Integer> callback);

    void testSpeed(OnResultCallback<TestResult> callback, String ip, String port);

}
