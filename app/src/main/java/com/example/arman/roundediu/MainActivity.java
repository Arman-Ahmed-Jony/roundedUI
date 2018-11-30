package com.example.arman.roundediu;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity implements LoginFragment.UserAuthCompleteListenner {
private FragmentManager manager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        manager=getSupportFragmentManager();
        FragmentTransaction ft=manager.beginTransaction();
        LoginFragment loginFragment=new LoginFragment();
        ft.add(R.id.fragmentContainer,loginFragment);
        ft.commit();


    }

    @Override
    public void onAuthenticationComplete() {
        FragmentTransaction ft=manager.beginTransaction();
        EventListFragment eventlistFragment=new EventListFragment();
        ft.replace(R.id.fragmentContainer,eventlistFragment);
        ft.setTransition(FragmentTransaction.TRANSIT_ENTER_MASK);
        ft.commit();

    }
}
