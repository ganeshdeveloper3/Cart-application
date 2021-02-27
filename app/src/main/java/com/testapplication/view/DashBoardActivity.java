package com.testapplication.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.testapplication.MainActivity;
import com.testapplication.R;
import com.testapplication.databinding.ActivityDashBoardBinding;

public class DashBoardActivity extends AppCompatActivity {

    ActivityDashBoardBinding boardBinding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        boardBinding = DataBindingUtil.setContentView(DashBoardActivity.this, R.layout.activity_dash_board);
        boardBinding.btnBookList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DashBoardActivity.this, ListBookActivity.class);
                startActivity(intent);
            }
        });

        boardBinding.btnAddProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DashBoardActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}