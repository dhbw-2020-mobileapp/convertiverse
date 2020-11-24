package com.github.convertiverse.category;

import com.github.convertiverse.ConvertiverseApp;
import com.github.convertiverse.converter.BiConverter;
import com.github.convertiverse.converter.Converter;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author Tobias Büser
 */
public class ConverterCategory {

	private String key;
	private String displayName;
	private String iconKey;

	private List<String> converterKeys;
	private Map<String, BiConverter<?, ?>> converters = new HashMap<>();

	public ConverterCategory(String key, String displayName, String iconKey, List<String> converterKeys) {
		this.key = key;
		this.displayName = displayName;
		this.iconKey = iconKey;
		this.converterKeys = converterKeys;

		// get converter from given keys
		for (String converterKey : this.converterKeys) {
			BiConverter<?, ?> converter = ConvertiverseApp.getInstance().getConverter(converterKey);
			if(converter == null) continue;

			this.converters.put(converterKey, converter);
		}
	}

	public ConverterCategory(String key, String displayName, String iconKey, String... converterKeys) {
		this(key, displayName, iconKey, Arrays.asList(converterKeys));
	}

	public String getKey() {
		return key;
	}

	public String getDisplayName() {
		return displayName;
	}

	public String getIconKey() {
		return iconKey;
	}

	public Map<String, BiConverter<?, ?>> getConverters() {
		return converters;
	}

	public List<BiConverter<?, ?>> getConvertersSorted() {
		return converters.values().stream().sorted(Comparator.comparing(Converter::getKey)).collect(Collectors.toList());
	}

}
