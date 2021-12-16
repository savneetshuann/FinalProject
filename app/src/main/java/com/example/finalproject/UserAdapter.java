package com.example.finalproject;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class UserAdapter extends ArrayAdapter {
    Context context;
    int layoutRes;
    LayoutInflater inflater;
    List<User> users;
    DatabaseHelper mDatabase;

    public UserAdapter(Context context,int layoutRes,List<User> users,DatabaseHelper mDatabase)
    {
        super(context,layoutRes,users);
        this.context=context;
        this.layoutRes=layoutRes;
        this.users=users;
        this.mDatabase=mDatabase;
        //inflater=LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return users.size();
    }

    @Override
    public Object getItem(int i) {
        return users.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View v = inflater.inflate(layoutRes, null);
        TextView tvName = v.findViewById(R.id.txvName);
        TextView tvPhone = v.findViewById(R.id.txvPhone);
        TextView tvEmail = v.findViewById(R.id.txvEmail);
        ImageView tvImg = v.findViewById(R.id.imgUser);

        final User user = users.get(position);
        tvName.setText(user.getName());
        tvPhone.setText(user.getPhone());
        tvEmail.setText(user.getEmail());
        tvImg.setImageBitmap(user.getImage());
        //tvImg.setImageResource(user.getImage());
        return v;
    }
    /*
        //try
        int[] images = new int[] {R.drawable.icon01_01, R.drawable.icon01_02, R.drawable.icon01_03,R.drawable.icon01_04, R.drawable.icon01_05, R.drawable.icon01_06,
                R.drawable.icon01_07, R.drawable.icon01_08, R.drawable.icon01_09,R.drawable.icon01_10, R.drawable.icon01_11, R.drawable.icon01_12,
                R.drawable.icon01_13, R.drawable.icon01_14, R.drawable.icon01_15,R.drawable.icon01_16, R.drawable.icon01_17, R.drawable.icon01_18};
        ImageView mImageView = v.findViewById(R.id.imgUser);

        int imageId = (int)(Math.random() * images.length);// Get a random between 0 and images.length-1
        mImageView.setBackgroundResource(images[imageId]);// Set the image

         */



//    @Override
//    public View getView(int i, View view, ViewGroup viewGroup) {
//        ViewHolder holder;
//        if(view==null){
//            view=inflater.inflate(R.layout.list_row,null);
//            holder=new ViewHolder();
//            holder.name=view.findViewById(R.id.txvName);
//            holder.phone=view.findViewById(R.id.txvPhone);
//            holder.email=view.findViewById(R.id.txvEmail);
//            view.setTag(holder);
//        }
//        else
//            holder=(ViewHolder) view.getTag();
//        holder.name.setText(users.get(i).getName());
//        holder.phone.setText(users.get(i).getPhone());
//        holder.email.setText(users.get(i).getEmail());
//        return view;
//    }


    private void loadEmployees() {
        Cursor cursor = mDatabase.getAllUsers();
        users.clear();
        if (cursor.moveToFirst()) {

            do {
                users.add(new User(
                        cursor.getInt(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getString(3),
                        Utility.getPhoto(cursor.getBlob(4))

                ));
            } while (cursor.moveToNext());
            cursor.close();
        }
        notifyDataSetChanged();
    }
}
