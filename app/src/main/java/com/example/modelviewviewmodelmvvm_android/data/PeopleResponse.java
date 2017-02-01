package com.example.modelviewviewmodelmvvm_android.data;

import com.example.modelviewviewmodelmvvm_android.model.People;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Ramesh on 2/1/17.
 */

public class PeopleResponse {

    @SerializedName("results")
    private List<People> peopleList;

    public List<People> getPeopleList() {
        return peopleList;
    }

    public void setPeopleList(List<People> peopleList) {
        this.peopleList = peopleList;
    }
}
