package com.example.teacherma.apspeedtest.model;

import com.example.teacherma.apspeedtest.api.OnResultCallback;
import com.example.teacherma.apspeedtest.model.bean.HistoryResult;

import java.util.List;

public interface MainDataSource {

    public void getTestHistory(OnResultCallback<List<HistoryResult>> callback);

}
