package com.haicauvn.daycounter;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    ListView listView;
    TextView DateText;
    List<Item> listItem;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = (ListView)findViewById(R.id.ListView);
        listItem = new ArrayList<Item>();
        listItem.add(new Item("Name1", new Date()));
        listItem.add(new Item("Name2", new Date()));
        listItem.add(new Item("Name3", new Date()));
        listItem.add(new Item("Name4", new Date()));
        listItem.add(new Item("Name5", new Date()));

        ItemsAdapter adapter = new ItemsAdapter(
                MainActivity.this,
                R.layout.row,
                listItem
        );
        listView.setAdapter(adapter);
    }
}