package com.example.cardiacmeasurementmanager;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
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

/**
 * Insert Details class
 */
public class InsertDetails extends AppCompatActivity {

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    Gson gson;
    ArrayList<ModelClass> arrayList;
    ModelClass modelClass;
    DatePickerDialog.OnDateSetListener onDateSetListener;
    EditText date,time,systolic,diastolic,heartRate,comment;
    Button saveButton;
    String dateStr,timeStr;

    /**
     * saved Instance State
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_details);
        date = findViewById(R.id.dateValue);
        time = findViewById(R.id.timeValue);
        systolic = findViewById(R.id.systolicValue);
        diastolic = findViewById(R.id.diastolicValue);
        heartRate = findViewById(R.id.heartRateValue);
        comment = findViewById(R.id.commentValue);
        saveButton = findViewById( R.id.addButton);
        loadData();
        date.setOnClickListener(new View.OnClickListener() {
            /**
             *
             * @param view
             */
            @Override
            public void onClick(View view) {
                Calendar calendar = Calendar.getInstance();
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                int day = calendar.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog dialog = new DatePickerDialog(InsertDetails.this, android.R.style.Theme_Holo_Light_Dialog_MinWidth, onDateSetListener,
                        year, month, day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });
        time.setOnClickListener(new View.OnClickListener() {
            /**
             *
             * @param view
             */
            @Override
            public void onClick(View view) {
                Calendar calendar = Calendar.getInstance();
                int hour =calendar.get(Calendar.HOUR_OF_DAY);
                int minute =calendar.get(Calendar.MINUTE);
                //calendar.clear();
                TimePickerDialog dialog =  new TimePickerDialog(InsertDetails.this, new TimePickerDialog.OnTimeSetListener() {
                    /**
                     *
                     * @param view
                     * @param hourOfDay
                     * @param minute
                     */
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        timeStr = hourOfDay + ":" +minute;
                        time.setText(timeStr);

                    }
                }, hour , minute,true);
                //  dialog.setTitle("Select Time");

                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });
        onDateSetListener = new DatePickerDialog.OnDateSetListener() {
            /**
             * 
             * @param view
             * @param year
             * @param month
             * @param dayOfMonth
             */
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                dateStr = dayOfMonth + "-" + (month + 1) + "-" + year;
                date.setText(dateStr);
            }
        };
        saveButton.setOnClickListener(v -> {

            if (CheckFieldValidity()) {
                int sysInt = Integer.parseInt(systolic.getText().toString());
                int diasInt = Integer.parseInt(diastolic.getText().toString());
                int heartInt = Integer.parseInt(heartRate.getText().toString());
                String commentStr = comment.getText().toString();
                modelClass = new ModelClass(dateStr,timeStr,sysInt,diasInt,heartInt,commentStr);
                arrayList.add(modelClass);
                MainActivity.arrayList.add(modelClass);
                MainActivity.recyclerAdapter.notifyDataSetChanged();
                Toast.makeText(InsertDetails.this,"Record added!",Toast.LENGTH_SHORT).show();
                PreferenceManager.getDefaultSharedPreferences(this).edit().clear().commit();
                saveData();
                finish();
            }
        });
    }

    /**
     * Check Field Validity Function, checks validity of entered data
     * @return returns data validity
     */
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
            systolic.setError("Invalid data");
            return false;
        }
        if(n1>250)
        {
            systolic.setError("Invalid data");
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
            diastolic.setError("Invalid data");
            return false;
        }
        if(n2>200)
        {
            diastolic.setError("Invalid data");
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
            heartRate.setError("Invalid data");
            return false;
        }

        if(n3>150)
        {
            heartRate.setError("Invalid data");
            return false;
        }

        return true;
    }

    /**
     * Function to save data to the database
     */
    private void saveData()
    {
        sharedPreferences = getSharedPreferences("sharedpreference",MODE_PRIVATE);
        editor = sharedPreferences.edit();
        gson = new Gson();
        String jsonString = gson.toJson(arrayList);
        editor.putString("details",jsonString);
        editor.apply();
    }

    /**
     * Function to load data
     */
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