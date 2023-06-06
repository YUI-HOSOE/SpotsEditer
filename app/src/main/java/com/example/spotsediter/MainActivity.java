package com.example.spotsediter;

import androidx.appcompat.app.AppCompatActivity;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
//動かしたかどうかを認識
//動いていれば特定の座標に一度戻るまで動かないようにする
public class MainActivity extends AppCompatActivity implements SensorEventListener {
    float start;
    float end;
    ImageView img;
    TextView txtname;
    TextView txtTitle;
    int result;
    SensorManager manager;

    TextView txtX,txtText;
    Button btntxt;
    int count;
    boolean show=true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        img = findViewById(R.id.imgView);
        txtname = findViewById(R.id.txtname);
        txtTitle=findViewById(R.id.txtTitle);
        txtX=findViewById(R.id.txtX);
        txtText=findViewById(R.id.txtText);
        btntxt=findViewById(R.id.txtmess);


        manager =(SensorManager) getSystemService(SENSOR_SERVICE);
    }
protected void onResume() {

    super.onResume();

    Sensor sensor=manager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

    manager.registerListener(
            this,sensor,
            SensorManager.SENSOR_DELAY_NORMAL
    );

}

    @Override
    protected void onPause() {
        super.onPause();
        if(manager != null){
            manager.unregisterListener(this);

        }
    }
//    @Override
//    public boolean onTouchEvent(MotionEvent event) {
//        if (event.getAction() == MotionEvent.ACTION_DOWN) {
//            start = event.getX();
//        }
//        if (event.getAction() == MotionEvent.ACTION_UP) {
//            end = event.getX();
//            int a = Cal(start, end);
//            picture(a);
//
//            //img.setImageResource();
//
//        }
//
//
//        return super.onTouchEvent(event);
//    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if(event.sensor.getType()==Sensor.TYPE_ACCELEROMETER){

            int a= Cal(event.values);
            picture(a);
        }

    }
    public int Cal(float[] values) {

        int num  =0;
if(count!=0){
    num=0;
    if(values[0]>= -4 && values[0]<=4){
        count=0;
    }

}else {
    if (values[0] > 4) {
        num = -1;
        txtText.setText("");
        show = !show;
        count++;
    } else if (values[0] < -4) {
        num = 1;
        txtText.setText("");
        show = !show;
        count++;
    } else {
        num = 0;
        count = 0;
    }
}
        txtX.setText("" + count);
        return num;

    }

    public void picture(int num) {
        int count =0;


        List<Integer> list = new ArrayList<>();
        list.add(R.drawable.hototogisu_tokugawa_ieyasu);
        list.add(R.drawable.toushougu);
        list.add(R.drawable.yama);
        list.add(R.drawable.rinnnouji);
        list.add(R.drawable.mizumi);
        list.add(R.drawable.saka);
        list.add(R.drawable.taki);
        list.add(R.drawable.yu);
        list.add(R.drawable.doubutu);
        list.add(R.drawable.park);

        String[] txt = {"", "日光東照宮", "日光二荒山", "輪王寺", "中善寺湖","いろは坂", "華厳の滝", "鬼怒太の湯",
                "宇都宮動物園", "とちのきファミリーランド"};


        result += num;

            if (result < 0) {
                result = 9;
            } else if (result > 9) {
                result = 0;
            } else {
                result += 0;

            }


            img.setImageResource(list.get(result));
            txtname.setText(txt[result]);






        if(result!=0){
            txtTitle.setText(null);

        }else{
           // Animation animation = AnimationUtils.loadAnimation(this,R.anim.animetion);

            txtTitle.setText("典雅！！\n家族で栃木旅行");
            //txtTitle.setAnimation(animation);

        }




    }



    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
//何も行わない

    }

    public void onClick(View view) {
        String[] txt=getResources().getStringArray(R.array.txtText);

if(txtText.getText()=="") {
    txtText.setText(txt[result]);

}else{
    txtText.setText("");

}
    }
}