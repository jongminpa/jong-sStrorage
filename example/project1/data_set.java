package com.example.project1;

import androidx.appcompat.app.AppCompatActivity;


import android.os.Bundle;

import android.widget.DatePicker;
import android.widget.EditText;

public class data_set extends AppCompatActivity {

    EditText dlgEdtHeight, dlgEdtWeight;
    DatePicker datePicker;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_set);

        dlgEdtHeight = (EditText) findViewById(R.id.edtHeight);
        dlgEdtWeight = (EditText) findViewById(R.id.edtWeight);
        datePicker = (DatePicker) findViewById(R.id.mCurrentDate);




    }
}
