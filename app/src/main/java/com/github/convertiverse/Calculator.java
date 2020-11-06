package com.github.convertiverse;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class Calculator extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);
    }

    public void calculate(View v) {
        TextView t = findViewById(R.id.editTextNumber);
        float input = Float.parseFloat(t.getText().toString());

        float output = (float) (input * 1.2);

        TextView t2 = findViewById(R.id.editTextNumber2);
        t2.setText("" + output);

        // Log.d("test", "test1");
        // Toast.makeText(this, "test", Toast.LENGTH_LONG).show();
    }
}