package com.ming.sister.video;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.ming.sister.R;

import java.util.ArrayList;

public class MyAdapter extends ArrayAdapter<PeopleItem> {
    public MyAdapter(@NonNull Context context, int resource, @NonNull ArrayList<PeopleItem> objects) {
        super(context, resource, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = View.inflate(getContext(), R.layout.item_people, null);

        TextView tv_name = (TextView)view.findViewById(R.id.tv_name);
        ImageView iv_head = (ImageView)view.findViewById(R.id.iv_head);

        PeopleItem people = getItem(position);

        tv_name.setText(people.getName());
        iv_head.setImageResource(people.getPictureId());

        return view;
    }
}
