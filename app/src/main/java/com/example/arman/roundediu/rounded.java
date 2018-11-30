package com.example.arman.roundediu;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class rounded extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rounded);
    }

    public void gotoAnotherPage(View view) {
        startActivity(new Intent(this,MainActivity.class));
    }
}
