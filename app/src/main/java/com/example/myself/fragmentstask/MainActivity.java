package com.example.myself.fragmentstask;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

//http://www.camposha.info/source/android-data-passing-fragment-activity-via-intent

public class MainActivity extends AppCompatActivity implements FragmentA.DataPassListener ,FragmentB.MyListener{
TextView tvactivity;
    Button btnactivitya,btnactivityb;
    EditText editText1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvactivity= (TextView) findViewById(R.id.tvactivity);
        btnactivitya= (Button) findViewById(R.id.btnactivity);
        btnactivityb= (Button) findViewById(R.id.btnactivity2);
        editText1= (EditText) findViewById(R.id.etactivity);



        btnactivityb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentB f = (FragmentB) getSupportFragmentManager().findFragmentById(R.id.fragb);
                f.displayReceivedData(editText1.getText().toString());
            }
        });

        btnactivitya.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentA f = (FragmentA) getSupportFragmentManager().findFragmentById(R.id.fraga);
                f.displayReceivedData(editText1.getText().toString());
            }
        });

        FragmentManager fragmentManager=getSupportFragmentManager();
        FragmentTransaction transaction=fragmentManager.beginTransaction();

        FragmentA fragmentA=new FragmentA();
        FragmentB fragmentB=new FragmentB();

        transaction.add(R.id.fraga,fragmentA);
        transaction.add(R.id.fragb,fragmentB);

        transaction.commit();

    }

    @Override
    public void passDatatoactivity(String data) {
        tvactivity.setText(data);
    }

    @Override
    public void passDatatofragment(String data) {
        FragmentB f = (FragmentB) getSupportFragmentManager().findFragmentById(R.id.fragb);
        f.displayReceivedData(data);
    }

    @Override
    public void toactivity(String data) {
        tvactivity.setText(data);
    }

    @Override
    public void tofragment(String data) {
        FragmentA f = (FragmentA) getSupportFragmentManager().findFragmentById(R.id.fraga);
        f.displayReceivedData(data);
    }
}
