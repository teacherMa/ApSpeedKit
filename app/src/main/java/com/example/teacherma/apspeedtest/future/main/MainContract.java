package com.example.teacherma.apspeedtest.future.main;

import com.example.teacherma.apspeedtest.framework.BasePresenterApi;
import com.example.teacherma.apspeedtest.framework.BaseViewApi;
import com.example.teacherma.apspeedtest.model.bean.HistoryResult;

import java.util.List;

public class MainContract {

    interface View extends BaseViewApi<Presenter> {
        void loadingHistory(List<HistoryResult> historyResults);
    }

    interface Presenter extends BasePresenterApi {

    }
}
