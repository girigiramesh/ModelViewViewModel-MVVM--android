package com.example.modelviewviewmodelmvvm_android.data;

import retrofit2.http.GET;
import retrofit2.http.Url;
import rx.Observable;

/**
 * Created by Ramesh on 2/1/17.
 */

public interface PeopleService {

    @GET
    Observable<PeopleResponse> fetchPeople(@Url String url);
}
