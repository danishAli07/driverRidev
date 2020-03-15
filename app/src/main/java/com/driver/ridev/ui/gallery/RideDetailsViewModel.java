package com.driver.ridev.ui.gallery;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class RideDetailsViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public RideDetailsViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is Rider details fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}