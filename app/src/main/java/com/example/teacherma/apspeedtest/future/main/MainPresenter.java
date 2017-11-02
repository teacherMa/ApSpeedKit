package com.example.teacherma.apspeedtest.future.main;

import android.support.annotation.NonNull;

import com.example.teacherma.apspeedtest.api.OnResultCallback;
import com.example.teacherma.apspeedtest.framework.BasePresenter;
import com.example.teacherma.apspeedtest.framework.BasePresenterApi;
import com.example.teacherma.apspeedtest.model.MainRepository;
import com.example.teacherma.apspeedtest.model.bean.HistoryResult;
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

    private void requestHistory(){
        OnResultCallback<List<HistoryResult>> resultCallback = new OnResultCallback<List<HistoryResult>>() {
            @Override
            public void onSuccess(List<HistoryResult> resultValue, int code) {
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
}
