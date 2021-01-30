package com.github.convertiverse.userdata;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Tobias Büser
 */
public class UserDataManager {

	private final Map<String, Boolean> unitVisibility = new HashMap<>();

	public UserDataManager() {
	}

	public boolean isVisible(String unitKey) {
		Boolean flag = this.unitVisibility.getOrDefault(unitKey, false);

		return flag != null && flag;
	}

	public void setVisible(String unitKey, boolean flag) {
		this.unitVisibility.put(unitKey, flag);
	}

}
