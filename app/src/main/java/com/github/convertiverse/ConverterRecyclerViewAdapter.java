package com.github.convertiverse;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.github.convertiverse.category.CategoryRegistry;
import com.github.convertiverse.unit.Unit;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

public class ConverterRecyclerViewAdapter extends RecyclerView.Adapter {

    List<? extends Unit> unitList;
    Context context;
    String categoryName;

    // Saves all Units so they can be all edited, when changing the amount for a conversion.
    ArrayList<ViewHolderUnit> views = new ArrayList<>();

    public ConverterRecyclerViewAdapter(List<? extends Unit> units, Context context) {
        this.unitList = units;
        this.context = context;
    }

    @Override
    public int getItemViewType(int position) {
        // Differentiate between a "addUnit"-element and "unit"-element (0 = "addUnit"; 1 = "unit")
        return (position == unitList.size()) ? 0 : 1;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view;

        if (viewType == 1) {
            view = layoutInflater.inflate(R.layout.unit, parent, false);
            return new ViewHolderUnit(view);
        } else {
            view = layoutInflater.inflate(R.layout.add_unit, parent, false);
            return new ViewHolderAddUnit(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if(position == unitList.size()) {
            // Set AddUnit-Item

            ViewHolderAddUnit viewHolderAddUnit = (ViewHolderAddUnit)holder;

            viewHolderAddUnit.addUnitText.setText("Einheit hinzufügen");

            // Set onCLickListener for opening the converter activity
            viewHolderAddUnit.addUnitBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Open Visibility settings for the Units
                    AlertDialog.Builder builder = new AlertDialog.Builder(context);

                    String[] unitArray = new String[]{"nm", "mm", "cm", "dm", "m", "km", "..."};
                    final boolean[] checkedUnitArray = new boolean[]{true, false, true, true, false, true, false};

                    //convert Array to List
                    final  List<String> unitList = Arrays.asList(unitArray);
                    //set AlertDialog title
                    builder.setTitle("Einheit auswählen");
                    //set icon (optional)
                    builder.setIcon(R.drawable.currency);
                    //set multichoice
                    builder.setMultiChoiceItems(unitArray, checkedUnitArray, new DialogInterface.OnMultiChoiceClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                            //update current focused item's checked status
                            checkedUnitArray[which] = isChecked;
                            //get current focused Item
                            String currentItem = unitList.get(which);
                            //notify the current action
                            Toast.makeText(context,currentItem + " " + isChecked, Toast.LENGTH_SHORT).show();

                        }
                    });

                    //set positives/yes button click listener
                    builder.setPositiveButton("ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            // change shown items;
                            // unitList has the informations/changes
                        }
                    });

                    // wird eventuell entfernt
                    //set neutral/cancel button click listener
                    builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            //do something here
                        }
                    });

                    AlertDialog dialog = builder.create();
                    dialog.show();
                }
            });

        } else {
            // Set Unit-Item

            ViewHolderUnit viewHolderUnit = (ViewHolderUnit)holder;

            //save item for editing during conversion.
            views.add(viewHolderUnit);

            viewHolderUnit.unitShortName.setText(unitList.get(position).getDisplayNameShort());
            viewHolderUnit.unitShortName.setText(unitList.get(position).getDisplayName());

            // Set Enter-Press Listener
            viewHolderUnit.unitAmount.setOnKeyListener(new View.OnKeyListener() {
                public boolean onKey(View view, int keyCode, KeyEvent keyevent) {
                    //If the keyevent is a key-down event on the "enter" button
                    if ((keyevent.getAction() == KeyEvent.ACTION_DOWN) && (keyCode == KeyEvent.KEYCODE_ENTER)) {
                        //...
                        // Perform your action on key press here
                        // ...

                        Float amount = Float.parseFloat(viewHolderUnit.unitAmount.getText().toString());

                        for (int i=0; i<views.size(); i++) {
                            if (i != position) {
                                ViewHolderUnit v = views.get(i);

                                // Call Converter Method
                                // ...
                                //ConvertiverseApp.getInstance().convert(view, 10, v);

                                EditText inputField = v.unitAmount;
                                inputField.setText("1234.59"); // <-- zum Test

                            }
                        }

                        Toast.makeText(context, "Enter Pressed: " + amount, Toast.LENGTH_SHORT).show(); // <-- nur zum testen
                        return true;
                    }
                    return false;
                }
            });
        }
    }



    @Override
    public int getItemCount() {
        return unitList.size() + 1;
    }

    class ViewHolderUnit extends RecyclerView.ViewHolder {

        TextView unitShortName;
        TextView unitName;
        EditText unitAmount;

        public ViewHolderUnit(@NonNull View itemView) {
            super(itemView);

            unitShortName = itemView.findViewById(R.id.textView_unitShortName);
            unitName = itemView.findViewById(R.id.textView_unitName);
            unitAmount = itemView.findViewById(R.id.editNum_amount);
        }
    }

    class ViewHolderAddUnit extends RecyclerView.ViewHolder {

        TextView addUnitText;
        ConstraintLayout addUnitBtn;

        public ViewHolderAddUnit(@NonNull View itemView) {
            super(itemView);

            addUnitText = itemView.findViewById(R.id.textView_addUnitText);
            addUnitBtn = itemView.findViewById(R.id.layout_add_unit);
        }
    }
}
