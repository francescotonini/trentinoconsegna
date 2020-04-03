package dev.tonini.trentinoconsegna;

import android.content.Context;

import dev.tonini.trentinoconsegna.api.ConsegnoInTrentinoAPI;
import dev.tonini.trentinoconsegna.repositories.ShopsRepository;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DataRepository {
    private static DataRepository instance;
    private final ConsegnoInTrentinoAPI api;
    private final ShopsRepository shopsRepository;

    private DataRepository(final Context context, final AppExecutors appExecutors) {
        api = new Retrofit.Builder()
                .baseUrl("https://consegnointrentino.provincia.tn.it/api/opendata/v2/content/search/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(ConsegnoInTrentinoAPI.class);

        shopsRepository = new ShopsRepository(appExecutors, api);
    }

    public static DataRepository getInstance(final Context appContext, final AppExecutors appExecutors) {
        if (instance == null) {
            synchronized (DataRepository.class) {
                if (instance == null) {
                    instance = new DataRepository(appContext, appExecutors);
                }
            }
        }

        return instance;
    }

    public ShopsRepository getShopsRepository() {
        return shopsRepository;
    }
}
