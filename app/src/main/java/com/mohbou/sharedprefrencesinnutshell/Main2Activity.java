package com.mohbou.sharedprefrencesinnutshell;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class Main2Activity extends AppCompatActivity {

    private static final String TAG = "result";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        final String extra = getIntent().getStringExtra(MainActivity.USER_NAME);
        Log.d(TAG, "onCreate: "+extra);

    }
}
