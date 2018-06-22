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
public class FragmentB extends Fragment {
    TextView txtData;
    MyListener mCallback;
    public FragmentB() {
        // Required empty public constructor
    }

    public interface MyListener{
        public void toactivity(String data);
        public void tofragment(String data);
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try
        {
            mCallback = (MyListener) getActivity();
        }
        catch (ClassCastException e)
        {
            throw new ClassCastException(context.toString()+ " must implement");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_b, container, false);
        txtData= (TextView) view.findViewById(R.id.tvfrag2);
        Button btnfr3= (Button) view.findViewById(R.id.btnfrag3);
        Button btnfr4= (Button) view.findViewById(R.id.btnfrag4);
        final EditText etfr2= (EditText) view.findViewById(R.id.etfrag2);
        btnfr3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCallback.tofragment(etfr2.getText().toString());
            }
        });


        btnfr4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCallback.toactivity(etfr2.getText().toString());
            }
        });

        return  view;
    }


    protected void displayReceivedData(String message)
    {
        txtData.setText("Data received: "+message);
    }

}
