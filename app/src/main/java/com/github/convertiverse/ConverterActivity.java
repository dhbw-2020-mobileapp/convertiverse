package com.github.convertiverse;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.github.convertiverse.category.ConverterCategory;
import com.github.convertiverse.unit.Unit;
import com.github.convertiverse.userdata.HistoryPoint;

import java.util.ArrayList;
import java.util.List;

public class ConverterActivity extends AppCompatActivity {

    private ConverterCategory category;

    private TextView textView_converterHeading;
    private ConstraintLayout constraintLayout_converterColor;
    private ImageView imageView_converterIcon;

    // Adapter
    private RecyclerView recyclerView;
    private RecyclerView.Adapter converterAdapter;
    private RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_converter);

        category = ConvertiverseApp.getInstance().getCategory(getIntent().getStringExtra("key"));

        textView_converterHeading = findViewById(R.id.textView_converterHeading);
        constraintLayout_converterColor = findViewById(R.id.constraintLayout_converterColor);
        imageView_converterIcon = findViewById(R.id.imageView_converterIcon);

        // Set Heading, Color, Icon
        int imgKey = this.getResources().getIdentifier("com.github.convertiverse:drawable/" + category.getKey(), null, null);
        textView_converterHeading.setText(category.getDisplayName());
        constraintLayout_converterColor.setBackgroundColor(Color.parseColor(category.getColorCode()));
        imageView_converterIcon.setImageResource(imgKey);

        // Adapter
        recyclerView = findViewById(R.id.recyclerView_converter);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        setConverterAdapter();
    }



    public void showHistory(View v) {
        // open History-Window
        AlertDialog.Builder builder = new AlertDialog.Builder(ConverterActivity.this);

        // get all history-points
        List<HistoryPoint> historyPointList = ConvertiverseApp.getInstance().getUserDataManager().getHistoryPoints();
        List<HistoryPoint> historyPointListFiltered = new ArrayList<HistoryPoint>();

        // filter historyPointList after category
        for (int i = 0; i < historyPointList.size(); i++) {
            HistoryPoint point = historyPointList.get(i);
            Unit historyUnit = ConvertiverseApp.getInstance().getUnit(point.getUnit());

            if (historyUnit.getCategoryKey().equals(category.getKey())) {
                historyPointListFiltered.add(point);
            }
        }

        // Array with Display-Text
        String[] historyArray = new String[ historyPointList.size() ];

        for (int i = 0; i < historyPointListFiltered.size(); i++) {
            HistoryPoint point = historyPointListFiltered.get(i);
            Unit historyUnit = ConvertiverseApp.getInstance().getUnit(point.getUnit());

            historyArray[i] = (historyUnit.getDisplayName() + ": " + point.getValue()).replace('.', ',');
        }

        //set AlertDialog title
        builder.setTitle("Verlauf");

        //set icon (optional)
        //builder.setIcon(R.drawable.currency);

        //set properties using chaining
        builder.setItems(historyArray, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {


                //Toast.makeText(ConverterActivity.this, historyArray[which], Toast.LENGTH_SHORT).show(); // <-- nur zum testen

            }
        });

        //set neutral/cancel button click listener
        builder.setNegativeButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //do something here
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    public void setConverterAdapter() {
        List<? extends Unit> unitsList = ConvertiverseApp.getInstance().getUnits(category.getKey());
        List<Unit> unitsFilteredList = new ArrayList<>();

        for (Unit unit: unitsList) {
            boolean visible = ConvertiverseApp.getInstance().getUserDataManager().isVisible(unit.getKey());
            if (visible) {

                unitsFilteredList.add(unit);

            }
        }

        //Log.i("TEST_APP", "test: " + unitsFilteredList.stream().map(e -> e.getDisplayName()).reduce("", String::concat)); //<--TEST
        converterAdapter = new ConverterRecyclerViewAdapter(unitsFilteredList, ConverterActivity.this, category.getKey());
        recyclerView.setAdapter(converterAdapter);
    }


}