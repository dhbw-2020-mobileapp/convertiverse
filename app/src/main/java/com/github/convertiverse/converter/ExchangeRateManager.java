package com.github.convertiverse.converter;

import android.util.Log;
import com.github.convertiverse.ConvertiverseApp;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * @author Tobias Büser
 */
public class ExchangeRateManager {

	/**
	 * Supported currencies can be found here:
	 * https://www.exchangerate-api.com/docs/supported-currencies
	 */
	private Map<String, Double> conversionRates = new HashMap<>();

	/**
	 * API key from v6.exchangerate-api.com
	 */
	private final String apiKey;

	public ExchangeRateManager(String apiKey) {
		this.apiKey = apiKey;
	}

	public double getRateOrDefault(String unitShortKey, double defaultRate) {
		Double rate = this.conversionRates.getOrDefault(unitShortKey, defaultRate);
		return rate == null ? defaultRate : rate;
	}

	public void request() {
		OkHttpClient client = new OkHttpClient();

		Request request = new Request.Builder()
				.url(String.format("https://v6.exchangerate-api.com/v6/%s/latest/USD", this.apiKey))
				.build();
		client.newCall(request).enqueue(new Callback() {
			@Override
			public void onFailure(Call call, IOException e) {
				// something went wrong, we just print it.
				e.printStackTrace();
			}

			@Override
			public void onResponse(Call call, Response response) throws IOException {
				if (!response.isSuccessful()) {
					throw new IOException("Unexpected response: " + response);
				}
				if (response.body() == null) {
					Log.w("ExchangeRateManager", "response body is null");
					return;
				}

				String jsonData = response.body().string();
				try {
					JSONObject jsonObject = new JSONObject(jsonData);
					JSONObject conversionRates = jsonObject.getJSONObject("conversion_rates");

					conversionRates.keys().forEachRemaining(s -> {
						try {
							double rate = conversionRates.getDouble(s);
							ExchangeRateManager.this.conversionRates.put(s, rate);
						} catch (JSONException e) {
							e.printStackTrace();
						}
					});
				} catch (JSONException e) {
					e.printStackTrace();
				}

				Log.i("ExchangeRateManager", "found " + conversionRates.size() + " conversion rates.");
				System.out.println(ConvertiverseApp.getInstance().convert("euro", 36, "japanese_yen"));
			}
		});
	}

}
