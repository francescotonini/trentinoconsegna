package dev.tonini.trentinoconsegna;

import android.app.Application;

public class TrentinoConsegnaApp extends Application {
    private AppExecutors appExecutors;

    @Override
    public void onCreate() {
        super.onCreate();

        // This is a class with different thread pools
        appExecutors = new AppExecutors();
    }

    public DataRepository getDataRepository() {
        return DataRepository.getInstance(getApplicationContext(), appExecutors);
    }

    public AppExecutors getAppExecutors() {
        return appExecutors;
    }
}
