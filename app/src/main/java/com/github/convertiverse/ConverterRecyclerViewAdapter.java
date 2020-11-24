package com.github.convertiverse;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ConverterRecyclerViewAdapter extends RecyclerView.Adapter {

    List<TestClass> unitList;
    Context context;

    public ConverterRecyclerViewAdapter(List<TestClass> units, Context context) {
        this.unitList = units;
        this.context = context;
    }

    @Override
    public int getItemViewType(int position) {
        // 0 = "addUnit"; 1 = "unit"
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
        }

        view = layoutInflater.inflate(R.layout.add_unit, parent, false);
        return new ViewHolderAddUnit(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if(position == unitList.size()) {
            /*holder.button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(context, "Button Clicked", Toast.LENGTH_LONG).show();
                }
            });*/
        }
        else {
            // z.B.: holder.categoryName.setText(categoriesList.get(position).getCategoryName());
        }
    }

    @Override
    public int getItemCount() {
        return unitList.size() + 1;
    }

    class ViewHolderUnit extends RecyclerView.ViewHolder {

        TextView textView_unitShortName;
        TextView textView_unitName;
        EditText editNum_amount;

        public ViewHolderUnit(@NonNull View itemView) {
            super(itemView);

            textView_unitShortName = itemView.findViewById(R.id.textView_unitShortName);
            textView_unitName = itemView.findViewById(R.id.textView_unitName);
            editNum_amount = itemView.findViewById(R.id.editNum_amount);
        }
    }

    class ViewHolderAddUnit extends RecyclerView.ViewHolder {

        TextView textView_addUnitText;
        TextView textView_addUnitBtn;

        public ViewHolderAddUnit(@NonNull View itemView) {
            super(itemView);

            textView_addUnitText = itemView.findViewById(R.id.textView_addUnitText);
            textView_addUnitBtn = itemView.findViewById(R.id.textView_addUnitBtn);
        }
    }
}
