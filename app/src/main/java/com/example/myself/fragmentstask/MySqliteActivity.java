package com.example.myself.fragmentstask;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MySqliteActivity extends AppCompatActivity {
Button save,display,delete,update;
    EditText name,designation,oldname,newname;
    TextView result;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_sqlite);

        save= (Button) findViewById(R.id.btnsave);
        display= (Button) findViewById(R.id.btnDisplay);
        delete= (Button) findViewById(R.id.btndelete);
        update= (Button) findViewById(R.id.btnupdate);

        name= (EditText) findViewById(R.id.etname);
        designation= (EditText) findViewById(R.id.etdesignation);
        oldname= (EditText) findViewById(R.id.etold);
        newname= (EditText) findViewById(R.id.etnew);

        result= (TextView) findViewById(R.id.tvresult);

        final DbAdapter dbAdapter=new DbAdapter(this);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                long id=dbAdapter.insertData(name.getText().toString().trim(),designation.getText().toString().trim());
                if (id==-1){
                    Toast.makeText(MySqliteActivity.this, "Error cannot save", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(MySqliteActivity.this, "Success data saved", Toast.LENGTH_SHORT).show();
                }
            }
        });


        display.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                result.setText(dbAdapter.getData());
            }
        });



        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int id=dbAdapter.delete(name.getText().toString().trim());
                if (id<=0){
                    Toast.makeText(MySqliteActivity.this, "Error cannot Delete", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(MySqliteActivity.this, "Success data deleted", Toast.LENGTH_SHORT).show();
                }
            }
        });

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int id=dbAdapter.updateName(oldname.getText().toString(),newname.getText().toString());
                if (id<=0){
                    Toast.makeText(MySqliteActivity.this, "Error cannot update", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(MySqliteActivity.this, "Success data updated", Toast.LENGTH_SHORT).show();
                }
            }
        });



    }
}
