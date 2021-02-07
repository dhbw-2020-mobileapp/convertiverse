package com.github.convertiverse;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import com.github.convertiverse.unit.Unit;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

public class ConverterRecyclerViewAdapter extends RecyclerView.Adapter {

    List<? extends Unit> unitList;
    Context context;
    String categoryKey;

    ArrayList<ViewHolderUnit> views = new ArrayList<>();

    public ConverterRecyclerViewAdapter(List<? extends Unit> units, Context context, String categoryKey) {
        this.unitList = units;
        this.context = context;
        this.categoryKey = categoryKey;
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

                    // Get all units
                    final List<? extends Unit> unitsList = ConvertiverseApp.getInstance().getUnits(categoryKey);

                    // DisplayName and visibility
                    final boolean[] checkedUnitArray = new boolean[ unitsList.size() ];
                    String[] unitArray = new String[ unitsList.size() ];

                    for (int i = 0; i < unitsList.size(); i++) {
                        checkedUnitArray[i] = ConvertiverseApp.getInstance().getUserDataManager().isVisible(unitsList.get(i).getKey());
                        unitArray[i] = unitsList.get(i).getDisplayName();
                    }

                    //set AlertDialog title
                    builder.setTitle("Einheit auswählen");

                    //set icon (optional)
                    //builder.setIcon(R.drawable.currency);

                    //set multichoice
                    builder.setMultiChoiceItems(unitArray, checkedUnitArray, new DialogInterface.OnMultiChoiceClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which, boolean isChecked) {

                            //update current focused item's checked status
                            checkedUnitArray[which] = isChecked;

                            //get current focused Item
                            String currentItemKey = unitsList.get(which).getKey();
                            String currentItemName = unitsList.get(which).getDisplayNameShort();

                            //set visibility
                            ConvertiverseApp.getInstance().getUserDataManager().setVisible(currentItemKey, isChecked);

                            //notify the current action
                            Toast.makeText(context,currentItemName + " " + isChecked, Toast.LENGTH_SHORT).show();

                        }
                    });

                    //set positives/yes button click listener
                    builder.setPositiveButton("anpassen", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                            // Set Adapter to update shown Units
                            ((ConverterActivity) context).setConverterAdapter();

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
            viewHolderUnit.unitName.setText(unitList.get(position).getDisplayName());

            // Set Enter-Press Listener
            viewHolderUnit.unitAmount.setOnKeyListener(new View.OnKeyListener() {
                public boolean onKey(View view, int keyCode, KeyEvent keyevent) {
                    //If the keyevent is a key-down event on the "enter" button
                    if ((keyevent.getAction() == KeyEvent.ACTION_DOWN) && (keyCode == KeyEvent.KEYCODE_ENTER)) {

                        double fromValue = 0;
                        double toValue = 0;

                        try {
                            fromValue = Double.parseDouble( views.get(position).unitAmount.getText().toString().replace(',', '.') );
                        } catch (Exception e) {
                            Toast.makeText(context, "ungültige Eingabe", Toast.LENGTH_SHORT).show();
                        }


                        for (int i=0; i<views.size(); i++) {
                            if (i != position) {
                                ViewHolderUnit v = views.get(i);

                                // Call Converter Method and round result
                                try {
	                                toValue = ConvertiverseApp.getInstance().convert(unitList.get(position).getKey(), fromValue, unitList.get(i).getKey());
	                                System.out.println("convert " + unitList.get(position).getKey() + " to " + unitList.get(i).getKey());
                                } catch (IllegalStateException ex) {
                                	// when a converter could not be found
	                                toValue = 0;
                                }
                                BigDecimal bd = new BigDecimal(toValue).setScale(5, RoundingMode.HALF_UP);
                                String output = Double.toString(bd.doubleValue()).replace('.', ',');

                                // Display toValue
                                EditText inputField = v.unitAmount;
                                inputField.setText(output);

                            }
                        }

                        // set history
                        ConvertiverseApp.getInstance().getUserDataManager().addHistoryPoint(unitList.get(position).getKey(), fromValue);

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
