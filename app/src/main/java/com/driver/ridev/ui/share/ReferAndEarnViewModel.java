package com.driver.ridev.ui.share;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ReferAndEarnViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public ReferAndEarnViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is Refer and Earn fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}