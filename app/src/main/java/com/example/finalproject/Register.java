package com.example.finalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Register extends AppCompatActivity {
    Button save;
    EditText name,phone,email;
    public static String tName;
    public static String tPhone;
    public static String tEmail;

    DatabaseHelper mDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Register");
        setContentView(R.layout.activity_register);
        save=findViewById(R.id.btnSave);
        name=findViewById(R.id.txtName);
        phone=findViewById(R.id.txtPhone);
        email=findViewById(R.id.txtEmail);
        mDatabase = new DatabaseHelper(this);

       save.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               emptyCheck();

               tName=name.getText().toString().trim();
               tPhone=phone.getText().toString().trim();
               tEmail=email.getText().toString().trim();
               if(tName.isEmpty()&&tPhone.isEmpty()&&tEmail.isEmpty())
               {
                   Toast.makeText(getApplicationContext(), "Fields cannot be empty", Toast.LENGTH_SHORT).show();
               }
               else {
                   Intent intent = new Intent(Register.this, Verify.class);
                   startActivity(intent);
               }
           }
       });



    }
    private void emptyCheck()
    {
        String uname = name.getText().toString().trim();
        String uphone = phone.getText().toString().trim();
        String uemail = email.getText().toString().trim();
        if (uname.isEmpty()) {
            name.setError("name field is mandatory");
            name.requestFocus();
            return;
        }

        if (uphone.isEmpty()) {
            phone.setError("salary field cannot be empty");
            phone.requestFocus();
            return;
        }
        if (uemail.isEmpty()) {
            email.setError("salary field cannot be empty");
            email.requestFocus();
            return;
        }

    }

}