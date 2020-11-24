package com.github.convertiverse;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ConverterActivity extends AppCompatActivity {

    // Nur zu testen
    List<TestClass> categoriesTest = new ArrayList<TestClass>();
    List<TestClass> unitTest = new ArrayList<TestClass>();
    // test ende

    //Adapter
    private RecyclerView recyclerView;
    private RecyclerView.Adapter converterAdapter;
    private RecyclerView.LayoutManager layoutManager;

    TextView textView_converterHeading;
    ImageView imageView_converterIcon;

    int categoryId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_converter);

        //nur zum testen
        unitTest.add(new TestClass(0, "währung", "empty"));
        unitTest.add(new TestClass(1, "Gewicht", "empty"));
        unitTest.add(new TestClass(2, "Entfernung", "empty"));
        unitTest.add(new TestClass(3, "Geschwindigkeit", "empty"));
        unitTest.add(new TestClass(4, "währung", "empty"));
        unitTest.add(new TestClass(5, "Gewicht", "empty"));
        unitTest.add(new TestClass(3, "Geschwindigkeit", "empty"));
        unitTest.add(new TestClass(4, "währung", "empty"));
        unitTest.add(new TestClass(5, "Gewicht", "empty"));
        unitTest.add(new TestClass(3, "Geschwindigkeit", "empty"));
        unitTest.add(new TestClass(4, "währung", "empty"));
        unitTest.add(new TestClass(5, "Gewicht", "empty"));
        // test ende

        //Adapter
        recyclerView = findViewById(R.id.recyclerView_converter);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        converterAdapter = new ConverterRecyclerViewAdapter(unitTest, ConverterActivity.this);
        recyclerView.setAdapter(converterAdapter);

        textView_converterHeading = findViewById(R.id.textView_converterHeading);
        imageView_converterIcon = findViewById(R.id.imageView_converterIcon);

        //nur zum testen
        categoriesTest.add(new TestClass(0, "währung", "empty"));
        categoriesTest.add(new TestClass(1, "Gewicht", "empty"));
        categoriesTest.add(new TestClass(2, "Entfernung", "empty"));
        categoriesTest.add(new TestClass(3, "Geschwindigkeit", "empty"));
        categoriesTest.add(new TestClass(4, "währung", "empty"));
        categoriesTest.add(new TestClass(5, "Gewicht", "empty"));
        categoriesTest.add(new TestClass(6, "Entfernung", "empty"));
        categoriesTest.add(new TestClass(7, "Geschwindigkeit", "empty"));
        categoriesTest.add(new TestClass(8, "währung", "empty"));
        categoriesTest.add(new TestClass(9, "Gewicht", "empty"));
        categoriesTest.add(new TestClass(10, "Entfernung", "empty"));
        categoriesTest.add(new TestClass(11, "Geschwindigkeit", "empty"));
        // test ende


        Intent intent = getIntent();
        // wird später wahrscheinlich key (String)
        categoryId = intent.getIntExtra("id", -1);
        TestClass category = null;

        if (categoryId >= 0) {
            // Loop through List of categories to find a match
            for (TestClass t: categoriesTest) {
                if (t.getCategoryId() == categoryId) {
                    category = t;
                }
            }
            textView_converterHeading.setText(category.getCategoryName());
            //imageView_converterIcon.setImageIcon(...);
            //set recyclerview here; I think ?!
        }
    }




/*
    public void calculate(View v) {
        TextView t = findViewById(R.id.editTextNumber);
        float input = Float.parseFloat(t.getText().toString());

	    EuroToDollarConverter converter = (EuroToDollarConverter) ConvertiverseApp.getInstance().getConverter("euro_to_dollar");

        double output = converter.forwards(new EuroUnit(input)).getValue();

        TextView t2 = findViewById(R.id.editTextNumber2);
        t2.setText("" + output);

        // Log.d("test", "test1");
        // Toast.makeText(this, "test", Toast.LENGTH_LONG).show();
    }*/
}