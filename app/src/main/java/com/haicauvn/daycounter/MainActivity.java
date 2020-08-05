package com.haicauvn.daycounter;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

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
    Button buttonAdd;

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
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent I = new Intent(view.getContext(), InfoActivity.class);
                final String getNameItem = listItem.get(i).getName();
                final Date getDateItem = listItem.get(i).getDate();
                final String getDesciption = listItem.get(i).getDescription();

                I.putExtra("nameitem", getNameItem);
                I.putExtra("dateitem", getDateItem);
                I.putExtra("desitem", getDesciption);
                startActivity(I);
            }
        });
        buttonAdd = (Button) findViewById(R.id.btnAddNew);
        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent I = new Intent(MainActivity.this, AddNewActivity.class);
                startActivity(I);
            }
        });
    }
}