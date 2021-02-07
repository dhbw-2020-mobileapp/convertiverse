package com.github.convertiverse;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.github.convertiverse.category.ConverterCategory;
import com.google.common.collect.Lists;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private List<ConverterCategory> categoriesList;
    private RecyclerView recyclerView;
    private int sortPosition = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // List of Categories
        categoriesList = ConvertiverseApp.getInstance().getCategories();

        // Sort-Dropdown
        Spinner sortSpinner = findViewById(R.id.spinner_sort);
        ArrayAdapter<CharSequence> sortAdapter = ArrayAdapter.createFromResource(this, R.array.categoriesSortingOptions, android.R.layout.simple_spinner_item);
        sortAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sortSpinner.setAdapter(sortAdapter);
        sortSpinner.setOnItemSelectedListener(this);
        sortCategories();

        // Category-Search
        EditText editText = findViewById(R.id.editText_search);
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                categoriesList = ConvertiverseApp.getInstance().getCategories();
                String searchText = s.toString();

                if (!(searchText.equalsIgnoreCase(""))) {
                    String shortenedCategory;

                    for (int i = 0; i < categoriesList.size(); i++) {
                        // Shorten Categorie Display Name so it can be compared to the User Input
                        shortenedCategory = categoriesList.get(i).getDisplayName().substring(0, Math.min(categoriesList.get(i).getDisplayName().length(), searchText.length()));

                        if (!searchText.equalsIgnoreCase(shortenedCategory)) {
                            categoriesList.set(i, null);
                        }
                    }

                    categoriesList.removeAll(Collections.singleton(null));
                }

                if (sortPosition == 0) {
                    sortCategories();
                } else {
                    sortCategories();
                    categoriesList = Lists.reverse(categoriesList);
                }

                setCategoryAdapter();

            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        // Adapter (CategoriesRecyclerViewAdapter)
        recyclerView = findViewById(R.id.recyclerView_categories);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        setCategoryAdapter();
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        if (position == 0) {
            sortPosition = 0;
            sortCategories();
        } else {
            sortPosition = 1;
            sortCategories();
            categoriesList = Lists.reverse(categoriesList);
        }
        String sortingOption = parent.getItemAtPosition(position).toString();
        Toast.makeText(parent.getContext(), sortingOption, Toast.LENGTH_SHORT).show();
        setCategoryAdapter();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
    }

    private void setCategoryAdapter() {
        RecyclerView.Adapter categoriesAdapter = new CategoriesRecyclerViewAdapter(categoriesList, MainActivity.this);
        recyclerView.setAdapter(categoriesAdapter);
    }

    private void sortCategories() {
        categoriesList.sort((object1, object2) -> object1.getDisplayName().compareTo(object2.getDisplayName()));
    }

}