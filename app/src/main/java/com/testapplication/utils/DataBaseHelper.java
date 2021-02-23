package com.testapplication.utils;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.testapplication.model.ProductModel;

import java.util.ArrayList;
import java.util.List;

public class DataBaseHelper extends SQLiteOpenHelper {
    public static final String PRODUCT_TABLE = "PRODUCT_TABLE";
    public static final String COLUMN_PRODUCT_NAME = "PRODUCT_NAME";
    public static final String COLUMN_PRODUCT_DESC = "PRODUCT_DESC";
    public static final String COLUMN_PRODUCT_QUANTITY = "PRODUCT_QUANTITY";
    public static final String COLUMN_PRODUCT_PRICE = "PRODUCT_PRICE";
    public static final String COLUMN_ID = "ID";
    public static final String COLUMN_USER_MOB = "COLUMN_USER_MOB";

    public DataBaseHelper(@Nullable Context context) {
        super(context, "product.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableStatement = "CREATE TABLE " + PRODUCT_TABLE + "(" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_PRODUCT_NAME + " TEXT, " + COLUMN_PRODUCT_DESC + " TEXT, " + COLUMN_PRODUCT_QUANTITY + " TEXT, " + COLUMN_PRODUCT_PRICE + " TEXT, " + COLUMN_USER_MOB + " TEXT)";
        db.execSQL(createTableStatement);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public boolean addOne(ProductModel productModel)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_PRODUCT_NAME,productModel.getProduct_name());
        cv.put(COLUMN_PRODUCT_DESC,productModel.getProduct_desc());
        cv.put(COLUMN_PRODUCT_QUANTITY,productModel.getProduct_quantity());
        cv.put(COLUMN_PRODUCT_PRICE,productModel.getProduct_price());
        cv.put(COLUMN_USER_MOB,productModel.getUser_mobile_no());

        long result =   db.insert(PRODUCT_TABLE,null,cv);
        if (result == -1)
        {
            return false;
        }else {
            return true;

        }
    }

    public List<ProductModel>getProductList()
    {
        List<ProductModel> returnList = new ArrayList<>();
        String queryString = "SELECT * FROM "+PRODUCT_TABLE;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(queryString,null);
        if (cursor.moveToFirst())
        {
            do {
                int productId = cursor.getInt(0);
                String productName = cursor.getString(1);
                String productDesc = cursor.getString(2);
                String productQuantity = cursor.getString(3);
                String productPrice = cursor.getString(4);
                String userMobNo = cursor.getString(5);
                ProductModel productModel = new ProductModel(productId,productName,productDesc,productQuantity,productPrice,userMobNo);
                returnList.add(productModel);
            }while (cursor.moveToNext());

        }else {

        }
        cursor.close();
        db.close();

        return returnList;
    }
}
