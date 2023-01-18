package com.example.drivershipment;
import android.content.Context;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.zip.Inflater;

public class CustomAdapter extends BaseAdapter {
    Context context;
    DriverShipment driverList[];
    LayoutInflater inflter;

    public CustomAdapter(Context applicationContext, DriverShipment[] driverList) {
        this.context = context;
        this.driverList = driverList;
        inflter = (LayoutInflater.from(applicationContext));
    }

    @Override
    public int getCount() {
        return driverList.length;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = inflter.inflate(R.layout.activity_listview, null);
        TextView text = (TextView) view.findViewById(R.id.textView);
        text.setText(driverList[i].getDriver());
        return view;
    }
}