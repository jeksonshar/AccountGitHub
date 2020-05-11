package com.jeksonshar.accgithub.ui.root;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;

import com.jeksonshar.accgithub.ui.choice.ChoiceFragment;
import com.jeksonshar.accgithub.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentManager fm = getSupportFragmentManager();
        Fragment fragment = fm.findFragmentById(R.id.fragment_container);

        if (fragment == null) {
            fragment = new ChoiceFragment();
            fm.beginTransaction().
                    add(R.id.fragment_container, fragment)
                    .commit();
        }
    }
}