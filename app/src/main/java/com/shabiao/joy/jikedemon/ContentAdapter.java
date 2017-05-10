package com.shabiao.joy.jikedemon;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by joy on 2017/2/3.
 */

public class ContentAdapter extends RecyclerView.Adapter {
    MainActivity mainActivity;
    ArrayList<String> list;

    public ContentAdapter(MainActivity mainActivity, ArrayList<String> list) {
        this.mainActivity = mainActivity;
        this.list = list;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
//        String string = list.get(position);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        MyViewHolder myViewHolder = new MyViewHolder(LayoutInflater.from(mainActivity).inflate(R.layout.item,parent,false));
        return myViewHolder;
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView tv;

        public MyViewHolder(View view) {
            super(view);
//            tv = (TextView) view.findViewById(R.id.id_num);
        }
    }

    public ArrayList<String> getAdapterList() {
        return list;
    }
}
