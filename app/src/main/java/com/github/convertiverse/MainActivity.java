package com.github.convertiverse;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

	//Adapter
	private RecyclerView recyclerView;
	private RecyclerView.Adapter categoriesAdapter;
	private RecyclerView.LayoutManager layoutManager;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		// Sort-Dropdown
		Spinner sortSpinner = findViewById(R.id.spinner_sort);
		ArrayAdapter<CharSequence> sortAdapter = ArrayAdapter.createFromResource(this, R.array.categoriesSortingOptions, android.R.layout.simple_spinner_item);
		sortAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		sortSpinner.setAdapter(sortAdapter);
		sortSpinner.setOnItemSelectedListener(this);

		//Adapter
		recyclerView = findViewById(R.id.recyclerView_categories);
		recyclerView.setHasFixedSize(true);
		layoutManager = new LinearLayoutManager(this);
		recyclerView.setLayoutManager(layoutManager);
		categoriesAdapter = new CategoriesRecyclerViewAdapter(ConvertiverseApp.getInstance().getCategories(), MainActivity.this);
		recyclerView.setAdapter(categoriesAdapter);
	}

	@Override
	public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
		String sortingOption = parent.getItemAtPosition(position).toString();
		Toast.makeText(parent.getContext(), sortingOption, Toast.LENGTH_SHORT).show();
	}

	@Override
	public void onNothingSelected(AdapterView<?> parent) {

	}

	//test !!!!!!!!!!!!!
	public void openTest(View v) {
		Intent i = new Intent(this, ConverterActivity.class);
		i.putExtra("categorie", "currency");
		startActivity(i);
	}

}