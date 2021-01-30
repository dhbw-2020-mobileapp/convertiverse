package com.github.convertiverse.category;

/**
 * @author Tobias Büser
 */
public class ConverterCategory {

	private final String key;
	private final String displayName;
	private final String iconKey;
	private final String colorCode;

	public ConverterCategory(String key, String displayName, String iconKey, String colorCode) {
		this.key = key;
		this.displayName = displayName;
		this.iconKey = iconKey;
		this.colorCode = colorCode;
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

	public String getColorCode() {
		return colorCode;
	}

}
