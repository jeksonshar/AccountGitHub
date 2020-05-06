package com.jeksonshar.accgithub;

import android.os.AsyncTask;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.jeksonshar.accgithub.okhttp.NetworkingWithOkHttp;
import com.jeksonshar.accgithub.retrofit.NetworkingWithRetrofit;

import java.util.concurrent.ExecutionException;

class AccGitHubViewModel extends ViewModel {

    private final ChoiceFragment.Clicked clicked;
    private final String USER_LOGIN;

    private MutableLiveData<AccGitHuber> mStore;

    AccGitHubViewModel(ChoiceFragment.Clicked clicked, String userLogin) {
        this.clicked = clicked;
        this.USER_LOGIN = userLogin;
    }

    LiveData<AccGitHuber> getRequest() throws ExecutionException, InterruptedException {
        if (mStore == null) {
            mStore = new MutableLiveData<>();
            setRequest();
        }
        return mStore;
    }

    private void setRequest() throws ExecutionException, InterruptedException {
        mStore.postValue((new InternetRequestTask().execute()).get());
    }


    // The class for a background task
    private class InternetRequestTask extends AsyncTask<Void, Void, AccGitHuber> {

        // Method will be called in background thread
        @Override
        protected AccGitHuber doInBackground(Void... voids) {
            return executeRequest();
        }
    }

    private AccGitHuber executeRequest() {
        if (clicked.equals(ChoiceFragment.Clicked.OK_HTTP)) {
            return NetworkingWithOkHttp.makeRequest(USER_LOGIN);
        } else {
            return NetworkingWithRetrofit.makeRequest(USER_LOGIN);
        }
    }
}
