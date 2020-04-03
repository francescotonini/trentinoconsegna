package dev.tonini.trentinoconsegna.repositories;

import dev.tonini.trentinoconsegna.AppExecutors;
import dev.tonini.trentinoconsegna.api.ConsegnoInTrentinoAPI;

public abstract class BaseRepository {
    public BaseRepository(AppExecutors appExecutors, ConsegnoInTrentinoAPI api) {
        this.appExecutors = appExecutors;
        this.api = api;
    }

    public AppExecutors getAppExecutors() {
        return appExecutors;
    }

    public ConsegnoInTrentinoAPI getApi() {
        return api;
    }

    private final ConsegnoInTrentinoAPI api;
    private final AppExecutors appExecutors;
}
