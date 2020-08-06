package com.haicauvn.daycounter;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class EditActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {

    Button doneButton;
    TextInputEditText inputTitle, inputDes, dateInput;
    TextInputLayout dateInputLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        setTitle("Edit Event");
        inputTitle = (TextInputEditText) findViewById(R.id.titleEditText);
        inputDes = (TextInputEditText) findViewById(R.id.desEditText);
        dateInput = (TextInputEditText) findViewById(R.id.dateEditText);
        inputTitle.setText(getIntent().getStringExtra("nameitem"));
        dateInput.setText(getIntent().getStringExtra("dateitem"));
        dateInput.setShowSoftInputOnFocus(false);
        dateInput.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDatePickerDialog();
                hideSoftKeyboard(EditActivity.this);
            }
        });

        inputDes.setText(getIntent().getStringExtra("desitem"));

        //done Button
        doneButton =(Button) findViewById(R.id.btnDoneEdit);
        doneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(EditActivity.this, "Done", Toast.LENGTH_SHORT).show();
                Intent I = new Intent(EditActivity.this, MainActivity.class);
                //I.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                final String getNameItem = inputTitle.getText().toString();
                final String getDateItem = dateInput.getText().toString();
                final String getDesciption = inputDes.getText().toString();
                I.putExtra("iditem", getIntent().getStringExtra("iditem"));
                I.putExtra("numitem", getIntent().getStringExtra("numitem"));
                I.putExtra("nameitem", getNameItem);
                I.putExtra("dateitem", getDateItem);
                I.putExtra("desitem", getDesciption);
                I.putExtra("action","edit");
                I.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(I);
            }
        });
    }

    public static void hideSoftKeyboard(Activity activity) {
        InputMethodManager inputMethodManager =
                (InputMethodManager) activity.getSystemService(
                        Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(
                activity.getCurrentFocus().getWindowToken(), 0);
    }
    private void showDatePickerDialog(){
        DatePickerDialog datePickerDialog = new DatePickerDialog(
                this,
                this,
                Calendar.getInstance().get(Calendar.YEAR),
                Calendar.getInstance().get(Calendar.MONTH),
                Calendar.getInstance().get(Calendar.DAY_OF_MONTH)
        );
        datePickerDialog.show();
    }
    @Override
    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
        String stringDate = Integer.toString(i2) +"/"+Integer.toString(i1+1) +"/"+ Integer.toString(i);
        Date date = new Date();
        try {
            date = new SimpleDateFormat("dd/MM/yyyy").parse(stringDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        SimpleDateFormat DateFor = new SimpleDateFormat("E, MMM dd yyyy");
//        SimpleDateFormat DateFor = new SimpleDateFormat("dd/MM/yyyy");
        stringDate= DateFor.format(date);
        dateInput.setText(stringDate);
//        dateInput.setText(date.toString());
    }
}