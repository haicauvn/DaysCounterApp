package com.haicauvn.daycounter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Parcelable;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.w3c.dom.Text;

import java.lang.reflect.Type;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {
    ListView listView;
    TextView DateText;
    ArrayList<Item> listItem;
    Button buttonAdd;
    private static String action;
    private static final String LIST_ITEM = "LIST_ITEM";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = (ListView)findViewById(R.id.ListView);
        listItem = new ArrayList<Item>();
        if(savedInstanceState!=null){
            listItem = savedInstanceState.getParcelableArrayList(LIST_ITEM);
            DateText.setText("ok");
        }
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
        SharedPreferences sharedPreferences = getSharedPreferences("share preferences", MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString(LIST_ITEM, null);
        Type type = new TypeToken<ArrayList<Item>>() {}.getType();
        if(json!=null || savedInstanceState!=null){
            listItem =gson.fromJson(json, type);
        }
//            listItem.add(new Item("Nop", date1));
//            listItem.add(new Item("Go home and left the game", date2));
//            listItem.add(new Item("Tet", date3));
        action = getIntent().getStringExtra("action");

        if(getIntent().getStringExtra("action")!=null){
            if(getIntent().getStringExtra("action").equals("delete")){
                for(int i=0; i<listItem.size(); i++){
                   if(listItem.get(i).getId().equals(getIntent().getStringExtra("iditem"))){
                        listItem.remove(i);
                        break;
                   }
                }
            }else if(getIntent().getStringExtra("action").equals("add")){
                addToList();
            }else if(getIntent().getStringExtra("action").equals("edit")){
                editItemToList();
                Intent I = new Intent(MainActivity.this, InfoActivity.class);
                I.putExtra("nameitem", getIntent().getStringExtra("nameitem"));
                I.putExtra("desitem", getIntent().getStringExtra("desitem"));
                I.putExtra("dateitem",getIntent().getStringExtra("dateitem"));
                I.putExtra("iditem",getIntent().getStringExtra("iditem"));

                //send number days
                Date dateTemp = new Date();
                try {
                    dateTemp=new SimpleDateFormat("E, MMM dd yyyy").parse(getIntent().getStringExtra("dateitem"));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                long diff = (new Date()).getTime()- dateTemp.getTime();
                diff = TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
                I.putExtra("numitem", Long.toString(diff));

                //start
                startActivity(I);
            }
        }

        SimpleDateFormat DateFor = new SimpleDateFormat("EE dd/MM/yyyy");
        String stringDate= DateFor.format(new Date());
        DateText = (TextView) findViewById(R.id.DateText);
        DateText.setText(stringDate);

        ItemsAdapter adapter = new ItemsAdapter(
                MainActivity.this,
                R.layout.row,
                listItem
        );
        listView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView absListView, int i) {

            }

            @Override
            public void onScroll(AbsListView absListView, int i, int i1, int i2) {
                if(i +i1 ==i2 && i2!=0 && i >=1){
                    buttonAdd.setVisibility(View.INVISIBLE);
                }
                if(i+i1!=i2 && buttonAdd.getVisibility()!=View.VISIBLE){
                    buttonAdd.setVisibility(View.VISIBLE);
                }
            }
        });
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent I = new Intent(view.getContext(), InfoActivity.class);
                final String getNameItem = listItem.get(i).getName();
                SimpleDateFormat DateFor = new SimpleDateFormat("E, MMM dd yyyy");
                final String getDateItem = DateFor.format(listItem.get(i).getDate()) ;
                final String getDesciption = listItem.get(i).getDescription();
                long diff = (new Date()).getTime()- listItem.get(i).getDate().getTime();
                diff = TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
                I.putExtra("nameitem", getNameItem);
                I.putExtra("dateitem", getDateItem);
                I.putExtra("desitem", getDesciption);
                I.putExtra("numitem",Long.toString(diff));
                I.putExtra("iditem",listItem.get(i).getId());
                startActivity(I);
            }
        });
        buttonAdd = (Button) findViewById(R.id.btnAddNew);
        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent I = new Intent(MainActivity.this, AddNewActivity.class);
                saveData();
                startActivity(I);
            }
        });
    }
    private void saveData(){
        SharedPreferences sharedPreferences = getSharedPreferences("share preferences", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json =gson.toJson(listItem);
        editor.putString(LIST_ITEM, json);
        editor.apply();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 0) {
            if (resultCode == Activity.RESULT_OK) {
                saveData();
            }
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        saveData();
    }
    private void addToList(){
        String titletemp = getIntent().getStringExtra("nameitem").toString();
        String datetemp = getIntent().getStringExtra("dateitem").toString();
        String destemp = getIntent().getStringExtra("desitem").toString();
        Date dateTemp = new Date();
        try {
            dateTemp=new SimpleDateFormat("E, MMM dd yyyy").parse(datetemp);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        listItem.add(new Item(titletemp,dateTemp,destemp));
    }
    private void editItemToList(){
        for(int i=0; i<listItem.size(); i++){
            if(listItem.get(i).getId().equals(getIntent().getStringExtra("iditem"))){
                listItem.get(i).setName(getIntent().getStringExtra("nameitem"));
                listItem.get(i).setDescription(getIntent().getStringExtra("desitem"));
                Date dateTemp = new Date();
                try {
                    dateTemp=new SimpleDateFormat("E, MMM dd yyyy").parse(getIntent().getStringExtra("dateitem"));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                listItem.get(i).setDate(dateTemp);
                break;
            }
        }
    }
    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelableArrayList(LIST_ITEM,  listItem);
    }
}