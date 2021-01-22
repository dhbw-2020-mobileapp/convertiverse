package com.github.convertiverse.category;

import com.github.convertiverse.unit.Unit;

/**
 * @author Tobias Büser
 */
public class ConverterCategory {

	private final String key;
	private final String displayName;
	private final String iconKey;

	private final Class<? extends Unit> defaultUnit;

	public ConverterCategory(String key, String displayName, String iconKey, Class<? extends Unit> defaultUnit) {
		this.key = key;
		this.displayName = displayName;
		this.iconKey = iconKey;
		this.defaultUnit = defaultUnit;
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

	public Class<? extends Unit> getDefaultUnit() {
		return defaultUnit;
	}

}
