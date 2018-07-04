package com.example.andys.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void workWithFigure(View view) {
        Button button = (Button) view;
        Intent intent = null;//create an "intent" to transition to a new view based on the selected figure
        switch (button.getText().toString()) {
            case "Circle":
                intent = new Intent(MainActivity.this, OneInputActivity.class);
                break;
            case "Ellipse":
                intent = new Intent(MainActivity.this, TwoInputActivity.class);
                break;
            case "Rectangle":
                intent = new Intent(MainActivity.this, TwoInputActivity.class);
                break;
            case "Square":
                intent = new Intent(MainActivity.this, OneInputActivity.class);
                break;
            case "Trapezium":
                intent = new Intent(MainActivity.this, ThreeInputActivity.class);
                break;
        }
        intent.putExtra("figure", button.getText().toString());//pass argument to intent to be retrieved in onCreate method of other activities
        startActivity(intent);//transition to the new view
    }
}
