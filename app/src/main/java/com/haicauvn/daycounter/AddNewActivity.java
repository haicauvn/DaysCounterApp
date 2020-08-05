package com.haicauvn.daycounter;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Context;
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

import java.util.Calendar;

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

        //dateInput.setInputType(InputType.TYPE_NULL);
        dateInput.setText("Aug");
        dateInput.setShowSoftInputOnFocus(false);
//        dateInput.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View view, MotionEvent motionEvent) {
//                showDatePickerDialog();
//                return true;
//            }
//        });
        dateInput.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                showDatePickerDialog();
            }
        });
        dateInput.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDatePickerDialog();
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
        String date = Integer.toString(i2) +" "+Integer.toString(i1) +" "+ Integer.toString(i);
        dateInput.setText(date);
    }
}