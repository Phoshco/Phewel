package com.example.phewel;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.view.MenuItem;

import com.google.android.material.appbar.MaterialToolbar;
import com.jjoe64.graphview.GraphView;
import androidx.core.splashscreen.SplashScreen;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Button enter;
    private TextView avgcost;
    private TextView avgconsume;
    private RecyclerView recyclerView;
    private EditText mileage;
    private EditText fuel;
    private EditText cost;
    private Button remove;
    private GraphView graphView;
    interfaceData id;
    itemAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SplashScreen splashScreen = SplashScreen.installSplashScreen(this);

        setContentView(R.layout.activity_main);

        //this.getSupportActionBar().hide();
        MaterialToolbar toolbar = (MaterialToolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        enter = findViewById(R.id.enter);
        avgcost = findViewById(R.id.avgcost);
        avgconsume = findViewById(R.id.avgconsume);
        recyclerView = findViewById(R.id.eventview);
        mileage = findViewById(R.id.mileage);
        fuel = findViewById(R.id.fuel);
        cost = findViewById(R.id.cost);
        remove = findViewById(R.id.remove);
        graphView = findViewById(R.id.graphview);

        id = new interfaceData(MainActivity.this);
        List<Entry> list = id.processData();

        calcInfo calc = new calcInfo(list);
        Double davgConsume = calc.avgFuelEff();
        String avgConsume = davgConsume + " km/L";
        avgconsume.setText(avgConsume);
        avgcost.setText(calc.avgCostEff());
        calc.generateGraphs(graphView);

        adapter = new itemAdapter(list, getApplicationContext());
        recyclerView.setAdapter(adapter);
        LinearLayoutManager layout = new LinearLayoutManager(MainActivity.this);
        recyclerView.setLayoutManager(layout);
        //GradientDrawable drawable = new GradientDrawable(GradientDrawable.Orientation.BOTTOM_TOP, new int[]{0xFFB600FF,0xFF00DDFF});
        //drawable.setThickness(10);
        //DividerItemDecoration itemDecoration = new DividerItemDecoration(recyclerView.getContext(), layout.getOrientation());
        //itemDecoration.setDrawable(drawable);
        //recyclerView.addItemDecoration(itemDecoration);

        enter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String beforeOdo;
                String toMileage = mileage.getText().toString();
                String toFuel = fuel.getText().toString();
                String toCost = cost.getText().toString();
                String toType = "SPC 98";
                if (TextUtils.isEmpty(toMileage) || TextUtils.isEmpty(toFuel)||TextUtils.isEmpty(toCost)){
                    Toast.makeText(MainActivity.this,"Empty field(s)",Toast.LENGTH_SHORT).show();
                } else if (toMileage.equals("69") && toFuel.equals("69") && toCost.equals("69")){
                    id.resetFromSavedFile();
                    Toast.makeText(MainActivity.this,"Imported!", Toast.LENGTH_SHORT).show();
                    finish();
                    startActivity(getIntent());
                    overridePendingTransition(0,0);
                } else {
                    if (list.isEmpty()){
                        beforeOdo = id.firstOdo();
                    } else {
                        beforeOdo = list.get(0).getOdoAfter()+"";
                    }
                    beforeOdo = "" + (double) Math.round(Double.parseDouble(toMileage) * 10d) / 10d;
                    toMileage = Double.parseDouble(beforeOdo) + ((double) Math.round(Double.parseDouble(toMileage) * 10d) / 10d) + "";
                    Entry newEntry = new Entry(beforeOdo, toMileage, toFuel, toCost, toType);
                    id.addData(newEntry);
                    Toast.makeText(MainActivity.this,"Added!", Toast.LENGTH_SHORT).show();
                    finish();
                    startActivity(getIntent());
                    overridePendingTransition(0,0);
                }
            }
        });

        remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (list.size() == 0){
                    Toast.makeText(MainActivity.this,"No records to delete", Toast.LENGTH_SHORT).show();
                } else{
                    id.delData();
                    Toast.makeText(MainActivity.this,"Deleted latest record!", Toast.LENGTH_SHORT).show();
                    finish();
                    startActivity(getIntent());
                    overridePendingTransition(0,0);
                }
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.top_bar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.Reset:
                popupsClass resetPop = new popupsClass();
                resetPop.showReset(MainActivity.this, id);
                return true;
            case R.id.Import:
                popupsClass importPop = new popupsClass();
                importPop.showImport(MainActivity.this,id);
                return true;
            case R.id.Export:
                popupsClass exportPop = new popupsClass();
                exportPop.showExport(MainActivity.this,id);
                return true;
            case R.id.amogus:
                Toast.makeText(MainActivity.this,"SUS", Toast.LENGTH_SHORT).show();
                return true;

            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);
        }
    }

}