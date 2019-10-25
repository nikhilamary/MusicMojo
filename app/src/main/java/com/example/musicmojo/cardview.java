package com.example.musicmojo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class cardview extends AppCompatActivity {
    private TextView Location1;


    ListView detailList;
    List<DetailModel> details;

    private DatabaseReference db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cardview);

        detailList = findViewById(R.id.listView);
        db = FirebaseDatabase.getInstance().getReference().child("Details");

        details = new ArrayList<>();
        Location1 = (TextView)findViewById(R.id.location);

        db.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot postSnapshot: dataSnapshot.getChildren()) {
                    DetailModel detail = postSnapshot.getValue(DetailModel.class);
                    details.add(detail);

                }

                DetailAdapter adapter = new DetailAdapter(cardview.this, details);
                detailList.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    public void locate(View view) {
        Intent i = new Intent(cardview.this,MapsActivity.class);
        startActivity(i);
    }
}

