package com.example.teacherma.apspeedtest.future.main;

import android.support.annotation.NonNull;

import com.example.teacherma.apspeedtest.api.OnResultCallback;
import com.example.teacherma.apspeedtest.framework.BasePresenter;
import com.example.teacherma.apspeedtest.model.MainRepository;
import com.example.teacherma.apspeedtest.model.bean.TestResult;
import com.example.teacherma.apspeedtest.utils.BaseUtil;

import java.util.List;

public class MainPresenter extends BasePresenter<MainContract.View, MainRepository> implements MainContract.Presenter {

    public MainPresenter(@NonNull MainRepository repository, @NonNull MainContract.View view) {
        super(repository, view);
    }

    @Override
    public void start() {
        requestHistory();
    }

    private void requestHistory() {
        OnResultCallback<List<TestResult>> resultCallback = new OnResultCallback<List<TestResult>>() {
            @Override
            public void onSuccess(List<TestResult> resultValue, int code) {
                if (null == getView()) {
                    return;
                }
                getView().loadingHistory(resultValue);
            }

            @Override
            public void onFail(String errorMessage) {
                if (null == getView()) {
                    return;
                }
                BaseUtil.showToast(errorMessage);
            }
        };
        getRepository().getTestHistory(resultCallback);
    }

    @Override
    public void requestBuildNewTest() {
        OnResultCallback<Integer> resultCallback = new OnResultCallback<Integer>() {
            @Override
            public void onSuccess(Integer resultValue, int code) {
                if (null == getView()) {
                    return;
                }
                getView().onRequestNewTestResult(resultValue);
            }

            @Override
            public void onFail(String errorMessage) {
                if (null == getView()) {
                    return;
                }
                BaseUtil.showToast(errorMessage);
            }
        };
        getRepository().requestBuildNewTest(resultCallback);
    }

    @Override
    public void testSpeed(String ip, String port) {
        OnResultCallback<TestResult> resultCallback = new OnResultCallback<TestResult>() {
            @Override
            public void onSuccess(TestResult resultValue, int code) {
                if (null == getView()) {
                    return;
                }
                getView().testResult(resultValue);
            }

            @Override
            public void onFail(String errorMessage) {
                if (null == getView()) {
                    return;
                }
                BaseUtil.showToast(errorMessage);
            }
        };

        getRepository().testSpeed(resultCallback, ip, port);
    }
}
