package com.github.convertiverse;

import com.github.convertiverse.category.CategoryRegistry;
import com.github.convertiverse.category.ConverterCategory;
import com.github.convertiverse.converter.BiConverter;
import com.github.convertiverse.converter.ConverterRegistry;
import com.github.convertiverse.converter.EuroToDollarConverter;
import java.util.List;

/**
 * @author Tobias Büser
 */
public class ConvertiverseApp {

	private static ConvertiverseApp INSTANCE;

	public static ConvertiverseApp getInstance() {
		if(INSTANCE == null) INSTANCE = new ConvertiverseApp();
		return INSTANCE;
	}

	private final ConverterRegistry converterRegistry;
	private final CategoryRegistry categoryRegistry = new CategoryRegistry();

	public ConvertiverseApp() {
		this.converterRegistry = new ConverterRegistry();

		// initialize default converter
		converterRegistry.register(new EuroToDollarConverter());

		// initialize default categories
		categoryRegistry.register(new ConverterCategory("currency", "Währung", "empty"));
		categoryRegistry.register(new ConverterCategory("weight", "Gewicht", "empty"));
		categoryRegistry.register(new ConverterCategory("distance", "Entfernung", "empty"));
		categoryRegistry.register(new ConverterCategory("speed", "Geschwindigkeit", "empty"));
	}

	public BiConverter<?, ?> getConverter(String key) {
		return this.converterRegistry.get(key);
	}

	public ConverterCategory getCategory(String key) {
		return this.categoryRegistry.get(key);
	}

	public List<ConverterCategory> getCategories() {
		return this.categoryRegistry.getAll();
	}

}
