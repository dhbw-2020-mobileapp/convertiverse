package com.github.convertiverse.unit;

/**
 * @author Tobias Büser
 */
public abstract class Unit {

	private final String key;
	private final String categoryKey;
	private final String displayName;
	private final String displayNameShort;

	public Unit(String key, String categoryKey, String displayName, String displayNameShort) {
		this.key = key;
		this.categoryKey = categoryKey;
		this.displayName = displayName;
		this.displayNameShort = displayNameShort;
	}

	public String getKey() {
		return key;
	}

	public String getCategoryKey() {
		return categoryKey;
	}

	public String getDisplayName() {
		return displayName;
	}

	public String getDisplayNameShort() {
		return displayNameShort;
	}

}
