package com.github.convertiverse.database;

/**
 * @author Tobias Büser
 */
public interface UserDao {

	void addUsages(String converter, int amount);

}
