package com.example.cardiacmeasurementmanager;

import android.content.Intent;
import android.content.SharedPreferences;
import android.icu.text.AlphabeticIndex;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.ArrayList;
public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    public static RecyclerAdapter recyclerAdapter;
    public static ArrayList<ModelClass> arrayList;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    Gson gson;
    Button addButton;
    ModelClass modelClass;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addButton = findViewById(R.id.addButton);
        loadData();
        recyclerView = findViewById(R.id.recyclerView);
        recyclerAdapter = new RecyclerAdapter(this,arrayList,ContextCompat.getColor(MainActivity.this, R.color.colorGreen), ContextCompat.getColor(MainActivity.this, R.color.colorRed));
        recyclerView.setAdapter(recyclerAdapter);
        recyclerView.setLayoutManager( new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));

        recyclerAdapter.setCustomClickListener(new RecyclerAdapter.CustomClickListener() {
            @Override
            public void cusOnClick(int position, View v) {
                Intent intent = new Intent(MainActivity.this, ViewDetails.class);
                intent.putExtra("index",position);
                startActivity(intent);
            }
            @Override
            public void onDeleteClick(int position) {
                arrayList.remove(position);
                recyclerAdapter.notifyItemRemoved(position);
                saveData();
                Toast.makeText(MainActivity.this,"Data Deleted!",Toast.LENGTH_SHORT).show();
            }


        });
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,InsertDetails.class));
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