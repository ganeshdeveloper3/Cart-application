package com.testapplication.viewmodel;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;
import android.widget.Toast;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;


import com.google.gson.JsonObject;
import com.testapplication.model.BookModel;
import com.testapplication.network.APIService;
import com.testapplication.network.RetroInstance;
import com.testapplication.view.SuccessActivity;

import org.json.JSONObject;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class BooksViewModel extends AndroidViewModel {
    private MutableLiveData<BookModel> bookList;
    public BooksViewModel(Application application) {
        super(application);
        bookList = new MutableLiveData<>();
    }



    public MutableLiveData<BookModel> getBookListOberver() {
        return bookList;
    }

    public void makeApiCall() {
        APIService apiService = RetroInstance.getRetroClient().create(APIService.class);
        Call<BookModel> call = apiService.getBookList();
        call.enqueue(new Callback<BookModel>() {
            @Override
            public void onResponse(Call<BookModel> call, Response<BookModel> response) {
                bookList.postValue(response.body());

            }

            @Override
            public void onFailure(Call<BookModel> call, Throwable t) {
                bookList.postValue(null);
            }
        });

    }


    public void sendProductToServer(JSONObject jsonObject) {
        APIService apiService = RetroInstance.getRetroClient().create(APIService.class);

        Call<JSONObject> call = apiService.updateorder(jsonObject);


        call.enqueue(new Callback<JSONObject>() {
            @Override
            public void onResponse(Call<JSONObject> callback, Response<JSONObject> response) {
                String resp = response.message();
                Toast.makeText(getApplication(),"Successfully sent to server",Toast.LENGTH_LONG).show();

            }

            @Override
            public void onFailure(Call<JSONObject> call, Throwable t) {
                Toast.makeText(getApplication(),"Error",Toast.LENGTH_LONG).show();

            }
        });
    }

}
