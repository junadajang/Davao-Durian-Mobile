package edu.sti.davaodurian.e_rrigate;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;

public class Profile extends AppCompatActivity implements View.OnClickListener {

    ImageButton img1;
    ImageButton img2;
    ImageButton img3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        Intent intent = getIntent();

        img1 = (ImageButton) findViewById(R.id.imgBtnHome);
        img1.setOnClickListener(this);

        img2 = (ImageButton) findViewById(R.id.imgBtnMonitor);
        img2.setOnClickListener(this);

        img3 = (ImageButton) findViewById(R.id.imgBtnAbout);
        img3.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        if (v == img1 )
        {
            Intent intentHome = new Intent(this, Home.class);
            startActivity(intentHome);
        }
        if (v == img2) {
            Intent intentMonitor = new Intent(this, Monitor.class);
            startActivity(intentMonitor);
        }
        if (v == img3){
            Intent intentAbout = new Intent(this, About.class);
            startActivity(intentAbout);
        }

    }
}

