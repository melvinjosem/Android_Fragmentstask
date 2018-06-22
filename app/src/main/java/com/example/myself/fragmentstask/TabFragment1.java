package com.example.myself.fragmentstask;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;


/**
 * A simple {@link Fragment} subclass.
 */
public class TabFragment1 extends Fragment {

    SendData mydata;

    public TabFragment1() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
         View view=inflater.inflate(R.layout.fragment_tab_fragment1, container, false);
        Button button=view.findViewById(R.id.btnsend);
        final EditText editText=view.findViewById(R.id.et);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String text=editText.getText().toString();
                mydata.senddetails(text);
            }
        });

        return view;

    }
    interface SendData {
        void senddetails(String message);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try{
        mydata=(SendData)getActivity();}
        catch (ClassCastException e){

        }
    }
}
