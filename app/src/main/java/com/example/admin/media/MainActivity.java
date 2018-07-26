package com.example.admin.media;

import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Collections;

public class MainActivity extends AppCompatActivity  {
    ImageView imgPlay,imgNext,imgPre,imgVol;
    ArrayList<Mp3> arr = new ArrayList<>();
    ListView lview;
    Mp3Ad mp3Ad;
    Data data = new Data();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lview=this.findViewById(R.id.lview);

        Collections.addAll(arr,data.mp3s);
        init();
        lview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Intent intent = new Intent(MainActivity.this,PlayActivity.class);

                intent.putExtra("id",i);
                startActivity(intent);
            }
        });

    }


    private void init(){

        mp3Ad = new Mp3Ad(this,R.layout.lview_layout,arr);
        lview.setAdapter(mp3Ad);
        mp3Ad.notifyDataSetChanged();


    }

}
