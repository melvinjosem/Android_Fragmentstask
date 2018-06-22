package com.example.myself.fragmentstask;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.util.Date;

public class MyServiceclass extends AppCompatActivity {
    Myservice myservice;
    private boolean isBound = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_serviceclass);
    }


    public void dispalyDate(View v) {
        if (isBound) {
            Date date = myservice.getCurrentDate();
            Toast.makeText(this, String.valueOf(date), Toast.LENGTH_SHORT).show();
        }
    }


    private ServiceConnection serviceConnection=new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {

            Myservice.MyLocalBinder binder=(Myservice.MyLocalBinder)iBinder;
            myservice=binder.getService();
            isBound=true;
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {

            isBound=false;
        }
    };


    @Override
    protected void onStart() {
        super.onStart();

        Intent intent=new Intent(MyServiceclass.this,Myservice.class);
        bindService(intent,serviceConnection, Context.BIND_AUTO_CREATE);

    }

    @Override
    protected void onStop() {
        super.onStop();
        if (isBound) {
            unbindService(serviceConnection);
            isBound = false;
        }
    }
}
