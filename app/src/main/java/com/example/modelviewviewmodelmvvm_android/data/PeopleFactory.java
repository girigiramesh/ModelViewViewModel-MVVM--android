package com.example.modelviewviewmodelmvvm_android.data;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Ramesh on 2/1/17.
 */

public class PeopleFactory {
    private final static String BASE_URL = "http://api.randomuser.me/";

    public static PeopleService create() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        return retrofit.create(PeopleService.class);
    }
}
