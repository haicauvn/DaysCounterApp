package com.haicauvn.daycounter;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class AddNewActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {
    Button doneButton;
    TextInputEditText inputTitle, inputDes, dateInput;
    TextInputLayout dateInputLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new);
        inputTitle = (TextInputEditText) findViewById(R.id.titleEditText);
        inputDes = (TextInputEditText) findViewById(R.id.desEditText);
        dateInput = (TextInputEditText) findViewById(R.id.dateEditText);
        //dateInputLayout = (TextInputLayout) findViewById(R.id.dateEditLayout);

        SimpleDateFormat DateFor = new SimpleDateFormat("E, MMM dd yyyy");
        dateInput.setText(DateFor.format(new Date()).toString());
        dateInput.setShowSoftInputOnFocus(false);
        dateInput.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDatePickerDialog();
                hideSoftKeyboard(AddNewActivity.this);
            }
        });
        doneButton = (Button) findViewById(R.id.btnAddNew);
        doneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!inputTitle.getText().toString().equals("")){
                    Intent I = new Intent(AddNewActivity.this, MainActivity.class);
                    I.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    final String getNameItem = inputTitle.getText().toString();
                    final String getDateItem = dateInput.getText().toString();
                    final String getDesciption = inputDes.getText().toString();

                    I.putExtra("nameitem", getNameItem);
                    I.putExtra("dateitem", getDateItem);
                    I.putExtra("desitem", getDesciption);
                    startActivity(I);
                }
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