package com.syt.phanmemkhaosat;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

/**
 * Created by hung-pc on 5/17/2017.
 */

public class MyFirebaseMessagingService extends Service
{
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
