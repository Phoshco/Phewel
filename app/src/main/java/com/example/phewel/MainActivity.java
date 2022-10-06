package com.example.phewel;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Button enter;
    private TextView avgcost;
    private TextView avgconsume;
    private RecyclerView recyclerView;
    private EditText mileage;
    private EditText fuel;
    private EditText cost;
    private Switch remove;

    itemAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        enter = findViewById(R.id.enter);
        avgcost = findViewById(R.id.avgcost);
        avgconsume = findViewById(R.id.avgconsume);
        recyclerView = findViewById(R.id.eventview);
        mileage = findViewById(R.id.mileage);
        fuel = findViewById(R.id.fuel);
        cost = findViewById(R.id.cost);
        remove = findViewById(R.id.remove);

        interfaceData id = new interfaceData(MainActivity.this);

        //List<Entry> list = id.readData();
        //id.createFile();
        List<Entry> list = id.processData();

        calcInfo calc = new calcInfo(list);
        avgconsume.setText(calc.avgFuelEff());
        avgcost.setText(calc.avgCostEff());

        adapter = new itemAdapter(list, getApplicationContext());
        recyclerView.setAdapter(adapter);
        LinearLayoutManager layout = new LinearLayoutManager(MainActivity.this);
        recyclerView.setLayoutManager(layout);
        //GradientDrawable drawable = new GradientDrawable(GradientDrawable.Orientation.BOTTOM_TOP, new int[]{0xFFB600FF,0xFF00DDFF});
        //drawable.setThickness(10);
        DividerItemDecoration itemDecoration = new DividerItemDecoration(recyclerView.getContext(), layout.getOrientation());
        //itemDecoration.setDrawable(drawable);
        recyclerView.addItemDecoration(itemDecoration);

        enter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String beforeOdo = list.get(0).getOdoBefore()+"";
                String toMileage = mileage.getText().toString();
                String toFuel = fuel.getText().toString();
                String toCost = cost.getText().toString();
                String toType = "SPC 98";
                if (TextUtils.isEmpty(toMileage)||TextUtils.isEmpty(toFuel)||TextUtils.isEmpty(toCost)){
                    Toast.makeText(MainActivity.this,"Empty field(s)",Toast.LENGTH_SHORT).show();
                } else if (toMileage.equals("69") || toFuel.equals("69") || toCost.equals("69")){
                    id.createFile();
                    Toast.makeText(MainActivity.this,"Reset!", Toast.LENGTH_SHORT).show();
                    finish();
                    startActivity(getIntent());
                } else if (toMileage.equals("1") || toFuel.equals("1") || toCost.equals("1")){
                    id.exportFile();
                    Toast.makeText(MainActivity.this,"Exported!", Toast.LENGTH_SHORT).show();
                } else {
                Entry newEntry = new Entry(beforeOdo, toMileage, toFuel, toCost, toType);
                id.addData(newEntry);
                Toast.makeText(MainActivity.this,"Added!", Toast.LENGTH_SHORT).show();
                finish();
                startActivity(getIntent());
                }
            }
        });



    }
}