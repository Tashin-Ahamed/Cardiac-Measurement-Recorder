package com.example.cardiacmeasurementmanager;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Calendar;

public class UpdateMeasurement extends AppCompatActivity {
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    Gson gson;
    ArrayList<ModelClass> arrayList;
    DatePickerDialog.OnDateSetListener onDateSetListener;
    TimePickerDialog.OnTimeSetListener onTimeSetListener;
    String dateStr,timeStr;
    ModelClass modelClass;
    EditText date,time,systolic,diastolic,heartRate,comment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_measurement);

        Intent intent = getIntent();
        int index = intent.getIntExtra("index",0);
        date = findViewById(R.id.dateValue);
        time = findViewById(R.id.timeValue);
        systolic = findViewById(R.id.systolicValue);
        diastolic = findViewById(R.id.diastolicValue);
        heartRate = findViewById(R.id.heartRateValue);
        comment = findViewById(R.id.commentValue);
        Button updateButton = findViewById( R.id.addButton);
        loadData();
        datePicker();
        timePicker();
        modelClass = arrayList.get(index);
        date.setText(""+modelClass.getDate());
        time.setText(""+modelClass.getTime());
        systolic.setText(""+modelClass.getSystolic());
        diastolic.setText(""+modelClass.getDiastolic());
        heartRate.setText(""+modelClass.getHeartRate());
        comment.setText(""+modelClass.getComment());
        updateButton.setOnClickListener(v -> {
            if (CheckFieldValidity()) {
                int sysInt = Integer.parseInt(systolic.getText().toString());
                int diasInt = Integer.parseInt(diastolic.getText().toString());
                int heartInt = Integer.parseInt(heartRate.getText().toString());
                String commentStr = comment.getText().toString();
                modelClass = new ModelClass(dateStr,timeStr,sysInt,diasInt,heartInt,commentStr);
                arrayList.set(index,modelClass);
                MainActivity.arrayList.set(index,modelClass);
                MainActivity.recyclerAdapter.notifyDataSetChanged();
                PreferenceManager.getDefaultSharedPreferences(this).edit().clear().commit();
                saveData();
                Toast.makeText(UpdateMeasurement.this,"Updated successfully!",Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }
    private boolean CheckFieldValidity() {
        if (date.length() == 0) {
            date.setError("This field is required");
            return false;
        }

        if (time.length() == 0) {
            time.setError("This field is required");
            return false;
        }

        if (systolic.length() == 0) {
            systolic.setError("This field is required");
            return false;
        }

        String s1 = systolic.getText().toString();
        int n1 = Integer.parseInt(s1);
        if(n1<0)
        {
            systolic.setError("Invalid data input");
            return false;
        }
        if(n1>250)
        {
            systolic.setError("Invalid data input");
            return false;
        }


        if (diastolic.length() == 0) {
            diastolic.setError("This field is required");
            return false;
        }

        String s2 = diastolic.getText().toString();
        int n2 = Integer.parseInt(s2);
        if(n2<0)
        {
            diastolic.setError("Invalid data input");
            return false;
        }
        if(n2>200)
        {
            diastolic.setError("Invalid data input");
            return false;
        }

        if (heartRate.length() == 0) {
            heartRate.setError("This field is required");
            return false;
        }

        String s3 = heartRate.getText().toString();
        int n3 = Integer.parseInt(s3);

        if(n3<0)
        {
            heartRate.setError("Invalid data input");
            return false;
        }

        if(n3>150)
        {
            heartRate.setError("Invalid data input");
            return false;
        }

        return true;
    }

    private void datePicker()
    {
        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar calendar = Calendar.getInstance();
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                int day = calendar.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog dialog = new DatePickerDialog(UpdateMeasurement.this, android.R.style.Theme_Holo_Light_Dialog_MinWidth, onDateSetListener,
                        year, month, day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });
        onDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                dateStr = dayOfMonth + "-" + (month + 1) + "-" + year;
                date.setText(dateStr);
            }
        };
    }
    private void timePicker() {
        time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar calendar = Calendar.getInstance();
                int hour =calendar.get(Calendar.HOUR_OF_DAY);
                int minute =calendar.get(Calendar.MINUTE);
                TimePickerDialog dialog =  new TimePickerDialog(UpdateMeasurement.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        timeStr = hourOfDay + ":" +minute;
                        time.setText(timeStr);

                    }
                }, hour , minute,true);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });
    }

    private void saveData()
    {
        sharedPreferences = getSharedPreferences("sharedpreference",MODE_PRIVATE);
        editor = sharedPreferences.edit();
        gson = new Gson();
        String jsonString = gson.toJson(arrayList);
        editor.putString("details",jsonString);
        editor.apply();
    }
    private void loadData()
    {
        sharedPreferences = getSharedPreferences("sharedpreference",MODE_PRIVATE);
        gson = new Gson();
        String jsonString = sharedPreferences.getString("details",null);
        Type type = new TypeToken<ArrayList<ModelClass>>(){}.getType();
        arrayList = gson.fromJson(jsonString,type);
        if(arrayList ==null)
        {
            arrayList = new ArrayList<ModelClass>();
        }
    }
}