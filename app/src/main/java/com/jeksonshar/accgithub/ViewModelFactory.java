package com.jeksonshar.accgithub;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public class ViewModelFactory extends ViewModelProvider.NewInstanceFactory {
    private final ChoiceFragment.Clicked clicked;
    private final String USER_LOGIN;

    ViewModelFactory(ChoiceFragment.Clicked clicked, String userLogin) {
        super();
        this.clicked = clicked;
        this.USER_LOGIN = userLogin;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass == AccGitHubViewModel.class) {
            return (T) new AccGitHubViewModel(clicked, USER_LOGIN);
        }
        return super.create(modelClass);
    }
}
