package com.testapplication.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.testapplication.R;
import com.testapplication.databinding.ActivityAddProductBinding;
import com.testapplication.model.ProductModel;
import com.testapplication.utils.DataBaseHelper;

public class AddProductActivity extends AppCompatActivity implements View.OnClickListener {
    String productName, productDesc, productQuantity, productPrice;
    ActivityAddProductBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(AddProductActivity.this, R.layout.activity_add_product);
        binding.btnSave.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        productName = binding.editTextProductName.getText().toString();
        productDesc = binding.editTextProductDesc.getText().toString();
        productQuantity = binding.editTextProductQuantity.getText().toString();
        productPrice = binding.editTextProductPrice.getText().toString();
        if (productName.isEmpty()) {
            binding.editTextProductName.setError("Product Name Should not be blank");
            return;
        } else if (productDesc.isEmpty()) {
            binding.editTextProductDesc.setError("Product Description Should not be blank");
            return;
        } else if (productQuantity.isEmpty()) {
            binding.editTextProductQuantity.setError("Product Quantity Should not be blank");
            return;
        } else if (productPrice.isEmpty()) {
            binding.editTextProductPrice.setError("Product Price Should not be blank");
            return;

        } else {
            ProductModel productModel = null;
            try {
                productModel = new ProductModel(-1, productName, productDesc, productQuantity, productPrice, "9591608905");
                //   Toast.makeText(AddProductActivity.this,productModel.toString(),Toast.LENGTH_LONG).show();
            } catch (Exception e) {
                Toast.makeText(AddProductActivity.this, "Error creating product", Toast.LENGTH_LONG).show();
                productModel = new ProductModel(-1, "error", "error", "0", "0", "0");
            }
            DataBaseHelper dataBaseHelper = new DataBaseHelper(AddProductActivity.this);
            boolean success = dataBaseHelper.addOne(productModel);
            if (success == true) {
                Intent intent = new Intent(AddProductActivity.this, SuccessActivity.class);
                startActivity(intent);
                finish();

            } else {
                Toast.makeText(AddProductActivity.this, "error", Toast.LENGTH_LONG).show();
            }

        }


    }
}