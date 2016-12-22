package com.tripidevs.swoly;

import android.app.Activity;
import android.os.Bundle;
import android.support.wearable.view.WatchViewStub;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.AdapterView;
import android.widget.SpinnerAdapter;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void changeWeight(View v)
    {
        boolean plus = v.equals(findViewById(R.id.maxplus));
        TextView maxWeight = (TextView) findViewById(R.id.max);
        if(plus)
            Constant.max+=5;
        if (!plus&&(((((Constant.max)-5) * Constant.percent) - 45) / 2) > 0)
            Constant.max-=5;
        maxWeight.setText(String.valueOf(Constant.max));
        refreshEachSide();
    }

    public void changePercent(View v)
    {
        boolean plus = v.equals(findViewById(R.id.percentplus));
        TextView percent = (TextView) findViewById(R.id.percent);
        if(plus)
            Constant.percent+=.05f;
        else if ((((((Constant.max)-5) * Constant.percent) - 45) / 2) > 0)
            Constant.percent-=.05f;
        String percentText = String.valueOf(Math.round(Constant.percent*100))+"%";
        percent.setText(percentText);
        refreshEachSide();
    }

    public void refreshEachSide() {
        TextView sides = (TextView) findViewById(R.id.eachside);
        double eachSideWeight;
        eachSideWeight = (((Constant.max * Constant.percent) - 45) / 2);
        if (eachSideWeight > 0)
            sides.setText(String.format("%.2f", eachSideWeight)+" lbs");
    }
}
