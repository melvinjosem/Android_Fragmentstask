package com.example.myself.fragmentstask;


import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by Rooman on 6/22/2018.
 */

public class Myservice extends Service {
    public Myservice(){

    }

    private final IBinder binder=new MyLocalBinder();

    @Override
    public IBinder onBind(Intent intent) {
        return binder;
    }

    public class MyLocalBinder extends Binder {
         Myservice getService(){
            return Myservice.this;
        }
    }
    public Date getCurrentDate() {
        return Calendar.getInstance().getTime();
    }
}
