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

import com.example.andys.myapplication.figures.Trapezium;

import java.util.ArrayList;

public class ThreeInputActivity extends AppCompatActivity implements InputActivity {

    String figure;

    /**
     * executed upon creation of the Activity
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_three_input);
        figure = getIntent().getStringExtra("figure");//fetch argument with type of figure passed to activity
        ((TextView) findViewById(R.id.figureTitle)).setText(figure);//use the figure name to set the activity title
        final EditText baseA = findViewById(R.id.baseA);//get the input field by its id
        final EditText baseB = findViewById(R.id.baseB);//get the input field by its id
        final EditText height = findViewById(R.id.height);//get the input field by its id
        switch (figure) {//set hint text based on what figure name was passed
            case "Trapezium":
                baseA.setHint("base A");
                baseB.setHint("base B");
                height.setHint("height");
                break;
        }
        ArrayList<EditText> inputFields = new ArrayList<>();
        inputFields.add(baseA);
        inputFields.add(baseB);
        inputFields.add(height);
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
        double baseA = Double.parseDouble(((EditText) findViewById(R.id.baseA)).getText().toString());//get the value from input field
        double baseB = Double.parseDouble(((EditText) findViewById(R.id.baseB)).getText().toString());//get the value from input field
        double height = Double.parseDouble(((EditText) findViewById(R.id.height)).getText().toString());//get the value from input field
        switch (figure) {
            case "Trapezium":
                Trapezium trapezium = new Trapezium(baseA, baseB, height);//use the figure and dimension values to create an appropriate figure with its dimensions
                ((TextView) findViewById(R.id.areaResult)).setText("Area is: " + trapezium.area());//updated the label with the area result
                break;
        }
    }
}
