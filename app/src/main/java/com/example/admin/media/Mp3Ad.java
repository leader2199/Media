package com.example.admin.media;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class Mp3Ad extends ArrayAdapter<Mp3> {
    Context context;
    int res;
    ArrayList<Mp3> arr;
    public Mp3Ad(@NonNull Context context, int resource, @NonNull ArrayList<Mp3> objects) {
        super(context, resource, objects);
        this.context=context;
        this.arr=objects;
        this.res=resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        viewHolder viewHolder = new viewHolder();
        if(convertView==null){
            convertView= LayoutInflater.from(context).inflate(R.layout.lview_layout,parent,false);
            viewHolder.texName=convertView.findViewById(R.id.texName);
            viewHolder.texArt=convertView.findViewById(R.id.texArt);
            viewHolder.img=convertView.findViewById(R.id.imageView);
            convertView.setTag(viewHolder);
        }else {
            viewHolder= (Mp3Ad.viewHolder) convertView.getTag();
        }
        Mp3 mp3 = arr.get(position);
        viewHolder.texName.setText(mp3.getName());
        viewHolder.texArt.setText(mp3.getArtist());
        return convertView;
    }

    public class viewHolder{
        TextView texName,texArt;
        ImageView img;
    }
}
