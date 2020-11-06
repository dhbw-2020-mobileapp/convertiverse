package com.github.convertiverse;

import com.github.convertiverse.converter.BiConverter;
import com.github.convertiverse.converter.ConverterRegistry;
import com.github.convertiverse.converter.EuroToDollarConverter;

/**
 * @author Tobias Büser
 */
public class ConvertiverseApp {

	private static ConvertiverseApp INSTANCE;

	public static ConvertiverseApp getInstance() {
		if(INSTANCE == null) INSTANCE = new ConvertiverseApp();
		return INSTANCE;
	}

	private final ConverterRegistry registry;

	public ConvertiverseApp() {
		this.registry = new ConverterRegistry();

		// initialize default converter
		registry.register(new EuroToDollarConverter());
	}

	public BiConverter getConverter(String key) {
		return this.registry.get(key);
	}

}
