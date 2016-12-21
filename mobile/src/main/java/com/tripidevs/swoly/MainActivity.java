package com.tripidevs.swoly;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;

import static com.tripidevs.swoly.R.id;

public class MainActivity extends AppCompatActivity {

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
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

    public void percentageClick(View view) {
        Button button = (Button) view;
        TextView sides = (TextView) findViewById(id.txtEachSide);
        EditText MaxWeight = (EditText) findViewById(id.maxweight);
        float eachSideWeight;
        int maxWeight;
        float percentage = Float.parseFloat(button.getText().toString())/100;
        Context context = view.getContext();
        try
        {
            maxWeight = Integer.parseInt(MaxWeight.getText().toString());
            eachSideWeight = ((maxWeight*percentage)-45)/2;
            sides.setText(String.valueOf(eachSideWeight));
        }
        catch(Exception e)
        {
            CharSequence msg = "Please enter a number";
            Toast toast = Toast.makeText(context, msg, Toast.LENGTH_LONG);
            toast.show();
        }
    }
    //Method for adding and subtracting weight
    public void changeWeight(View v)
    {
        boolean plus = v.equals(findViewById(id.pluscircle));
        int curr = 0;
        EditText currWeight = (EditText) findViewById(id.maxweight);
        curr = Integer.parseInt(currWeight.getText().toString());
        if(plus)
            curr+=5;
        else
            curr-=5;

        currWeight.setText(String.valueOf(curr));
    }

    //Function to switch the activity to settings
    public void switchActivity(View v) {
        Intent intentSettings = new Intent(this, Settings.class);
        startActivity(intentSettings);
    }

    //Function to call the custom alert dialog
    public void customPercentClick(View v)
    {
        customPercent dialog = new customPercent();
        dialog.show(getFragmentManager(), "custom_dialog");
    }
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    public Action getIndexApiAction() {
        Thing object = new Thing.Builder()
                .setName("Main Page") // TODO: Define a title for the content shown.
                // TODO: Make sure this auto-generated URL is correct.
                .setUrl(Uri.parse("http://[ENTER-YOUR-URL-HERE]"))
                .build();
        return new Action.Builder(Action.TYPE_VIEW)
                .setObject(object)
                .setActionStatus(Action.STATUS_TYPE_COMPLETED)
                .build();
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        AppIndex.AppIndexApi.start(client, getIndexApiAction());
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        AppIndex.AppIndexApi.end(client, getIndexApiAction());
        client.disconnect();
    }
}
