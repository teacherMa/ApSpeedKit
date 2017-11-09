package com.example.teacherma.apspeedtest.future.help;

import android.support.annotation.NonNull;

import com.example.teacherma.apspeedtest.framework.BasePresenter;
import com.example.teacherma.apspeedtest.framework.BasePresenterApi;
import com.example.teacherma.apspeedtest.model.MainRepository;

public class HelpPresenter extends BasePresenter<HelpContract.View, MainRepository> implements HelpContract.Presenter {

    public HelpPresenter(@NonNull MainRepository repository, @NonNull HelpContract.View view) {
        super(repository, view);
    }

    @Override
    public void start() {

    }
}
