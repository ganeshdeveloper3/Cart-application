package com.testapplication.network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetroInstance {

    //http://aryu.co.in/tracking/viewreport

    //http://15.206.209.151/api/getAllAvailableBooks

    public static String BASE_URL ="http://15.206.209.151/api/";
    private static Retrofit retrofit;

    public static Retrofit getRetroClient()
    {
        if (retrofit == null)
        {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
