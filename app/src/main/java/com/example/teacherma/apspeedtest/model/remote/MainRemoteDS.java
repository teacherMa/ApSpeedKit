package com.example.teacherma.apspeedtest.model.remote;

import com.example.teacherma.apspeedtest.api.OnResultCallback;
import com.example.teacherma.apspeedtest.model.MainDataSource;
import com.example.teacherma.apspeedtest.model.bean.HistoryResult;

import java.util.List;

public class MainRemoteDS implements MainDataSource {
    private MainRemoteDS() {
    }

    public static MainRemoteDS getInstance() {
        return SingletonHolder.INSTANCE;
    }

    @Override
    public void getTestHistory(OnResultCallback<List<HistoryResult>> callback) {

    }

    private static class SingletonHolder {
        private static final MainRemoteDS INSTANCE = new MainRemoteDS();
    }
}
