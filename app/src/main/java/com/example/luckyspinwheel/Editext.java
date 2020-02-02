package com.example.luckyspinwheel;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Handler;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AbsListView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.Arrays;

public class Editext extends AppCompatActivity {

    String first="first";
    ListView l1;
    LinearLayout ma;
    int er;
    StringBuilder sos;
    ImageView i1;
    EditText etComments;
    static final String pref="main";
    String appnvali="";
    Intent ii;
    EditText ee[];
    String x[],y[];
    int po[];
    StringBuilder stringBuilder;
    SharedPreferences sharedpreferences;
    int n;
    String arr[],second[];

    Button ok,save,load;
    String va;

    protected void onPause() {
        first="first";
        super.onPause();
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editext);
        ii=getIntent();
        ma=(LinearLayout)findViewById(R.id.ma);
        final String a=ii.getStringExtra("num");
        va=ii.getStringExtra("va");

        if(va.equals("yes"))
        {
            second=ii.getStringArrayExtra("arrr");
        }

        n=Integer.parseInt(a);
        l1=(ListView)findViewById(R.id.listview);
        i1=(ImageView)findViewById(R.id.imagev);
        l1.setScrollContainer(false);
        ok=(Button)findViewById(R.id.ok);
        x=new String[n];
        y=new String[n];
        Arrays.fill(y,"");
        po=new int[n];
        arr=new String[n];
        ee=new EditText[n];
        for(int i=0;i<n;i++)
        {
            x[i]=(i+1)+".";
        }


        load=(Button)findViewById(R.id.load);
        save=(Button)findViewById(R.id.save);


        ma.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                hide(v);
                return false;
            }
        });

        l1.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                hide(v);
                return false;
            }
        });

        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                er = 0;
                if(va.equals("yes"))
                {
                    for (int i = 0; i < n; i++) {

                        if (!(second[i].equals(""))) {
                            if (second[i].length() <= 10) {
                                arr[i] = second[i];
                            } else {
                                er = 1;
                                ee[i].setError("Length Limit Increase");
                                Toast.makeText(Editext.this, "Please Enter Valid Length Value", Toast.LENGTH_LONG).show();
                                break;
                            }

                        } else {
                            er = 1;
                            ee[i].setError("Enter Value");
                            Toast.makeText(Editext.this, "Please Enter Empty Value", Toast.LENGTH_LONG).show();
                            break;
                        }
                    }
                }
                else
                {
                    for (int i = 0; i < n; i++) {

                        if (!(ee[i].getText().toString()).equals("")) {
                            if (ee[i].getText().toString().length() <= 10) {
                                arr[i] = ee[i].getText().toString();
                            } else {
                                er = 1;
                                ee[i].setError("Length Limit Increase");
                                Toast.makeText(Editext.this, "Please Enter Valid Length Value", Toast.LENGTH_LONG).show();
                                break;
                            }

                        } else {
                            er = 1;
                            ee[i].setError("Enter Value");
                            Toast.makeText(Editext.this, "Please Enter Empty Value", Toast.LENGTH_LONG).show();
                            break;
                        }
                    }
                }


                if (er == 0) {
                    final View view = getLayoutInflater().inflate(R.layout.menu, null);
                    final AlertDialog alertDialog = new AlertDialog.Builder(Editext.this).create();
                    alertDialog.setCancelable(false);
                    final TextView etComments = (TextView) view.findViewById(R.id.tttttt);
                    final Button ok=(Button)view.findViewById(R.id.ok);
                    final Button cancel=(Button)view.findViewById(R.id.cancel);
                    RadioButton r1=(RadioButton)view.findViewById(R.id.r1);
                    RadioButton r2=(RadioButton)view.findViewById(R.id.r2);
                    alertDialog.setView(view);
                    alertDialog.show();


                    ok.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            Intent i=new Intent(Editext.this,wheel.class);
                            i.putExtra("arrr",arr);
                            i.putExtra("num",a);

                            if(!(first.equals("first")))
                            {
                                i.putExtra("x","ok");
                            }
                            else
                            {
                                i.putExtra("x","not");
                            }
                            alertDialog.dismiss();
                            startActivity(i);

                        }
                    });

                    cancel.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            alertDialog.dismiss();
                        }
                    });


                }



            }
        });


        load.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent iop=new Intent(Editext.this,load.class);
                startActivity(iop);
            }
        });


        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                sos=new StringBuilder();
                er = 0;

                if(va.equals("yes"))
                {
                    for (int i = 0; i < n; i++) {

                        if (!(second[i].equals(""))) {
                            if (second[i].length() <= 10) {
                                arr[i] = second[i];
                                sos.append(second[i]);
                                sos.append(",");
                            } else {
                                er = 1;
                                ee[i].setError("Length Limit Increase");
                                Toast.makeText(Editext.this, "Please Enter Valid Length Value", Toast.LENGTH_LONG).show();
                                break;
                            }

                        } else {
                            er = 1;
                            ee[i].setError("Enter Value");
                            Toast.makeText(Editext.this, "Please Enter Empty Value", Toast.LENGTH_LONG).show();
                            break;
                        }
                    }
                }
                else
                {
                    for (int i = 0; i < n; i++) {
                        if (!(ee[i].getText().toString()).equals("")) {
                            if (ee[i].getText().toString().length() <= 10) {
                                sos.append(ee[i].getText().toString());
                                sos.append(",");
                            } else {
                                er = 1;
                                ee[i].setError("Length Limit Increase");
                                Toast.makeText(Editext.this, "Please Enter Valid Length Value", Toast.LENGTH_LONG).show();
                                break;
                            }

                        } else {
                            er = 1;
                            ee[i].setError("Enter Value");
                            Toast.makeText(Editext.this, "Please Enter Empty Value", Toast.LENGTH_LONG).show();
                            break;
                        }
                    }
                }



                if (er == 0) {

                    final View view = getLayoutInflater().inflate(R.layout.edittedilog, null);
                    final AlertDialog alertDialog = new AlertDialog.Builder(Editext.this).create();
                    final Button b1=(Button)view.findViewById(R.id.positive);
                    final Button b2=(Button)view.findViewById(R.id.negative);
                    alertDialog.setCancelable(false);
                    etComments = (EditText) view.findViewById(R.id.etComments);
                    alertDialog.setView(view);
                    alertDialog.show();

                    b1.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            appnvali=etComments.getText().toString();
                            if(appnvali.equals(""))
                            {
                                etComments.setError("Enter Value");
                            }
                            else
                            {
                                sharedpreferences=getSharedPreferences(pref,Context.MODE_PRIVATE);
                                String ww=sharedpreferences.getString(pref,"");
                                StringBuilder s=new StringBuilder(ww);
                                if(!ww.equals("")) {
                                    s.append(",");
                                }
                                s.append(appnvali+"");

                                SharedPreferences.Editor editor=sharedpreferences.edit();
                                editor.putString(appnvali+"",sos.toString()+"");
                                editor.commit();

                                SharedPreferences.Editor editor1=sharedpreferences.edit();
                                editor1.putString(pref,s.toString()+"");
                                editor1.commit();
                                Toast.makeText(Editext.this,"Saved",Toast.LENGTH_SHORT).show();
                                alertDialog.dismiss();

                            }
                        }
                    });


                    b2.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            alertDialog.dismiss();
                        }
                    });

                }


            }
        });

        i1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        Myadp myadp=new Myadp();
        l1.setAdapter(myadp);
    }



    public void onRadioButtonClicked(View view) {

        boolean checked = ((RadioButton)view).isChecked();

        switch(view.getId()) {
            case R.id.r1:
                if (checked)
                    first="first";
                break;

            case R.id.r2:
                if (checked)
                    first="second";
                break;
        }
    }

    void hide(View view)
    {
        InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    class Myadp extends BaseAdapter
    {

        @Override
        public int getCount() {
            return n;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {


            if (convertView == null) {
                convertView = getLayoutInflater().inflate(R.layout.listsee,null,false);
            }
            convertView=getLayoutInflater().inflate(R.layout.listsee,null);
            TextView t=(TextView) convertView.findViewById(R.id.ttt);
            LinearLayout lll=(LinearLayout)convertView.findViewById(R.id.maa);

            lll.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    hide(v);
                    return false;
                }
            });


            ee[position]=(EditText)convertView.findViewById(R.id.eee);
            t.setText(x[position]+"");
            if(va.equals("yes"))
            {
                ee[position].setText(second[position]+"");
            }
            else
            {
                if(po[position]==0) {
                    ee[position].setHint((position+1)+"");
                    po[position]++;
                }
                else
                {
                    if(y[position].equals(""))
                    {
                        ee[position].setHint((position+1)+"");
                    }
                    else {
                        ee[position].setText(y[position] + "");
                    }
                }
            }

            ee[position].addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {

                }

                @Override
                public void afterTextChanged(Editable s) {
                    if(va.equals("yes"))
                    {
                        second[position]=ee[position].getText().toString();
                    }
                    else {
                        y[position] = ee[position].getText().toString();
                    }
                }
            });


            return convertView;

        }
    }
}

