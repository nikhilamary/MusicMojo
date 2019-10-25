package com.example.musicmojo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Addevent extends AppCompatActivity {
    private EditText date;
    private EditText time;
    private EditText Location;
    private Button addbtn;
    private Button backbtn;

    private DatabaseReference db;


    private static final String PRIMARY_CHANNEL_ID = "primary_notification_channel";
    private NotificationManager mNotifyManager;
    private static final int NOTIFICATION_ID = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addevent);

        date =(EditText)findViewById(R.id.date);
        time =findViewById(R.id.times);
        Location =(EditText)findViewById(R.id.location);
        addbtn = (Button)findViewById(R.id.addbtn);
        backbtn = (Button)findViewById(R.id.backbtn);

        db = FirebaseDatabase.getInstance().getReference().child("Details");




        addbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // startActivity(new Intent(Addevent.this,));

                addDetails();

                sendNotification();
            }
        });
        backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Addevent.this,MainActivity.class));
            }
        });
        createNotificationChannel();

    }

    public void addDetails(){
        String times = time.getText().toString().trim();
        String dates = date.getText().toString().trim();
        String locations = Location.getText().toString().trim();

        String id = db.push().getKey();
        if(TextUtils.isEmpty(dates)){
            Toast.makeText(this,"Please Enter Date",Toast.LENGTH_SHORT).show();
            return;
        }
        if(TextUtils.isEmpty(times)){
            Toast.makeText(this,"Please Enter Time",Toast.LENGTH_SHORT).show();
            return;


        }
        if(TextUtils.isEmpty(locations)){

            Toast.makeText(this,"Please Enter Location",Toast.LENGTH_SHORT).show();
            return;

        }
        else {
            try {
                DetailModel DM = new DetailModel(times, dates, locations);
                db.child(id).setValue(DM);
            } finally {

            }
        }
    }

    public void sendNotification() {
        NotificationCompat.Builder notifyBuilder = getNotificationBuilder();
        mNotifyManager.notify(NOTIFICATION_ID, notifyBuilder.build());

    }

    private NotificationCompat.Builder getNotificationBuilder(){

        Intent notificationIntent = new Intent(this, MainActivity.class);
        PendingIntent notificationPendingIntent = PendingIntent.getActivity(this,
                NOTIFICATION_ID, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        NotificationCompat.Builder notifyBuilder = new NotificationCompat.Builder(this, PRIMARY_CHANNEL_ID)
                .setContentTitle("New Event")
                .setContentText("New event has been added")
                .setContentIntent(notificationPendingIntent)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setDefaults(NotificationCompat.DEFAULT_ALL)
                .setAutoCancel(true)
                .setSmallIcon(R.drawable.musicmojo);

        return notifyBuilder;
    }

    public void createNotificationChannel()
    {
        mNotifyManager = (NotificationManager)
                getSystemService(NOTIFICATION_SERVICE);
        if (android.os.Build.VERSION.SDK_INT >=
                android.os.Build.VERSION_CODES.O) {
            // Create a NotificationChannel
            NotificationChannel notificationChannel = new NotificationChannel(PRIMARY_CHANNEL_ID,
                    "Mascot Notification", NotificationManager
                    .IMPORTANCE_HIGH);

            notificationChannel.enableLights(true);
            notificationChannel.setLightColor(Color.RED);
            notificationChannel.enableVibration(true);
            notificationChannel.setDescription("Notification from Mascot");
            mNotifyManager.createNotificationChannel(notificationChannel);
        }
    }

}
