package com.jeksonshar.accgithub;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class AccGitHubFragment extends Fragment {

private AccGitHuber mAccGitHuber;

private ImageView avatarView;
private TextView userLoginView;
private TextView userId;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAccGitHuber = new AccGitHuber(ChoiceFragment.USER_LOGIN);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(
                R.layout.fragment_acc_github,
                container,
                false);

        userLoginView = v.findViewById(R.id.user_login_view);
        userId = v.findViewById(R.id.user_id_view);
        avatarView = v.findViewById(R.id.user_avatar_view);

        userLoginView.setText(mAccGitHuber.getLogin());
        userId.setText( String.valueOf(mAccGitHuber.getId()));

        Picasso.get().load(mAccGitHuber.getAvatarUrl()).into(avatarView);

        return  v;
    }

    public static AccGitHubFragment newInstance(String string) {
        Bundle args = new Bundle();
        args.putString("key", string);
        AccGitHubFragment fragment = new AccGitHubFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public static Intent makeIntent(Context context) {
        Intent intent = new Intent(context, AccGitHubFragment.class);
        return intent;
    }
}
