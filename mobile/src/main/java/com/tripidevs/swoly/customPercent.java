package com.tripidevs.swoly;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

/**
 * Created by Innocent on 12/21/2016.
 * Class file used for the creation of the custom percentage dialog
 */

public class customPercent extends DialogFragment{
    LayoutInflater inflater;    //New inflater
    //Overriding the onCreateDialog function
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        //Setting the inflater equal to the value of the current activities layout inflater
        inflater = getActivity().getLayoutInflater();
        //Setting new view equal to the inflated value of the layout
        View v = inflater.inflate(R.layout.custom_percent, null);
        //Edit text that will be used to take a new percent value from the user
        final EditText newPercent = (EditText) v.findViewById(R.id.txtCustomPercent);
        //Creating an alert dialog builder
        //Used to perform functions such as setview()
        //Setting value of builder equal to a new builder with argument of the activity
        final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        //Creating the view using the builder
        //Creating a positive button
        //Button creates a new onClickListener
        //If the value of button pressed is "OK" then the current percentage is changed to the
        //new one passed and refreshEachSides from the main activity is called
        builder.setView(v).setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if(!newPercent.getText().toString().equals(""))
                {
                  Constant.percent = Float.parseFloat(newPercent.getText().toString()) / 100;
                    ((MainActivity) getActivity()).refreshEachSide();
                }
            }
        });

        return builder.create();    //Returning the dialog back to the main activity
    }


}
