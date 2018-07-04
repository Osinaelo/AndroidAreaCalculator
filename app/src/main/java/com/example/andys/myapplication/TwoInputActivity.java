package com.example.andys.myapplication;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

import com.example.andys.myapplication.figures.Ellipse;
import com.example.andys.myapplication.figures.Rectangle;

import java.util.ArrayList;

public class TwoInputActivity extends AppCompatActivity implements InputActivity {

    private String figure;

    /**
     * executed upon creation of Activity
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_two_input);
        figure = getIntent().getStringExtra("figure");//fetch argument with type of figure passed to activity
        ((TextView) findViewById(R.id.figureTitle)).setText(figure);//use the figure name to set the activity title
        final EditText dimensionX = findViewById(R.id.dimensionX);//get the input field by its id
        final EditText dimensionY = findViewById(R.id.dimensionY);//get the input field by its id
        switch (figure) {//set hint text based on what figure name was passed
            case "Rectangle":
                dimensionX.setHint("width");
                dimensionY.setHint("height");
                break;
            case "Ellipse":
                dimensionX.setHint("semi-minor axis");
                dimensionY.setHint("semi-major axis");
                break;
        }
        ArrayList<EditText> inputFields = new ArrayList<>();
        inputFields.add(dimensionX);
        inputFields.add(dimensionY);
        for (final EditText field: inputFields) { //for each input field, add a listener
            field.setOnEditorActionListener(new TextView.OnEditorActionListener() {
                @Override
                public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                    if (i == EditorInfo.IME_ACTION_DONE) {
                        InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                        if (imm != null) {
                            imm.hideSoftInputFromWindow(field.getWindowToken(), 0);//close the keyboard
                            field.clearFocus();//remove highlight from input field
                            return true;
                        }
                    }
                    return false;
                }
            });
        }
    }

    @Override
    public void calculateArea(View view) {
        double dimensionX = Double.parseDouble(((EditText) findViewById(R.id.dimensionX)).getText().toString());//get the value from input field
        double dimensionY = Double.parseDouble(((EditText) findViewById(R.id.dimensionY)).getText().toString());//get the value from input field
        switch (figure) {
            case "Rectangle":
                Rectangle rectangle = new Rectangle(dimensionX, dimensionY);//use the figure and dimension values to create an appropriate figure with its dimensions
                ((TextView) findViewById(R.id.areaResult)).setText("Area is: " + rectangle.area());//updated the label with the area result
                break;
            case "Ellipse":
                Ellipse ellipse = new Ellipse(dimensionX, dimensionY);//use the figure and dimension values to create an appropriate figure with its dimensions
                ((TextView) findViewById(R.id.areaResult)).setText("Area is: " + ellipse.area());//updated the label with the area result
                break;
        }
    }
}
