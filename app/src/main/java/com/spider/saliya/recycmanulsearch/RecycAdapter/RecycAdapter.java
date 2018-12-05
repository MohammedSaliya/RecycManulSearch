package com.spider.saliya.recycmanulsearch.RecycAdapter;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.TextView;

import com.spider.saliya.recycmanulsearch.MainActivity;
import com.spider.saliya.recycmanulsearch.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class RecycAdapter extends RecyclerView.Adapter<RecycAdapter.MyViewHolder> {

    ArrayList<String> arrayList;
    MainActivity activity;

    public RecycAdapter(MainActivity mainActivity, ArrayList<String> arrayList) {
        this.arrayList = arrayList;
        this.activity = activity;
    }

    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.activity_recyc, viewGroup, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        myViewHolder.name.setText(arrayList.get(i));

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public void filterList(ArrayList<String> filterdNames) {

        this.arrayList = filterdNames;
        notifyDataSetChanged();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView name;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.name);

        }
    }


}