package com.example.teacherma.apspeedtest.future.main;

import android.content.Context;
import android.view.ViewGroup;

import com.example.teacherma.apspeedtest.framework.BaseAdapter;
import com.example.teacherma.apspeedtest.framework.BaseViewHolder;
import com.example.teacherma.apspeedtest.model.bean.HistoryResult;

public class MainAdapter extends BaseAdapter<HistoryResult> {
    @Override
    protected BaseViewHolder createViewHolder(Context context, ViewGroup parent) {
        return new MainVH(context, parent);
    }
}
