package com.jeksonshar.accgithub;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public class ViewModelFactory extends ViewModelProvider.NewInstanceFactory {

    private final AccGitHubFragment.ChoiceOfRequest choice;
    private final String userLogin;

    ViewModelFactory(AccGitHubFragment.ChoiceOfRequest choice, String userLogin) {
        super();
        this.choice = choice;
        this.userLogin = userLogin;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass == AccGitHubViewModel.class) {
            return (T) new AccGitHubViewModel(choice, userLogin);
        }
        return super.create(modelClass);
    }
}
