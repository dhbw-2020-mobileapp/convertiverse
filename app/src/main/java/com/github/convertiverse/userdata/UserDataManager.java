package com.github.convertiverse.userdata;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Tobias Büser
 */
public class UserDataManager {

	private final Map<String, Boolean> unitVisibility = new HashMap<>();
	private final List<HistoryPoint> historyPoints = new ArrayList<>();


	public UserDataManager() {
	}

	public void addHistoryPoint(String unitKey, double value) {
		this.historyPoints.add(new HistoryPoint(unitKey, value));
	}

	public List<HistoryPoint> getHistoryPoints() {
		return this.historyPoints;
	}

	public boolean isVisible(String unitKey) {
		Boolean flag = this.unitVisibility.getOrDefault(unitKey, false);

		return flag != null && flag;
	}

	public void setVisible(String unitKey, boolean flag) {
		this.unitVisibility.put(unitKey, flag);
	}

}
