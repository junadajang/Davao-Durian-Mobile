package edu.sti.davaodurian.e_rrigate;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class Monitor extends AppCompatActivity {

    ListView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_monitor);

        lv = (ListView) findViewById(R.id.lvMoni);

        final ArrayList<String> arrayList = new ArrayList<>();

        arrayList.add("Farmer 1");
        arrayList.add("Farmer 2");
        arrayList.add("Farmer 3");
        arrayList.add("Farmer 4");
        arrayList.add("Farmer 5");

        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, arrayList);

        lv.setAdapter(arrayAdapter);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long l) {
                Toast.makeText(Monitor.this, "clicked item" + i + " " + arrayList.get(i).toString(), Toast. LENGTH_SHORT).show();
            }
        });



    }
}
