package com.jeksonshar.accgithub;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class AccGitHubViewModel extends ViewModel {

    private MutableLiveData<AccGitHuber> mStore = new MutableLiveData<>();

    public void saveRequest(AccGitHuber accGitHuber) {
        mStore.postValue(accGitHuber);
    }
    MutableLiveData<AccGitHuber> getRequest() {
        return mStore;
    }
}
