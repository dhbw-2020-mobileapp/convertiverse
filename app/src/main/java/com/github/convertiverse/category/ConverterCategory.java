package com.github.convertiverse.category;

/**
 * @author Tobias Büser
 */
public class ConverterCategory {

	private final String key;
	private final String displayName;
	private final String iconKey;

	public ConverterCategory(String key, String displayName, String iconKey) {
		this.key = key;
		this.displayName = displayName;
		this.iconKey = iconKey;
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

}
