package com.github.convertiverse.database;

import com.github.convertiverse.ConvertiverseApp;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author Tobias Büser
 */
public class FirestoreUserDao implements UserDao {

	private final ExecutorService executor = Executors.newSingleThreadExecutor();

	private final FirebaseFirestore firestore;

	public FirestoreUserDao(FirebaseFirestore firestore) {
		this.firestore = firestore;
	}

	@Override
	public void addUsages(String converter, int amount) {
		executor.execute(() -> {
			String userUniqueId = ConvertiverseApp.getInstance().getUniqueUserId();

			DocumentReference reference = firestore.collection("converter_usages")
					.document(userUniqueId);
			reference.update(converter, FieldValue.increment(amount));
		});
	}

}
