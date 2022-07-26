package com.example.cardiacmeasurementmanager;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;



public class ViewDetails extends AppCompatActivity {
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    Gson gson;
    ArrayList<ModelClass> arrayList;
    ModelClass modelClass;
    TextView date, time, systolic, diastolic, heartRate, comment;
    Button editButton, backButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_details);
        Intent intent = getIntent();
        int index = intent.getIntExtra("index", 0);
        date = findViewById(R.id.dateValue);
        time = findViewById(R.id.timeValue);
        systolic = findViewById(R.id.systolicValue);
        diastolic = findViewById(R.id.diastolicValue);
        heartRate = findViewById(R.id.heartRateValue);
        comment = findViewById(R.id.commentValue);
        editButton = findViewById(R.id.editButton);
        backButton = findViewById(R.id.backButton);
        loadData();
        modelClass = arrayList.get(index);
        date.setText("" + modelClass.getDate());
        time.setText("" + modelClass.getTime());
        systolic.setText("" + modelClass.getSystolic());
        diastolic.setText("" + modelClass.getDiastolic());
        heartRate.setText("" + modelClass.getHeartRate());
        comment.setText("" + modelClass.getComment());
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(ViewDetails.this,UpdateMeasurement.class);
                intent1.putExtra("index",index);
                startActivity(intent1);
                finish();
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