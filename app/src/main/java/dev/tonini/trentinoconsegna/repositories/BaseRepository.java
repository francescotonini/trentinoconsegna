package dev.tonini.trentinoconsegna.repositories;

import dev.tonini.trentinoconsegna.AppExecutors;
import dev.tonini.trentinoconsegna.api.ConsegnoInTrentinoAPI;
import dev.tonini.trentinoconsegna.helpers.SingleLiveEvent;

public abstract class BaseRepository {
    private final SingleLiveEvent<Void> errors;
    private final ConsegnoInTrentinoAPI api;
    private final AppExecutors appExecutors;

    public BaseRepository(AppExecutors appExecutors, ConsegnoInTrentinoAPI api) {
        this.appExecutors = appExecutors;
        this.api = api;

        this.errors = new SingleLiveEvent<>();
    }

    public AppExecutors getAppExecutors() {
        return appExecutors;
    }

    public ConsegnoInTrentinoAPI getApi() {
        return api;
    }

    public SingleLiveEvent<Void> getErrors() {
        return errors;
    }

    protected void fireError() {
        errors.setValue(null);
    }
}
