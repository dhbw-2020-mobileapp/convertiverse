package com.github.convertiverse;

import com.github.convertiverse.category.CategoryRegistry;
import com.github.convertiverse.category.ConverterCategory;
import com.github.convertiverse.converter.BiConverter;
import com.github.convertiverse.converter.ConverterRegistry;
import com.github.convertiverse.converter.EuroToDollarConverter;
import com.github.convertiverse.database.FirestoreUserDao;
import com.github.convertiverse.database.UserDao;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.installations.FirebaseInstallations;
import java.util.List;

/**
 * @author Tobias Büser
 */
public class ConvertiverseApp {

	private static ConvertiverseApp INSTANCE;

	public static ConvertiverseApp getInstance() {
		if(INSTANCE == null) INSTANCE = new ConvertiverseApp();
		return INSTANCE;
	}

	private final ConverterRegistry converterRegistry;
	private final CategoryRegistry categoryRegistry = new CategoryRegistry();

	private String uniqueUserId;
	private final UserDao userDao;

	public ConvertiverseApp() {
		this.converterRegistry = new ConverterRegistry();

		// initialize default converter
		converterRegistry.register(new EuroToDollarConverter());

		// initialize default categories
		categoryRegistry.register(new ConverterCategory("currency", "Währung", "empty"));
		categoryRegistry.register(new ConverterCategory("weight", "Gewicht", "empty"));
		categoryRegistry.register(new ConverterCategory("distance", "Entfernung", "empty"));
		categoryRegistry.register(new ConverterCategory("speed", "Geschwindigkeit", "empty"));

		// connect to db
		FirebaseFirestore db = FirebaseFirestore.getInstance();
		this.userDao = new FirestoreUserDao(db);

		FirebaseInstallations.getInstance().getId().addOnSuccessListener(s -> uniqueUserId = s);
	}

	public BiConverter<?, ?> getConverter(String key) {
		return this.converterRegistry.get(key);
	}

	public ConverterCategory getCategory(String key) {
		return this.categoryRegistry.get(key);
	}

	public List<ConverterCategory> getCategories() {
		return this.categoryRegistry.getAll();
	}

	public String getUniqueUserId() {
		return uniqueUserId;
	}

	public UserDao getUserDao() {
		return userDao;
	}
}
