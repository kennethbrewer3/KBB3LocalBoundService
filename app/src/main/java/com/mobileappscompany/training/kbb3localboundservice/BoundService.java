package com.mobileappscompany.training.kbb3localboundservice;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class BoundService extends Service {

    private final IBinder kbb3Binder = new KBB3Binder();

    public BoundService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        return kbb3Binder;
    }

    public String getCurrentTime() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss MM/dd/yyyy",Locale.US);

        return (dateFormat.format(new Date()));
    }

    public class KBB3Binder extends Binder {
        BoundService getService() {
            return BoundService.this;
        }
    }
}
