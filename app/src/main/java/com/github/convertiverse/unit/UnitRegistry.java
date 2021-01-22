package com.github.convertiverse.unit;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author Tobias Büser
 */
public class UnitRegistry {

	private final Map<String, Unit> map = new HashMap<>();

	public void register(Unit unit) {
		this.map.put(unit.getKey(), unit);
	}

	public <T extends Unit> T get(String key) {
		return (T) this.map.get(key);
	}

	public List<Unit> getAll(String categoryKey) {
		return this.map.values().stream()
				.filter(unit -> unit.getCategoryKey().equals(categoryKey))
				.collect(Collectors.toList());
	}

}
