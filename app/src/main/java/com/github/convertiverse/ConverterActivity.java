package com.github.convertiverse;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.github.convertiverse.category.ConverterCategory;
import com.github.convertiverse.unit.Unit;
import com.github.convertiverse.unit.UnitRegistry;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ConverterActivity extends AppCompatActivity {

    //List<Unit> unitList = new ArrayList<Unit>(); // <-- Wird wahrscheinlich entfernt
    ConverterCategory category;

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
        constraintLayout_converterColor.setBackgroundColor(Color.parseColor("#222222")); // constraintLayout_converterColor.setBackgroundColor(Color.parseColor(category.getColor));
        imageView_converterIcon.setImageResource(imgKey);

        // Adapter
        recyclerView = findViewById(R.id.recyclerView_converter);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        converterAdapter = new ConverterRecyclerViewAdapter(ConvertiverseApp.getInstance().getUnits(category.getKey()), ConverterActivity.this);
        recyclerView.setAdapter(converterAdapter);
    }



    public void showHistory(View v) {
        // Open History
        AlertDialog.Builder builder = new AlertDialog.Builder(ConverterActivity.this);

        final CharSequence[] historyItems = {"Stand 1 ...", "Stand 2 ...", "Stand 3 ...", "Stand 4 ..."}; // STRING???
        //set AlertDialog title
        builder.setTitle("Verlauf");
        //set icon (optional)
        builder.setIcon(R.drawable.currency);
        //set properties using chaining
        builder.setItems(historyItems, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //do something when item is clicked
                Toast.makeText(ConverterActivity.this, historyItems[which].toString(), Toast.LENGTH_SHORT).show(); // <-- nur zum testen
            }
        });

        // wird eventuell entfernt
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


}