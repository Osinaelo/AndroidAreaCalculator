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

import com.example.andys.myapplication.figures.Circle;
import com.example.andys.myapplication.figures.Square;

public class OneInputActivity extends AppCompatActivity implements InputActivity {

    private String figure;

    /**
     * executed upon creation of the Activity
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_one_input);
        figure = getIntent().getStringExtra("figure");//fetch argument with type of figure passed to activity
        ((TextView) findViewById(R.id.figureTitle)).setText(figure);//use the figure name to set the activity title
        final EditText editText = findViewById(R.id.editText);//get the input field by its id
        switch (figure) {//set hint text based on what figure name was passed
            case "Circle":
                editText.setHint("radius");
                break;
            case "Square":
                editText.setHint("side");
                break;
        }
        //add listener that will trigger when the done button is pressed on the keyboard
        editText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                if (i == EditorInfo.IME_ACTION_DONE) {
                    InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                    if (imm != null) {
                        imm.hideSoftInputFromWindow(editText.getWindowToken(), 0);//close the keyboard
                        editText.clearFocus();//remove highlight from input field
                        return true;
                    }
                }
                return false;
            }
        });
    }

    @Override
    public void calculateArea(View view) {
        double dimension = Double.parseDouble(((EditText) findViewById(R.id.editText)).getText().toString());//get the value from input field
        switch (figure) {
            case "Circle":
                Circle circle = new Circle(dimension);//use the figure and dimension values to create an appropriate figure with its dimensions
                ((TextView) findViewById(R.id.areaResult)).setText("Area is: " + circle.area());//updated the label with the area result
                break;
            case "Square":
                Square square = new Square(dimension);//use the figure and dimension values to create an appropriate figure with its dimensions
                ((TextView) findViewById(R.id.areaResult)).setText("Area is: " + square.area());//updated the label with the area result
                break;
        }
    }
}
