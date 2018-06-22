package com.example.myself.fragmentstask;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentA extends Fragment {

    DataPassListener mCallback;
    EditText message;
    TextView tv;

    public void displayReceivedData(String s) {
        tv.setText(s);
    }

    public interface DataPassListener{
        public void passDatatoactivity(String data);
        public void passDatatofragment(String data);
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try
        {
            mCallback = (DataPassListener) getActivity();
        }
        catch (ClassCastException e)
        {
            throw new ClassCastException(context.toString()+ " must implement ");
        }
    }

    public FragmentA() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_a, container, false);
        Button tofragb= (Button) view.findViewById(R.id.btnfrag1);
        Button toactivity= (Button) view.findViewById(R.id.btnfrag2);
        message= (EditText) view.findViewById(R.id.etfrag1);
        tv= (TextView) view.findViewById(R.id.tvfrag1);
        tofragb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            mCallback.passDatatofragment(message.getText().toString());
            }
        });

        toactivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCallback.passDatatoactivity(message.getText().toString());
            }
        });
        return view;
    }

}
