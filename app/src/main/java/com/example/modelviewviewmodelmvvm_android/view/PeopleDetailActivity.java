package com.example.modelviewviewmodelmvvm_android.view;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;

import com.example.modelviewviewmodelmvvm_android.R;
import com.example.modelviewviewmodelmvvm_android.databinding.ActivityPeopleDetailBinding;
import com.example.modelviewviewmodelmvvm_android.model.People;
import com.example.modelviewviewmodelmvvm_android.viewmodel.PeopleDetailViewModel;
import com.example.modelviewviewmodelmvvm_android.viewmodel.PeopleDetailViewModelContract;

/**
 * Created by Ramesh on 2/1/17.
 */

public class PeopleDetailActivity extends AppCompatActivity implements PeopleDetailViewModelContract.DetailView {
    private static final String EXTRA_PEOPLE = "EXTRA_PEOPLE";
    private ActivityPeopleDetailBinding activityPeopleDetailBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityPeopleDetailBinding = DataBindingUtil.setContentView(this, R.layout.activity_people_detail);
        setSupportActionBar(activityPeopleDetailBinding.toolbar);
        displayHomeAsUpEnabled();
        getExtrasFromIntent();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public Context getContext() {
        return PeopleDetailActivity.this;
    }

    public static Intent launchDetail(Context context, People people) {
        Intent intent = new Intent(context, PeopleDetailActivity.class);
        intent.putExtra(EXTRA_PEOPLE, people);
        return intent;
    }

    private void displayHomeAsUpEnabled() {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) actionBar.setDisplayHomeAsUpEnabled(true);
    }

    private void getExtrasFromIntent() {
        People people = (People) getIntent().getSerializableExtra(EXTRA_PEOPLE);
        PeopleDetailViewModel peopleDetailViewModel = new PeopleDetailViewModel(people);
        activityPeopleDetailBinding.setPeopleDetailViewModel(peopleDetailViewModel);
        setTitle(people.name.title + "." + people.name.firts + " " + people.name.last);
    }
}
