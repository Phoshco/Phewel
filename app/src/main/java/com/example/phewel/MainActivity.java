package com.example.phewel;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    private Button enter;
    private TextView avgcost;
    private TextView avgconsume;
    private RecyclerView eventview;
    private EditText mileage;
    private EditText fuel;
    private EditText cost;
    private Switch remove;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        enter = findViewById(R.id.enter);
        avgcost = findViewById(R.id.avgcost);
        avgconsume = findViewById(R.id.avgconsume);
        eventview = findViewById(R.id.eventview);
        mileage = findViewById(R.id.mileage);
        fuel = findViewById(R.id.fuel);
        cost = findViewById(R.id.cost);
        remove = findViewById(R.id.remove);





    }
}