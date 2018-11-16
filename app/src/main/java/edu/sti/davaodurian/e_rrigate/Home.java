package edu.sti.davaodurian.e_rrigate;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.Spinner;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Home extends AppCompatActivity implements CompoundButton.OnCheckedChangeListener, AdapterView.OnItemSelectedListener {

   // private static final String TAG = "Home Activity";
    public static final String TAG = "Home Activity";
    private boolean toggleButtonValue = false;
    private DatabaseReference mDatabase; //variable for Database Reference

    private DatabaseReference conditionRef;
    private DatabaseReference statusRef;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Intent intent = getIntent();

        final ToggleButton toggleButton = findViewById(R.id.toggle_button);

        toggleButtonValue = toggleButton.isChecked();

        toggleButton.setOnCheckedChangeListener(this);
        mDatabase = FirebaseDatabase.getInstance().getReference().getRoot();

        conditionRef = mDatabase.child("arduinoDevice");
        conditionRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Device arduino = dataSnapshot.getValue(Device.class);
                Log.d(TAG, " "+arduino.getWaterLevel());
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

                Log.w(TAG, "loadPost:onCancelled", databaseError.toException());

            }
        });

                statusRef = mDatabase.child("waterLevel");
                statusRef.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        int signal = dataSnapshot.getValue(int.class);
                        Log.d(TAG, ""+ signal);
                        if (signal == 1);

                        {
                            toggleButton.setChecked(true);
                        }
                        if (signal == 0){
                                toggleButton.setChecked(false);
                            }

                    }


                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                        Log.w (TAG, "loadPost:onCancelled", databaseError.toException());
                    }
                });


        Spinner spinner = findViewById(R.id.spinner_irrigation);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.irrigation, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);

    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        int id = buttonView.getId();
        int value = 0;

        switch (id){
            case R.id.toggle_button:
                toggleButtonValue = isChecked;
                value = isChecked ? 1 : 0;
                break;
        }
        mDatabase.child("arduinoDevice").child("Switch").setValue(value); // Insert Data to Realtime Database: arduinoDevice/switch=value;
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String text = parent.getItemAtPosition(position).toString();
        Toast.makeText(parent.getContext(), text, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}

