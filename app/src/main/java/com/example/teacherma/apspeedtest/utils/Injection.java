package com.example.teacherma.apspeedtest.utils;

import com.example.teacherma.apspeedtest.model.MainRepository;
import com.example.teacherma.apspeedtest.model.local.MainLocalDS;
import com.example.teacherma.apspeedtest.model.remote.MainRemoteDS;

/**
 * Inject repository .
 * <p>
 */

public class Injection {
    public static MainRepository provideMainRepository() {
        return MainRepository.getInstance(MainLocalDS.getInstance(), MainRemoteDS.getInstance());
    }
}
