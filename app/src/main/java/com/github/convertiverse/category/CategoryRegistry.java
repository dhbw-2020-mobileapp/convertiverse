package com.github.convertiverse.category;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Tobias Büser
 */
public class CategoryRegistry {

	private final Map<String, ConverterCategory> categoryMap = new HashMap<>();

	public void register(ConverterCategory category) {
		this.categoryMap.put(category.getKey(), category);
	}

	public ConverterCategory get(String key) {
		return this.categoryMap.get(key);
	}

	public List<ConverterCategory> getAll() {
		return new ArrayList<>(this.categoryMap.values());
	}

}
