package com.github.convertiverse.converter;

import com.github.convertiverse.Unit;

/**
 * @author Tobias Büser
 */
public abstract class BiConverter<S extends Unit<?>, T extends Unit<?>> extends Converter<S, T> {

	private String color = "000000";
	private boolean visibility = true;

	public BiConverter(String key) {
		super(key);
	}

	public abstract S backwards(T inputUnit);

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public boolean isVisibility() {
		return visibility;
	}

	public void setVisibility(boolean visibility) {
		this.visibility = visibility;
	}

}
