package com.haicauvn.daycounter;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class InfoActivity extends AppCompatActivity {

    TextView textname;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);
        textname= (TextView) findViewById(R.id.textView);
        textname.setText(getIntent().getStringExtra("nameitem"));
    }
}