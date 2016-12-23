package com.tripidevs.swoly;
/*
    Main activity for mobile
 */
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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
import com.google.android.gms.vision.text.Text;

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

    //Function to change the weight when the plus or minus buttons are pressed
    public void changeWeight(View v) {
        //Code to get text inside accent circle, relating to max weight
        //then removes both elements from the screen once you interact with it.
        TextView maxText = (TextView) findViewById(id.maxText);
        TextView lbsText = (TextView) findViewById(id.lbsText);
        maxText.setVisibility(View.INVISIBLE);
        lbsText.setVisibility(View.INVISIBLE);
        //Bool to check which button is pressed
        //True if plus
        //False if minus
        boolean plus = v.equals(findViewById(id.pluscircle));
        //Max weight text box
        EditText maxWeight = (EditText) findViewById(id.maxweight);
        if(plus)
            Constant.max+=5;    //Adding five to the max value
        if (!plus&&(((((Constant.max)-5) * Constant.percent) - 45) / 2) > 0)
            Constant.max-=5;    //Subtracting five from the max value if the value is grater than 0
        //Setting the text value of the max weight to the value from the constants
        maxWeight.setText(String.valueOf(Constant.max));
        //Calling the function to refresh the value of the each side text
        refreshEachSide();
    }

    //Function to determine change weight when a preset percentage is clicked
    public void percentageClick(View view) {
        //Code to get text inside accent circle, relating to max weight
        //then removes both elements from the screen once you interact with it.
        TextView maxText = (TextView) findViewById(id.maxText);
        TextView lbsText = (TextView) findViewById(id.lbsText);
        maxText.setVisibility(View.INVISIBLE);
        lbsText.setVisibility(View.INVISIBLE);
        //Button object for the button pressed
        Button percent = (Button) view;
        //Changing the value of the constant percentage
        if ((((Constant.max * Constant.percent) - 45) / 2)>0)
            Constant.percent = (Float.parseFloat(percent.getText().toString()))/100;
        refreshEachSide();  //Refreshing the sides text value
    }

    //Function to refresh the value of the each sides text
    public void refreshEachSide() {
        //Object to hold the value of the each side text
        TextView sides = (TextView) findViewById(id.txtEachSide);
        //Double to hold the value of each side
        double eachSideWeight;
        //Setting each side double equal to the result of the appropriate function
        //((Maxweight*percentage)-barweight)/2
        eachSideWeight = ((Constant.max * Constant.percent) - 45) / 2;
        //Checking the value of each side
        //If greater than 0 then value of each side text is changed accordingly
        //Else toast displaying "Please lift more is displayed"
        if (eachSideWeight > 0)
            sides.setText(String.format("%.2f", eachSideWeight));
        else {
            CharSequence msg = "Please lift more";
            Toast toast = Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_LONG);
            toast.show();
        }

        //Setting the value of the current percentage text equal to the current percent
        //Displaying only two decimal places using string format function
        TextView currentPercent = (TextView) findViewById(id.txtCurrentPercent);
        currentPercent.setText(String.valueOf(Math.round(Constant.percent*100)));
    }

    //Function that will switch the main activity to the settings activity
    public void switchActivity(View v) {
        Intent intentSettings = new Intent(this, Settings.class);
        startActivity(intentSettings);
    }

    //Function to call the custom alert dialog
    public void customPercentClick(View v)
    {
        //Creating a new instance of the customPercent class
        customPercent dialog = new customPercent();
        //Showing the dialog
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
