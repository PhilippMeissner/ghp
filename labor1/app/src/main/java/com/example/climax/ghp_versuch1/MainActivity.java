package com.example.climax.ghp_versuch1;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;


public class MainActivity extends ActionBarActivity {

    private SharedPreferences prefs;
    private EditText editText;

    Button btn_ok;
    private final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop wurde aufgerufen.");

        editText = (EditText) findViewById(R.id.editText);

        prefs = getPreferences(MODE_PRIVATE);


        SharedPreferences.Editor editor = prefs.edit();
        //---save the values in the EditText view to preferences---
        editor.putString("test1", editText.getText().toString());
        editor.commit();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        editText = (EditText) findViewById(R.id.editText);
        btn_ok = (Button) findViewById(R.id.ok_btn);


        btn_ok.setOnClickListener(new Button.OnClickListener() {

            @Override
            public void onClick(View v) {

                // LogCat Ausgabe erzeugen
                Log.d(TAG, "Button \"btn_ok\" wurde betätigt.");

                //Toast ausgeben
                Context context = getApplicationContext();

                // Text aus string.xml laden
                CharSequence text =  getString(R.string.toast_text);
                int duration = Toast.LENGTH_SHORT;

                Toast toast = Toast.makeText(context, text, duration);
                toast.show();
            }
        });

        // Get Layout the button shall be added to
        //LinearLayout curr_layout = (LinearLayout) View.inflate(this, R.layout.activity_main, null);

        LinearLayout curr_layout = (LinearLayout) findViewById(R.id.linear_layout);
        Button dyn_btn = new Button(this);

        dyn_btn.setText(R.string.dyn_btn_text);

        //dyn_btn.setLayoutParams(curr_layout.getLayoutParams());
        dyn_btn.setLayoutParams(findViewById(R.id.ok_btn).getLayoutParams());

        dyn_btn.setOnClickListener(new Button.OnClickListener() {

            @Override
            public void onClick(View v) {

                // LogCat Ausgabe erzeugen
                Log.d(TAG, "Button \"dyn_btn\" wurde betätigt.");

                //Toast ausgeben
                Context context = getApplicationContext();

                // Text aus string.xml laden
                CharSequence text =  getString(R.string.dyn_toast_text);
                int duration = Toast.LENGTH_LONG;

                Toast toast = Toast.makeText(context, text, duration);
                toast.show();
            }


        });


        curr_layout.addView(dyn_btn);

        prefs = getPreferences(MODE_PRIVATE);

        editText.setText(prefs.getString("test1", ""));


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


}
