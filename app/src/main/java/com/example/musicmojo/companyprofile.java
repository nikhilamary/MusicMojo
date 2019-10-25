package com.example.musicmojo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class companyprofile extends AppCompatActivity {
    private Button location;







    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_companyprofile);
        location =(Button)findViewById(R.id.locationbtn);
        location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(companyprofile.this,MapsActivity.class));
            }
        });
    }
}
