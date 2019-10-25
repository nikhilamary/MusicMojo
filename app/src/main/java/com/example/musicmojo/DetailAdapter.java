package com.example.musicmojo;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class DetailAdapter extends ArrayAdapter<DetailModel> {

    private Activity context;
    List<DetailModel> details;

    public DetailAdapter(Activity context, List<DetailModel> details){
        super(context, R.layout.detail, details);

        this.context = context;
        this.details = details;
    }

    public View getView(int position, View convertView, ViewGroup parent) {

        View view = convertView;
        if(view == null) {
            LayoutInflater inflater = context.getLayoutInflater();
            view = inflater.inflate(R.layout.detail,null, true);
        }

        DetailModel detailModel = getItem(position);

        if (detailModel != null){
            TextView time = view.findViewById(R.id.time);
            TextView date = view.findViewById(R.id.date);
            TextView location = view.findViewById(R.id.location);

            time.setText(detailModel.getTime());
            date.setText(detailModel.getDate());
            location.setText(detailModel.getLocation());
        }
        return view;
    }
}
