package com.example.teacherma.apspeedtest.model;

import android.support.annotation.NonNull;

import com.example.teacherma.apspeedtest.api.OnResultCallback;
import com.example.teacherma.apspeedtest.framework.BaseRepository;
import com.example.teacherma.apspeedtest.model.bean.TestResult;

import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class MainRepository extends BaseRepository implements MainDataSource {
    private static Lock sLock = new ReentrantLock();
    private static MainRepository INSTANCE = null;
    private final MainDataSource mLocalDS;
    private final MainDataSource mRemoteDS;
    private boolean mIsFirstLoad;

    private MainRepository(@NonNull MainDataSource localDS, @NonNull MainDataSource remoteDS) {
        mLocalDS = localDS;
        mRemoteDS = remoteDS;
    }

    public static MainRepository getInstance(@NonNull MainDataSource localDS, @NonNull MainDataSource remoteDS) {
        sLock.lock();
        if (null == INSTANCE) {
            INSTANCE = new MainRepository(localDS, remoteDS);
        }
        sLock.unlock();

        return INSTANCE;
    }

    @Override
    public void getTestHistory(OnResultCallback<List<TestResult>> callback) {
        mLocalDS.getTestHistory(callback);
    }

    @Override
    public void requestBuildNewTest(OnResultCallback<Integer> callback) {
        mLocalDS.requestBuildNewTest(callback);
    }

    @Override
    public void testSpeed(OnResultCallback<TestResult> callback, String ip, String port) {
        mRemoteDS.testSpeed(callback, ip, port);
    }
}
