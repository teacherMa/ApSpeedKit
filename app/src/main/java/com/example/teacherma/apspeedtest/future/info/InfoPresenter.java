package com.example.teacherma.apspeedtest.future.info;

import android.support.annotation.NonNull;

import com.example.teacherma.apspeedtest.framework.BasePresenter;
import com.example.teacherma.apspeedtest.framework.BasePresenterApi;
import com.example.teacherma.apspeedtest.model.MainRepository;

public class InfoPresenter extends BasePresenter<InfoContract.View, MainRepository> implements InfoContract.Presenter {

    public InfoPresenter(@NonNull MainRepository repository, @NonNull InfoContract.View view) {
        super(repository, view);
    }

    @Override
    public void start() {

    }
}
