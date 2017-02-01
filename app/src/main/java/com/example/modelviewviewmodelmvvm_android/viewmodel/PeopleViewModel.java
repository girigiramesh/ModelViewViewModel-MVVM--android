package com.example.modelviewviewmodelmvvm_android.viewmodel;

import android.content.Context;
import android.databinding.ObservableField;
import android.databinding.ObservableInt;
import android.support.annotation.Nullable;
import android.view.View;

import com.example.modelviewviewmodelmvvm_android.PeopleApplication;
import com.example.modelviewviewmodelmvvm_android.R;
import com.example.modelviewviewmodelmvvm_android.data.PeopleResponse;
import com.example.modelviewviewmodelmvvm_android.data.PeopleService;

import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;

/**
 * Created by Ramesh on 2/1/17.
 */

public class PeopleViewModel implements PeopleViewModelContract.ViewModel {
    public ObservableInt peopleProgress, peopleList, peopleLabel;
    public ObservableField<String> messageLabel;
    private PeopleViewModelContract.MainView mainView;
    private Context context;
    private Subscription subscription;

    public PeopleViewModel(@Nullable PeopleViewModelContract.MainView mainView, @Nullable Context context) {
        this.mainView = mainView;
        this.context = context;
        peopleProgress = new ObservableInt(View.GONE);
        peopleList = new ObservableInt(View.GONE);
        peopleLabel = new ObservableInt(View.VISIBLE);
        if (context != null) {
            messageLabel = new ObservableField<>(context.getString(R.string.default_loading_people));
        }
    }

    @Override
    public void destroy() {
        reset();
    }

    public void onClickFabLoad(View view) {
        initializeViews();
        fetchPeopleList();
    }

    private void fetchPeopleList() {
        final String URL = "http://api.randomuser.me/?results=10&nat=en";
        unSubscribeFromObservable();
        PeopleApplication peopleApplication = PeopleApplication.create(context);
        PeopleService peopleService = peopleApplication.getPeopleService();
        subscription = peopleService.fetchPeople(URL)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(peopleApplication.subscribeScheduler())
                .subscribe(new Action1<PeopleResponse>() {
                    @Override
                    public void call(PeopleResponse peopleResponse) {
                        peopleProgress.set(View.GONE);
                        peopleLabel.set(View.GONE);
                        peopleList.set(View.VISIBLE);
                        if (mainView != null) {
                            mainView.loadData(peopleResponse.getPeopleList());
                        }
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        throwable.printStackTrace();
                        messageLabel.set(context.getString(R.string.error_loading_people));
                        peopleProgress.set(View.GONE);
                        peopleLabel.set(View.VISIBLE);
                        peopleList.set(View.GONE);
                    }
                });
    }

    public void initializeViews() {
        peopleLabel.set(View.GONE);
        peopleList.set(View.GONE);
        peopleProgress.set(View.VISIBLE);
    }

    private void unSubscribeFromObservable() {
        if (subscription != null && !subscription.isUnsubscribed()) {
            subscription.unsubscribe();
        }
    }

    private void reset() {
        unSubscribeFromObservable();
        subscription = null;
        context = null;
        mainView = null;
    }
}
