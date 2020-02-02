package com.example.luckyspinwheel;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button b1,b2;
    LinearLayout ma;
    EditText e1;
    ImageView i1,i2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        b1=(Button)findViewById(R.id.ok);
        b2=(Button)findViewById(R.id.load);
        e1=(EditText)findViewById(R.id.editttext);
        i1=(ImageView)findViewById(R.id.substract);
        i2=(ImageView)findViewById(R.id.addition);

        ma=(LinearLayout)findViewById(R.id.ma);
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent iop=new Intent(MainActivity.this,load.class);
                startActivity(iop);
            }
        });

        ma.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                hide(v);
                return false;
            }
        });

        i1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!((e1.getText().toString()).equals(""))) {
                    String x = e1.getText().toString();
                    int y = Integer.parseInt(x);
                    if (y > 3) {
                        y = y - 1;
                    }
                    e1.setText(y + "");
                }
                else
                {
                    e1.setError("Please Enter Number");
                }
            }
        });

        i2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!((e1.getText().toString()).equals(""))) {
                    String x = e1.getText().toString();
                    int y = Integer.parseInt(x);
                    if (y < 25) {
                        y = y + 1;
                    }
                    e1.setText(y + "");
                }
                else
                {
                    e1.setError("Please Enter Number");
                }
            }
        });


        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               String x=e1.getText().toString();
                if(!(x.equals("")) ) {

                    if((Integer.parseInt(e1.getText().toString())>=3 ) && (Integer.parseInt(e1.getText().toString()) <=25 ))
                    {
                        Intent i = new Intent(MainActivity.this, Editext.class);
                        i.putExtra("num",x);
                        i.putExtra("va","not");
                        startActivity(i);
                    }
                    else
                    {
                        Toast.makeText(MainActivity.this,"Please Enter Valid Number Of Option",Toast.LENGTH_SHORT).show();
                    }

                }
                else
                {
                    e1.setError("Please Enter Number");
                }
            }
        });
    }


    void hide(View view)
    {
        InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }
}
