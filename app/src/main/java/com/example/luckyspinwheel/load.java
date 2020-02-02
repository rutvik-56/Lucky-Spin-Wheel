package com.example.luckyspinwheel;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

public class load extends AppCompatActivity {

    ImageView b;
    ListView l1;
    int x=2;
    static final String pref="main";
     String index[],name[];
    SharedPreferences sharedpreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_load);
         l1=(ListView)findViewById(R.id.list);
        b=(ImageView)findViewById(R.id.backkk);
        sharedpreferences=getSharedPreferences(pref,Context.MODE_PRIVATE);
        String ww=sharedpreferences.getString(pref,"");
        name=ww.split(",");
        if(name[0].equals(""))
        {
            x=0;
        }
        else
        {
            x=name.length;
        }

        index=new String[x];
         for(int i=0;i<x;i++)
         {
             index[i]=(i+1)+".";
         }


        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        Myadptt myadp=new Myadptt();
        l1.setAdapter(myadp);
    }


    class Myadptt extends BaseAdapter
    {
        @Override
        public int getCount() {
            return x;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {

            convertView=getLayoutInflater().inflate(R.layout.loadlayout,null);
            TextView t=(TextView) convertView.findViewById(R.id.numberr);
            TextView tt=(TextView) convertView.findViewById(R.id.loadtextt);
            ImageView io=(ImageView)convertView.findViewById(R.id.cancel);
            t.setText(index[position]+"");
            tt.setText(name[position]+"");
            tt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                            sharedpreferences=getSharedPreferences(pref,Context.MODE_PRIVATE);
                            String ww=sharedpreferences.getString(name[position],"");
                            String xx[]=ww.split(",");
                            String d=xx.length+"";
                            Intent i=new Intent(load.this,Editext.class);
                            i.putExtra("arrr",xx);
                            i.putExtra("num",d);
                            i.putExtra("va","yes");
                            startActivity(i);
                            finish();

                        }
                    });

            io.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    StringBuilder s=new StringBuilder();
                    for(int i=0;i<x;i++)
                    {
                        if(position!=i) {
                            s.append(name[i]);
                            s.append(",");
                        }

                    }
                    SharedPreferences.Editor editor=sharedpreferences.edit();
                    editor.putString(pref,s.toString()+"");
                    editor.commit();
                    finish();
                    overridePendingTransition( 0, 0);
                    startActivity(getIntent());
                    overridePendingTransition( 0, 0);
                }
            });
            return convertView;
        }
    }


}
