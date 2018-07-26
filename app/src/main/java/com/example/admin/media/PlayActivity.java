package com.example.admin.media;

import android.media.MediaPlayer;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

public class PlayActivity extends AppCompatActivity implements View.OnClickListener {
    TextView texName2,texArt2,texSta,texTotal;
    SeekBar sb;
    Timer tm;
    ImageView img,imgPlay,imgPre,imgNext,imgVol;
    Data data = new Data();
    ArrayList<Mp3> arr;
    MediaPlayer mp = new MediaPlayer();
    tmTask tmTask;
    int i,lent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play2);
        i= getIntent().getIntExtra("id",2);
        setTex();
        player(i);
        setTouch();


    }



    private void setTex(){
        texName2=this.findViewById(R.id.texName2);
        texArt2=this.findViewById(R.id.texArt2);
        img=this.findViewById(R.id.imageView);
        imgPlay=this.findViewById(R.id.imgPlay2);
        imgPre=this.findViewById(R.id.imgPre);
        imgNext=this.findViewById(R.id.imgNext);
        imgVol=this.findViewById(R.id.imgVol);
        texSta=this.findViewById(R.id.texSta);
        texTotal=this.findViewById(R.id.texTotal);
        sb=this.findViewById(R.id.seekBar);

    }

    private void setTouch(){
        imgPlay.setOnClickListener(this);
        imgPre.setOnClickListener(this);
        imgNext.setOnClickListener(this);
        imgVol.setOnClickListener(this);
    }

    private void rotate(){
        RotateAnimation rotateAnimation = new RotateAnimation(0,360,RotateAnimation.RELATIVE_TO_SELF,0.5F,RotateAnimation.RELATIVE_TO_SELF,0.5F);
        rotateAnimation.setDuration(5000);
        rotateAnimation.setInterpolator(new LinearInterpolator());
        rotateAnimation.setRepeatCount(Animation.INFINITE);
        img.startAnimation(rotateAnimation);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case (R.id.imgPlay2):
                start();
                break;
            case (R.id.imgNext):
                nex();
                break;
            case (R.id.imgPre):
                tm.cancel();
                if(i==0){
                    i=data.mp3s.length-1;
                    player(i);
                    break;
                }else {
                    i=i-1;
                    player(i);
                    break;
                }

            case (R.id.imgVol):

        }
    }

    private void start(){
        if(mp.isPlaying()){
            mp.pause();
            tmTask.cancel();
            imgPlay.setImageResource(R.drawable.play);
            lent=mp.getCurrentPosition();

        }else {
            mp.seekTo(lent);
            tmTask = new tmTask();
            tm.scheduleAtFixedRate(tmTask,0,1);

            imgPlay.setImageResource(R.drawable.pause);
            mp.start();

        }
    }

    private void player(int pos){
        mp.release();
        prepare(pos);
        texName2.setText(data.mp3s[pos].getName());
        texArt2.setText(data.mp3s[pos].getArtist());

    }

    private void prepare(int id){
        mp=MediaPlayer.create(this,data.mp3s[id].getId());
        mp.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mediaPlayer) {
                mp.start();
                int i = mp.getDuration();
                staTotal(texTotal,i);
                sb.setMax(i);
                sb.setProgress(0);
                sb.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                    @Override
                    public void onProgressChanged(SeekBar seekBar, int i, boolean b) {

                    }

                    @Override
                    public void onStartTrackingTouch(SeekBar seekBar) {

                    }

                    @Override
                    public void onStopTrackingTouch(SeekBar seekBar) {
                        mp.seekTo(sb.getProgress());
                    }
                });

                tm = new Timer();
                tmTask = new tmTask();
                tm.scheduleAtFixedRate(tmTask,0,1);

            }
        });

    }

    private void staTotal(TextView tv,int i){
        int p = (int) TimeUnit.MILLISECONDS.toMinutes(i);
        int s= i/1000-p*60;
        tv.setText(p+":"+s);
    }

    private class tmTask extends TimerTask{

        @Override
        public void run() {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    sb.incrementProgressBy(1);
                    int i = sb.getProgress();
                    staTotal(texSta,i);
                    if(sb.getProgress()==sb.getMax()){
                        nex();
                    }
                }
            });
        }
    }

    private void nex(){
        tm.cancel();
        if(i==data.mp3s.length-1){
            i=0;
            player(i);
            return;
        }else {
            i=i+1;
            player(i);
            return;
        }
    }

}