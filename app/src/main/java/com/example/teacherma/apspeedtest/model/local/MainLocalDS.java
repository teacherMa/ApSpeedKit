package com.example.teacherma.apspeedtest.model.local;

import com.example.teacherma.apspeedtest.api.OnResultCallback;
import com.example.teacherma.apspeedtest.model.MainDataSource;
import com.example.teacherma.apspeedtest.model.bean.HistoryResult;
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
    public void getTestHistory(OnResultCallback<List<HistoryResult>> callback) {
        ArrayList<HistoryResult> results = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            results.add(new HistoryResult());
        }
        callback.onSuccess(results, Constants.ResultCode.LOCAL);
    }

    private static class SingletonHolder {
        private static final MainLocalDS INSTANCE = new MainLocalDS();
    }
}
