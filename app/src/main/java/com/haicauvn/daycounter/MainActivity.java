package com.haicauvn.daycounter;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.text.ParseException;
import java.text.SimpleDateFormat;
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
        String nop="04/08/2020";
        String home= "27/07/2020";
        String tet = "25/01/2020";
        Date date1 = new Date(), date2= new Date(), date3 = new Date(),dateCur= new Date();
        try {
            date1=new SimpleDateFormat("dd/MM/yyyy").parse(nop);
            date2=new SimpleDateFormat("dd/MM/yyyy").parse(home);
            date3=new SimpleDateFormat("dd/MM/yyyy").parse(tet);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        listItem.add(new Item("Nop", date1));
        listItem.add(new Item("Go home and left the game", date2));
        listItem.add(new Item("Tet", date3));
        SimpleDateFormat DateFor = new SimpleDateFormat("dd/MM/yyyy");
        String stringDate= DateFor.format(new Date());
        DateText = (TextView) findViewById(R.id.DateText);
        DateText.setText("Today: " +stringDate);
        ItemsAdapter adapter = new ItemsAdapter(
                MainActivity.this,
                R.layout.row,
                listItem
        );
        listView.setAdapter(adapter);
    }
}