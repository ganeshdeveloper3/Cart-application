package com.testapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;


import com.testapplication.databinding.ActivityMainBinding;
import com.testapplication.utils.DataBaseHelper;
import com.testapplication.view.AddProductActivity;
import com.testapplication.viewmodel.BooksViewModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    private BooksViewModel booksViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        booksViewModel = ViewModelProviders.of(this).get(BooksViewModel.class);

        binding.btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddProductActivity.class);
                startActivity(intent);
            }
        });

        binding.btnSync.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DataBaseHelper dataBaseHelper = new DataBaseHelper(MainActivity.this);
                Cursor cursor = dataBaseHelper.getAllData();  //cursor hold all your data
                JSONObject jobj;
                JSONArray arr = new JSONArray();
                cursor.moveToFirst();
                while (cursor.moveToNext()) {
                    jobj = new JSONObject();
                    try {
                        jobj.put("Id", cursor.getInt(0));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    try {
                        jobj.put("product_name", cursor.getString(1));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    try {
                        jobj.put("product_desc", cursor.getString(2));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    try {
                        jobj.put("product_quantity", cursor.getString(3));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    try {
                        jobj.put("product_price", cursor.getString(4));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    try {
                        jobj.put("user_mobile_no", cursor.getString(5));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    arr.put(jobj);
                }

         jobj = new JSONObject();
                try {
                    jobj.put("data", arr);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                String st = jobj.toString();
                Log.e("st123", "st123 " + st);

                booksViewModel.sendProductToServer(jobj);


            }
        });


    }
}