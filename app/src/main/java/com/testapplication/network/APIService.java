package com.testapplication.network;

import com.google.gson.JsonObject;
import com.testapplication.model.BookModel;

import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface APIService {

    @GET("getAllAvailableBooks")
    Call<BookModel>getBookList();

    @POST("addNewProduct")
    Call<JSONObject> updateorder(@Body JSONObject object);
}
