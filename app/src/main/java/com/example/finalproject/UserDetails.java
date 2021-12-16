package com.example.finalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class UserDetails extends AppCompatActivity {
    ImageView img;
    TextView name,email,phone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_details);
        img=findViewById(R.id.imgDetUser);
        name=findViewById(R.id.txvDetName);
        email=findViewById(R.id.txvDetEmail);
        phone=findViewById(R.id.txvDetPhone);

        Bundle recdData = getIntent().getExtras();
        String myVal = recdData.getString("name");
        name.setText(recdData.getString("name"));
        email.setText(recdData.getString("email"));
        phone.setText(recdData.getString("phone"));
        img.setImageBitmap(Utility.getPhoto(recdData.getByteArray("image")));

    }
}