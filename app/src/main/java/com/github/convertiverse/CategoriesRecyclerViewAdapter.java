package com.github.convertiverse;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.github.convertiverse.category.ConverterCategory;
import java.util.List;

public class CategoriesRecyclerViewAdapter extends RecyclerView.Adapter<CategoriesRecyclerViewAdapter.CategoriesViewHolder> {

    List<ConverterCategory> categoriesList;
    Context context;

    public CategoriesRecyclerViewAdapter(List<ConverterCategory> categories, Context context) {
        this.categoriesList = categories;
        this.context = context;
    }


    @NonNull
    @Override
    public CategoriesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.category, parent, false);
        CategoriesViewHolder holder = new CategoriesViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull CategoriesViewHolder holder, final int position) {
        holder.categoryName.setText(categoriesList.get(position).getDisplayName());
        //holder.categorieIcon.setImageIcon();

        // Open Converter Activity
        holder.categoryLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ConverterActivity.class);
                intent.putExtra("id", categoriesList.get(position).getKey());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return categoriesList.size();
    }

    public class CategoriesViewHolder extends RecyclerView.ViewHolder {
        TextView categoryName;
        ImageView categoryIcon;
        ConstraintLayout categoryLayout;

        public CategoriesViewHolder(@NonNull View itemView) {
            super(itemView);
            categoryName = itemView.findViewById(R.id.textView_categoryName);
            categoryIcon = itemView.findViewById(R.id.imageView_categoryIcon);
            categoryLayout = itemView.findViewById(R.id.layout_category);
        }
    }

    public class HeaderViewHolder extends RecyclerView.ViewHolder {
        TextView categorieName;
        ImageView categorieIcon;

        public HeaderViewHolder(@NonNull View itemView) { super(itemView);}
    }
}
