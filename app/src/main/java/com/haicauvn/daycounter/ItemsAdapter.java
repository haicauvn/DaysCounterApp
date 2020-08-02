package com.haicauvn.daycounter;

import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class ItemsAdapter extends BaseAdapter {
    Context myContext;
    int myLayout;
    List<Item> myList;
    public ItemsAdapter(Context context, int layout, List<Item> list) {
        myContext= context;
        myLayout = layout;
        myList = list;
    }

    @Override
    public int getCount() {
        return myList.size();
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
        LayoutInflater inflater = (LayoutInflater) myContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = inflater.inflate(myLayout, null);
        TextView txtName = (TextView)view.findViewById(R.id.NameText);
        txtName.setText(myList.get(i).getName());
        TextView txtNum = (TextView)view.findViewById(R.id.NumberText);
        txtNum.setText(myList.get(i).getDate().toString());
        return view;
    }
}
