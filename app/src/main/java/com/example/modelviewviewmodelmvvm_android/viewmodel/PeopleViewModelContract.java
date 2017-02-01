package com.example.modelviewviewmodelmvvm_android.viewmodel;

import android.content.Context;

import com.example.modelviewviewmodelmvvm_android.model.People;

import java.util.List;

/**
 * Created by Ramesh on 2/1/17.
 */

public interface PeopleViewModelContract {

    interface MainView {

        Context getContext();

        void loadData(List<People> peoples);
    }

    interface ViewModel {

        void destroy();
    }
}
