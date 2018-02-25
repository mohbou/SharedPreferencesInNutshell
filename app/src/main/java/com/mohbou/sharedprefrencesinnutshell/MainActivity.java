package com.mohbou.sharedprefrencesinnutshell;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {


    public static final String USER_NAME = "com.mohbou.sharedprefrencesinnutshell.USER_NAME";
    private static final String PREFS_NAME = "com.mohbou.sharedprefrencesinnutshell.prefs";
    SharedPreferences.Editor editor;
    SharedPreferences prefs;
    SharedPreferences prefs_fromXML;

    private EditText userNametxt;
    private Button buttonSend;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        userNametxt =findViewById(R.id.editText);
        buttonSend= findViewById(R.id.buttonSend);


        prefs = getSharedPreferences(PREFS_NAME,MODE_PRIVATE);
        editor = prefs.edit();
        final String pref_value = prefs.getString(USER_NAME, "Mohamed");
        editor.apply();
        userNametxt.setText(pref_value);

        buttonSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,Main2Activity.class);
                intent.putExtra(USER_NAME,userNametxt.getText().toString());
                startActivity(intent);
            }
        });



    }

    @Override
    protected void onPause() {
        super.onPause();
        prefs = getSharedPreferences(PREFS_NAME,MODE_PRIVATE);
        editor = prefs.edit();
        editor.putString(USER_NAME,userNametxt.getText().toString().trim());
        editor.apply();
    }


    @Override
    protected void onResume() {
        super.onResume();
        prefs_fromXML = PreferenceManager.getDefaultSharedPreferences(this);
        final boolean grid = prefs_fromXML.getBoolean(getString(R.string.pref_change_text), false);

        if(grid) {
            Log.d("test", "onCreate: selected");
            userNametxt.setText("selected");
        }
        else {
            Log.d("test", "onCreate: not selected");
            userNametxt.setText("not selected");
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_settings:
                Intent intent = new Intent(this,PrefActivity.class);
                startActivity(intent);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }
}
