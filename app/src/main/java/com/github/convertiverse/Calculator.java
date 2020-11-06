package com.github.convertiverse;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.github.convertiverse.converter.EuroToDollarConverter;
import com.github.convertiverse.unit.EuroUnit;

public class Calculator extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);
    }

    public void calculate(View v) {
        TextView t = findViewById(R.id.editTextNumber);
        float input = Float.parseFloat(t.getText().toString());

	    EuroToDollarConverter converter = (EuroToDollarConverter) ConvertiverseApp.getInstance().getConverter("euro_to_dollar");

        double output = converter.forwards(new EuroUnit(input)).getValue();

        TextView t2 = findViewById(R.id.editTextNumber2);
        t2.setText("" + output);

        // Log.d("test", "test1");
        // Toast.makeText(this, "test", Toast.LENGTH_LONG).show();
    }
}