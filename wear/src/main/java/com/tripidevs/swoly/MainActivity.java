package com.tripidevs.swoly;
/*
    Main activity for wear
 */
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    //Function to change the weight when the plus or minus buttons are pressed
    public void changeWeight(View v)
    {
        //Bool to check which button is pressed
        //True if plus
        //False if minus
        boolean plus = v.equals(findViewById(R.id.maxplus));
        TextView maxWeight = (TextView) findViewById(R.id.max);
        if(plus)
            Constant.max+=5;
        if (!plus&&(((((Constant.max)-5) * Constant.percent) - 45) / 2) > 0)
            Constant.max-=5;
        maxWeight.setText(String.valueOf(Constant.max));
        refreshEachSide();  //Refreshing the sides text value
    }

    //Function to determine change weight when a preset percentage is clicked
    public void changePercent(View v)
    {
        //Boolean value that will hold if the percent plus button is pressed
        boolean plus = v.equals(findViewById(R.id.percentplus));
        //Textview to hold the object of the percentage text
        TextView percent = (TextView) findViewById(R.id.percent);
        //Checking to see if the button pressed is a plus
        //If it is plus then 5% is added to current percent
        //Else if the value of the percentage is subtracted by 5%
        if(plus)
            Constant.percent+=.05f;
        else if ((((((Constant.max)-5) * Constant.percent) - 45) / 2) > 0)
            Constant.percent-=.05f;
        String percentText = String.valueOf(Math.round(Constant.percent*100))+"%";
        percent.setText(percentText);   //Setting the percent text equal to the percentText variable
        refreshEachSide();  //Refreshing the value of each side text
    }

    //Function to refresh the value of the each sides text
    public void refreshEachSide() {
        TextView sides = (TextView) findViewById(R.id.eachside);
        double eachSideWeight;
        //Setting each side double equal to the result of the appropriate function
        //((Maxweight*percentage)-barweight)/2
        //Checking the value of each side
        //If greater than 0 then value of each side text is changed accordingly
        eachSideWeight = (((Constant.max * Constant.percent) - 45) / 2);
        if (eachSideWeight > 0)
            sides.setText(String.format("%.2f", eachSideWeight)+" lbs");
    }
}
