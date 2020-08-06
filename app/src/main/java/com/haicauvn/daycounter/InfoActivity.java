package com.haicauvn.daycounter;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import org.w3c.dom.Text;

public class InfoActivity extends AppCompatActivity {

    TextInputEditText desText;
    TextView titleText, numberText, dateText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);
        setTitle("View Event");
        desText = (TextInputEditText) findViewById(R.id.desEditText);
        titleText = (TextView) findViewById(R.id.titleText);
        dateText =(TextView) findViewById(R.id.dateText);
        numberText= (TextView) findViewById(R.id.NumberText);
        titleText.setText(getIntent().getStringExtra("nameitem"));
        dateText.setText(getIntent().getStringExtra("dateitem"));
        desText.setText(getIntent().getStringExtra("desitem"));
        numberText.setText(getIntent().getStringExtra("numitem"));

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.commonmenu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if(id==R.id.deleteMenu){
            AlertDialog.Builder builder = new AlertDialog.Builder(InfoActivity.this);
            //set title
            builder.setTitle("Delete Event");
            builder.setMessage("Are you sure?");
            builder.setIcon(R.drawable.ic_delete_sure);
            builder.setCancelable(true);
            builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    deleteAction();
                }
            });
            builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.cancel();
                }
            });
            AlertDialog alertDialog = builder.create();
            alertDialog.show();
        }
        return super.onOptionsItemSelected(item);
    }
    private void deleteAction(){
        Toast.makeText(this, "Delete Finish", Toast.LENGTH_SHORT).show();
        Intent I = new Intent(InfoActivity.this, MainActivity.class);
        I.putExtra("iditem", getIntent().getStringExtra("iditem"));
        I.putExtra("action", "delete");
        startActivity(I);
    }
}