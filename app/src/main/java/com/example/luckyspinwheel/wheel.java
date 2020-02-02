package com.example.luckyspinwheel;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Build;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.prefs.PreferenceChangeEvent;

import github.hellocsl.cursorwheel.CursorWheelLayout;

public class wheel extends AppCompatActivity implements
        Animation.AnimationListener,
        CursorWheelLayout.OnMenuSelectedListener {

    boolean buttonrotation=true;
    CursorWheelLayout img;
    int posss=0;
    ImageView ba;
    Spinner s1;
   float indegree=0;
    CursorWheelLayout wheel_text;
    List<MenuItemData> lstText;
    Button swing;
    float start,end;
    int possl=0;
    Intent i;
    String xn;
    MediaPlayer ringstart,ringwin;
    LinearLayout iii;
    int nn;
     String x[];




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wheel);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        s1=(Spinner)findViewById(R.id.spik);
        swing=(Button)findViewById(R.id.swing);
       i=getIntent();
       iii=(LinearLayout)findViewById(R.id.lop);
         ringstart= MediaPlayer.create(wheel.this,R.raw.collision);
        ringwin= MediaPlayer.create(wheel.this,R.raw.win);
        String s=i.getStringExtra("num");
        nn=Integer.parseInt(s);
        x=new String[nn];
        x=i.getStringArrayExtra("arrr");
        xn=i.getStringExtra("x");
        img = (CursorWheelLayout) findViewById(R.id.wheel_text);
        wheel_text=(CursorWheelLayout)findViewById(R.id.wheel_text);
        ba=(ImageView) findViewById(R.id.backkk);
        ba.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        if(xn.equals("ok"))
        {
            int height = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 70, getResources().getDisplayMetrics());
            int width = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 170, getResources().getDisplayMetrics());
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(width, height);
            iii.setLayoutParams(layoutParams);
            ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(this, R.layout.sdfg, x);
            spinnerArrayAdapter.setDropDownViewResource( R.layout.sdfg); // The drop down view
            s1.setAdapter(spinnerArrayAdapter);

            s1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    possl=position;
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
        }


        swing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(buttonrotation) {
                    float ran = new Random().nextInt(nn);
                    ran=ran+1;

                    ran = ((360 / nn) * ran) + 3600;

                    RotateAnimation rotateAnimation = new RotateAnimation((float)indegree,(float)
                            ((float)indegree + ((float)ran)),1,0.5f,1,0.5f);
                    indegree=((float) indegree + ((float)ran)) %360;
                    rotateAnimation.setDuration((long) ran);
                    rotateAnimation.setFillAfter(true);
                    rotateAnimation.setInterpolator(new DecelerateInterpolator());
                    rotateAnimation.setAnimationListener(wheel.this);
                    img.setAnimation(rotateAnimation);
                    img.startAnimation(rotateAnimation);


                }
            }
        });
        lstText=new ArrayList<MenuItemData>();

        for(int i=0;i<nn;i++) {
            lstText.add(new MenuItemData("" +  x[i]));
        }
        wheeltextadapter adapter=new wheeltextadapter(wheel.this,lstText);
        wheel_text.setAdapter(adapter);
        wheel_text.setOnMenuSelectedListener(this);

    }


    @Override
    public void onAnimationStart(Animation animation) {
        this.buttonrotation=false;
        ringstart.start();
        ringstart.setLooping(true);

    }

    @Override
    public void onAnimationEnd(Animation animation) {
        end=this.indegree;
        ringstart.pause();
        float deg=0,dd=0;
        deg=360/nn;
        dd=360/(2*nn);

        float result=0;
        if(nn==13 || nn==11 || nn==14 || nn==16 ) {
            result = (int) ((369 - end + dd) / deg) + posss;
        }
        else if(nn==19 || nn==23)
        {
            result = (int) ((360+ ((360)/nn) - end + dd) / deg) + posss;
        }
        else if(nn==21 || nn==22 || nn==25)
        {
            result = (int) ((364 - end + dd) / deg) + posss;
        }
        else
        {
            result = (int) ((360 - end + dd) / deg) + posss;
        }

        if(result>(nn-1))
        {
            result=result-nn;
        }
        ringwin.start();
        ringwin.setLooping(true);
        this.buttonrotation=true;
        if(xn.equals("ok"))
        {
            final View view = getLayoutInflater().inflate(R.layout.result, null);
            final AlertDialog alertDialog = new AlertDialog.Builder(wheel.this).create();
            alertDialog.setCancelable(false);
            final TextView etComments = (TextView) view.findViewById(R.id.tttttt);
            final Button bb=(Button)view.findViewById(R.id.iolkj);
            if(x[(int)result].equals(x[possl]))
            {
                etComments.setText("You Are Winner");
            }
            else
            {
                etComments.setText("Better Luck Next Time");
            }
            alertDialog.setView(view);
            alertDialog.show();
            bb.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ringwin.pause();
                    alertDialog.dismiss();
                }
            });
        }
        else
        {
            final View view = getLayoutInflater().inflate(R.layout.result, null);
            final AlertDialog alertDialog = new AlertDialog.Builder(wheel.this).create();
            alertDialog.setCancelable(false);
            final TextView etComments = (TextView) view.findViewById(R.id.tttttt);
            final Button bb=(Button)view.findViewById(R.id.iolkj);
            etComments.setText(x[(int)result]);
            alertDialog.setView(view);
            alertDialog.show();
            bb.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ringwin.pause();
                    alertDialog.dismiss();
                }
            });
        }

    }

    @Override
    public void onAnimationRepeat(Animation animation) {

    }


    @Override
    public void onItemSelected(CursorWheelLayout parent, View view, int pos) {

        if(parent.getId()==R.id.wheel_text)
        {
           posss=pos;
        }
    }

    public void onBackPressed()
    {
        ringstart.pause();
        ringwin.pause();
        super.onBackPressed();

    }
}
