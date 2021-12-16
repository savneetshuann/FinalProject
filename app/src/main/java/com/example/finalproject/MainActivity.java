package com.example.finalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    DatabaseHelper mDatabase;
    Button add;
    ListView lv;
    List<User> userList=new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Register");
        add=findViewById(R.id.btnAdd);
        lv=findViewById(R.id.lvUsers);

        mDatabase = new DatabaseHelper(this);
        loadEmployees();
       // lv.setAdapter(new UserAdapter(this,));

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,Register.class);
                startActivity(intent);
            }

        });

    }
    private void loadEmployees() {

        Cursor cursor = mDatabase.getAllUsers();

        if (cursor.moveToFirst()) {
            do {
                userList.add(new User(
                        cursor.getInt(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getString(3),
                        Utility.getPhoto(cursor.getBlob(4))
                ));
            } while (cursor.moveToNext());
            cursor.close();

            // show items in a listView
            // we use a custom adapter to show employees

            //UserAdapter userAdapter = new UserAdapter(this, R.layout.list_row, userList, mDatabase);
//            UserAdapter userAdapter = new UserAdapter(this,userList, mDatabase);
//            lv.setAdapter(userAdapter);
            lv.setAdapter(new UserAdapter(this,R.layout.list_row,userList,mDatabase));
            lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    Intent in=new Intent(MainActivity.this,UserDetails.class);
                    //User obj = userList[i];
                    in.putExtra("name", userList.get(i).getName());
                    in.putExtra("phone", userList.get(i).getPhone());
                    in.putExtra("email", userList.get(i).getEmail());
                    in.putExtra("image",Utility.getBytes(userList.get(i).getImage()) );
                    startActivity(in);
                   // iv.setImageResource(carList.get(i).getImage());
                }
            });
        }
    }
}
