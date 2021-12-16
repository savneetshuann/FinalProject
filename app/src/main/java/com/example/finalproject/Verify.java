package com.example.finalproject;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

public class Verify extends AppCompatActivity {
    ImageButton img1, img2, img3, img4, img5, img6, img7, img8, img9, refresh;
    Boolean img1Checked = false;
    Boolean img2Checked = false;
    Boolean img3Checked = false;
    Boolean img4Checked = false;
    Boolean img5Checked = false;
    Boolean img6Checked = false;
    Boolean img7Checked = false;
    Boolean img8Checked = false;
    Boolean img9Checked = false;
    Boolean isRefresh=false;
    Button verify;
    CheckBox robot;

    DatabaseHelper mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verify);
        setTitle("Register");
        img1 = findViewById(R.id.imgButton1);
        img2 = findViewById(R.id.imgButton2);
        img3 = findViewById(R.id.imgButton3);
        img4 = findViewById(R.id.imgButton4);
        img5 = findViewById(R.id.imgButton5);
        img6 = findViewById(R.id.imgButton6);
        img7 = findViewById(R.id.imgButton7);
        img8 = findViewById(R.id.imgButton8);
        img9 = findViewById(R.id.imgButton9);
        robot = findViewById(R.id.chkRobot);
        refresh = findViewById(R.id.imgRefresh);
        verify = findViewById(R.id.btnVerify);

        mDatabase = new DatabaseHelper(this);

        img1.setOnClickListener(new ButtonsEvents());
        img2.setOnClickListener(new ButtonsEvents());
        img3.setOnClickListener(new ButtonsEvents());
        img4.setOnClickListener(new ButtonsEvents());
        img5.setOnClickListener(new ButtonsEvents());
        img6.setOnClickListener(new ButtonsEvents());
        img7.setOnClickListener(new ButtonsEvents());
        img8.setOnClickListener(new ButtonsEvents());
        img9.setOnClickListener(new ButtonsEvents());

        img1.setBackgroundResource(R.drawable.img1);
        img1.setTag("chk1");
        img2.setBackgroundResource(R.drawable.img2);
        img2.setTag("chk2");
        img3.setBackgroundResource(R.drawable.img3);
        img3.setTag("chk3");
        img4.setBackgroundResource(R.drawable.img4);
        img4.setTag("chk4");
        img5.setBackgroundResource(R.drawable.img5);
        img6.setBackgroundResource(R.drawable.img6);
        img7.setBackgroundResource(R.drawable.img7);
        img8.setBackgroundResource(R.drawable.img8);
        img9.setBackgroundResource(R.drawable.img9);


//Todo
       refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (img1Checked || img2Checked || img3Checked || img4Checked || img5Checked || img6Checked || img7Checked || img8Checked || img9Checked) {
                    img1Checked = false;
                    img2Checked = false;
                    img3Checked = false;
                    img4Checked = false;
                    img5Checked = false;
                    img6Checked = false;
                    img7Checked = false;
                    img8Checked = false;
                    img9Checked = false;
                    isRefresh=true;
                    img1.setImageResource(android.R.color.transparent);
                    img2.setImageResource(android.R.color.transparent);
                    img3.setImageResource(android.R.color.transparent);
                    img4.setImageResource(android.R.color.transparent);
                    img5.setImageResource(android.R.color.transparent);
                    img6.setImageResource(android.R.color.transparent);
                    img7.setImageResource(android.R.color.transparent);
                    img8.setImageResource(android.R.color.transparent);
                    img9.setImageResource(android.R.color.transparent);
                }
                System.out.println("----------->clicked");
                img1.setBackgroundResource(R.drawable.img1);
                img1.setTag("chk1");
                img2.setBackgroundResource(R.drawable.img2);
                img2.setTag("chk2");
                img3.setBackgroundResource(R.drawable.img9);
                img4.setBackgroundResource(R.drawable.img8);
                img5.setBackgroundResource(R.drawable.img5);
                img6.setBackgroundResource(R.drawable.img6);
                img7.setBackgroundResource(R.drawable.img7);
                img8.setBackgroundResource(R.drawable.img3);
                img8.setTag("chk3");
                img9.setBackgroundResource(R.drawable.img4);
                img9.setTag("chk4");


            }

        });

        verify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if ((img1Checked && img2Checked && img3Checked && img4Checked && robot.isChecked())||(isRefresh && img1Checked && img2Checked && img8Checked && img9Checked && robot.isChecked())) {//&& robot.isChecked()

                    AlertDialog.Builder builder = new AlertDialog.Builder(Verify.this);
                    builder.setMessage("Verified")
                            .setCancelable(false)
                            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    addUser();
                                    Intent intent2 = new Intent(Verify.this, MainActivity.class);
                                    startActivity(intent2);
                                }
                            });
                    AlertDialog alert = builder.create();
                    alert.show();
                } else {
                    AlertDialog.Builder builder = new AlertDialog.Builder(Verify.this);
                    builder.setMessage("Not Verified")
                            .setCancelable(false)
                            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    Intent intent2 = new Intent(Verify.this, MainActivity.class);
                                    startActivity(intent2);
                                }
                            });
                    AlertDialog alert = builder.create();
                    alert.show();
                }
            }


        });
    }

    private class ButtonsEvents implements View.OnClickListener {

        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.imgButton1:
                    if (!img1Checked) {
                        System.out.println("----------->" + view.getTag());
                        img1.setImageResource(R.drawable.checked);
                        img1Checked = true;
                    } else {
                        img1.setImageResource(R.drawable.img1);
                        img1Checked = false;
                    }
                    break;
                case R.id.imgButton2:
                    if (!img2Checked) {
                        img2.setImageResource(R.drawable.checked);
                        img2Checked = true;
                    } else {
                        img2.setImageResource(R.drawable.img2);
                        img2Checked = false;
                    }
                    break;
                case R.id.imgButton3:
                    if (!img3Checked) {
                        img3.setImageResource(R.drawable.checked);
                        img3Checked = true;
                    } else {
                        img3.setImageResource(R.drawable.img3);
                        img3Checked = false;
                    }
                    break;
                case R.id.imgButton4:
                    if (!img4Checked) {
                        img4.setImageResource(R.drawable.checked);
                        img4Checked = true;
                    } else {
                        img4.setImageResource(R.drawable.img4);
                        img4Checked = false;
                    }
                    break;
                case R.id.imgButton5:
                    if (!img5Checked) {
                        img5.setImageResource(R.drawable.checked);
                        img5Checked = true;
                    } else {
                        img5.setImageResource(R.drawable.img5);
                        img5Checked = false;
                    }
                    break;
                case R.id.imgButton6:
                    if (!img6Checked) {
                        img6.setImageResource(R.drawable.checked);
                        img6Checked = true;
                    } else {
                        img6.setImageResource(R.drawable.img6);
                        img6Checked = false;
                    }
                    break;
                case R.id.imgButton7:
                    if (!img7Checked) {
                        img7.setImageResource(R.drawable.checked);
                        img7Checked = true;
                    } else {
                        img7.setImageResource(R.drawable.img7);
                        img7Checked = false;
                    }
                    break;

                case R.id.imgButton8:
                    if (!img8Checked) {
                        img8.setImageResource(R.drawable.checked);
                        img8Checked = true;
                    } else {
                        img8.setImageResource(R.drawable.img8);
                        img8Checked = false;
                    }
                    break;
                case R.id.imgButton9:
                    if (!img9Checked) {
                        img9.setImageResource(R.drawable.checked);
                        img9Checked = true;
                    } else {
                        img9.setImageResource(R.drawable.img9);
                        img9Checked = false;
                    }
                    break;

            }

        }
    }

    private void addUser() {
        System.out.println("in add" + Register.tName);
        int[] images = new int[]{R.drawable.icon01_01, R.drawable.icon01_02, R.drawable.icon01_03, R.drawable.icon01_04, R.drawable.icon01_05, R.drawable.icon01_06,
                R.drawable.icon01_07, R.drawable.icon01_08, R.drawable.icon01_09, R.drawable.icon01_10, R.drawable.icon01_11, R.drawable.icon01_12,
                R.drawable.icon01_13, R.drawable.icon01_14, R.drawable.icon01_15, R.drawable.icon01_16, R.drawable.icon01_17, R.drawable.icon01_18,
                R.drawable.icon01_19, R.drawable.icon01_20, R.drawable.icon01_21, R.drawable.icon01_22, R.drawable.icon01_23, R.drawable.icon01_24,
                R.drawable.icon01_25, R.drawable.icon01_26, R.drawable.icon01_27, R.drawable.icon01_28, R.drawable.icon01_29, R.drawable.icon01_30};


        int imageId = (int) (Math.random() * images.length);// Get a random between 0 and images.length-1
        Bitmap img = BitmapFactory.decodeResource(getResources(), images[imageId]);// Set the image


        if (mDatabase.addUsers(Register.tName, Register.tPhone, Register.tEmail, img))
            Toast.makeText(this, "User Registered", Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(this, "User not Registered", Toast.LENGTH_SHORT).show();

    }
}