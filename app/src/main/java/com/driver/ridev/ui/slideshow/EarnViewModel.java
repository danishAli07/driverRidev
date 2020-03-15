package com.driver.ridev.ui.slideshow;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class EarnViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public EarnViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is Total Earn fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}