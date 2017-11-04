package com.example.teacherma.apspeedtest.future.main;

import com.example.teacherma.apspeedtest.framework.BasePresenterApi;
import com.example.teacherma.apspeedtest.framework.BaseViewApi;
import com.example.teacherma.apspeedtest.model.bean.TestResult;

import java.util.List;

public class MainContract {

    interface View extends BaseViewApi<Presenter> {
        void loadingHistory(List<TestResult> historyResults);

        void onRequestNewTestResult(int resultCode);

        void testResult(TestResult result);
    }

    interface Presenter extends BasePresenterApi {
        void requestBuildNewTest();

        void testSpeed(String ip, String port);
    }
}
