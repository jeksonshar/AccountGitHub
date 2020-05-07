package com.jeksonshar.accgithub;

import android.os.AsyncTask;

import androidx.annotation.MainThread;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.jeksonshar.accgithub.okhttp.NetworkingWithOkHttp;
import com.jeksonshar.accgithub.retrofit.NetworkingWithRetrofit;

class AccGitHubViewModel extends ViewModel {

    private final AccGitHubFragment.ChoiceOfRequest choice;
    private final String userLogin;

    private MutableLiveData<AccGitHuber> mStore;

    AccGitHubViewModel(AccGitHubFragment.ChoiceOfRequest choice, String userLogin) {
        this.choice = choice;
        this.userLogin = userLogin;
    }

    LiveData<AccGitHuber> getRequest()  {
        if (mStore == null) {
            mStore = new MutableLiveData<>();
            setRequest();
        }
        return mStore;
    }

    @MainThread
    private void setRequest()  {
        new InternetRequestTask().execute();
    }

    // The class for a background task
    private class InternetRequestTask extends AsyncTask<Void, Void, AccGitHuber> {

        // Method will be called in background thread
        @Override
        protected AccGitHuber doInBackground(Void... voids) {
            return executeRequest();
        }

        // Method will be called in main thread after the doInBackground() has finished
        @Override
        protected void onPostExecute(AccGitHuber accGitHuber) {
            mStore.postValue(accGitHuber);
        }
    }

    private AccGitHuber executeRequest() {
        if (choice.equals(AccGitHubFragment.ChoiceOfRequest.OK_HTTP)) {
            return NetworkingWithOkHttp.makeRequest(userLogin);
        } else {
            return NetworkingWithRetrofit.makeRequest(userLogin);
        }
    }
}
