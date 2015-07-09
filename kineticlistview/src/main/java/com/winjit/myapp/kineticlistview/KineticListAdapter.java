package com.winjit.myapp.kineticlistview;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.Random;

/**
 * Created by AmrutB on 30-06-2015.
 */
public class KineticListAdapter extends BaseAdapter {

    Context context;
    LayoutInflater layoutInflater;

    public KineticListAdapter(Context context) {
        this.context = context;
        layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return 50;
    }

    @Override
    public String getItem(int position) {
        return context.getString(R.string.app_name) + ", Item " + position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.list_item, parent, false);
        }
        final TextView textView = (TextView) convertView.findViewById(R.id.textView);
        textView.setText(getItem(position));
        final LinearLayout layout = (LinearLayout) convertView.findViewById(R.id.layout);
        Random rnd = new Random();
        int color = Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));
        layout.setBackgroundColor(color);
        convertView.post(new Runnable() {
            @Override
            public void run() {
                int height = ((LinearLayout)layout.getParent()).getHeight();
                LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) layout.getLayoutParams();
                int margin = (int) (height / ((layout.getRotationX() / 2) + 1));
                params.setMargins(margin, margin, margin, margin);
                layout.setLayoutParams(params);
                ((LinearLayout)layout.getParent()).setPadding(margin,margin,margin,margin);
                textView.setText(textView.getText() + " - " + height + " - " + margin);
            }
        });
        return convertView;
    }
}
